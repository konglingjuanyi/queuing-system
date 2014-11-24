package com.qunar.ops.oaengine.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class OaAuthInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(request.getRequestURI().endsWith("index.html") || request.getRequestURI().endsWith("login")) {
			return true;
		}
		String userId = (String)request.getSession().getAttribute("USER_ID");
		if(userId != null) return true;
		response.sendRedirect(request.getContextPath()+"/oa/index.html");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
