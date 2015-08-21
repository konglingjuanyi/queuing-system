package com.qunar.ops.recruit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qunar.ops.recruit.util.QUtils;

public class AuthInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		System.out.println("preHandle============================");
		if(request.getRequestURI().endsWith("index") || request.getRequestURI().endsWith("login")) {
			return true;
		}
		Object userId = request.getSession().getAttribute("user");//(String)request.getSession().getAttribute("USER_ID");
		if(userId != null) 
			return true;
		else{
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}
			
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

//		System.out.println("postHandle============================");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		System.out.println("afterCompletion============================");
	}

}
