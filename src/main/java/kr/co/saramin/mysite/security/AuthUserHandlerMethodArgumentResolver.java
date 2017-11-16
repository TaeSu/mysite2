package kr.co.saramin.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import kr.co.saramin.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		if (this.supportsParameter(parameter) == false) {
			return WebArgumentResolver.UNRESOLVED;
		}

		HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpSession session = httpServletRequest.getSession();

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		return authUser;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		//@AuthUser 가 붙어 있는지
		if (parameter.getParameterAnnotation(AuthUser.class) == null) {
			return false;
		}
		
		// 타입이 UserVo
		if (parameter.getParameterType().equals(UserVo.class) == false) {
			return false;
		}
		
		return true;
	}

}
