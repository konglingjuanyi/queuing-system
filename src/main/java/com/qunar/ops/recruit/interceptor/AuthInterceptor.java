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
		
		if(endsWith(request, "index") ||endsWith(request, "login")||
				endsWith(request, "getAllYears")||endsWith(request, "getPhaseAndCityByYear")||
				endsWith(request, "updateOprateCity")||endsWith(request, "getCityByYearAndPhase")||
				endsWith(request, "getAllYears1")||endsWith(request, "getPhaseAndCityByYear1")||
				endsWith(request, "updateOprateCity1")||endsWith(request, "getCityByYearAndPhase1")){
			return true;
		}
		
		
		Object userId = request.getSession().getAttribute("user");//(String)request.getSession().getAttribute("USER_ID");
		if(userId != null) 
			return true;
		else{
			response.sendError(404);
			return false;
		}
			
	}

	private boolean endsWith(HttpServletRequest request, String string) {
		return request.getRequestURI().endsWith(string);
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
