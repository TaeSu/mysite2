package kr.co.saramin.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.saramin.mysite.dto.JSONResult;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public void handlerException(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
		// 1. 로깅
		StringWriter error = new StringWriter();
		e.printStackTrace(new PrintWriter(error));
		
		// 2. 안내페이지
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("error", error.toString());
//		mav.setViewName("error/exception");
		
		String accept = request.getHeader("accept");
		if (accept.matches(".*application/json.*") == true) {
			response.setStatus(HttpServletResponse.SC_OK);
			JSONResult jsonResult = JSONResult.fail(error.toString());
			String jsonString = new ObjectMapper().writeValueAsString(jsonResult);
			
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(jsonString);
		} else {
			request.setAttribute("error", error);
			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
		}
	}
}
