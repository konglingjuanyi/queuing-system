package com.qunar.ops.recruit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qunar.ops.recruit.util.QUtils;

public class OaAuthInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("come to OaAuthInterceptor=======");
		if(request.getRequestURI().endsWith("index.html") || request.getRequestURI().endsWith("login")|| request.getRequestURI().endsWith("qmonitor.jsp")) {
			return true;
		}
		//String userId = (String)request.getSession().getAttribute("USER_ID");
		String userId = QUtils.getUsername(request);
		if(userId != null) return true;
		response.sendRedirect(request.getContextPath()+"/login");
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
