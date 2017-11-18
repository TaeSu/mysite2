package kr.co.saramin.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.saramin.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1.handler 종류 확인
		if (handler instanceof HandlerMethod == false) {
			return true;
		}
		
		//2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. Method @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		System.out.println("AuthInterceptor method Auth: " + auth);
		
		//4.Handler Method에 @Auth가 없다면
		//  Type(Class)에 @Auth가 붙어 있는지 확인
		if (auth == null) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
			
			System.out.println("AuthInterceptor class Auth: " + auth);
		}
		
		//4-1. @Auth가 없ㅇ면
		if (auth == null) {
			return true;
		}
		
		//5. 인증 여부 체크
		HttpSession session = request.getSession();
		if (session == null) {
			response.sendRedirect("/mysite2/user/login");
			return false;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if (authUser == null) {
			response.sendRedirect("/mysite2/user/login");
			return false;
		}
		
		Auth.Role role = auth.Role();
		System.out.println("Auth User Role: " + role);
		
		return true;
	}

}
