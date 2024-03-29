package com.qunar.ops.recruit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qunar.ops.recruit.service.StudentWaiter;

public class AuthInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//System.out.println("preHandle============================"+request.getRequestURI());
		Object userId = request.getSession().getAttribute("user");//(String)request.getSession().getAttribute("USER_ID");
		if(userId!=null){
			if(userId instanceof StudentWaiter){
				if(request.getRequestURI().endsWith("/student/login")||request.getRequestURI().endsWith("/student/register")||request.getRequestURI().endsWith("/student/refresh")) {
					return true;
				}else{
					if(endsWith(request, "error_400.html")){
						return true;
					}else{
						response.sendRedirect("/error_400.html");
						return false;
					}
				}
			}else{
				return true;
			}
		}else{
			if(request.getRequestURI().endsWith("index") || request.getRequestURI().endsWith("login")) {
				return true;
			}
			if(endsWith(request, "selectViewAndStudent")||endsWith(request, "getOneAndTwoStu")||endsWith(request, "getAllYears")||endsWith(request, "getPhaseAndCityByYear")||
					endsWith(request, "updateOprateCity")||endsWith(request, "getCityByYearAndPhase")||
					endsWith(request, "getAllYears1")||endsWith(request, "getPhaseAndCityByYear1")||
					endsWith(request, "updateOprateCity1")||endsWith(request, "getCityByYearAndPhase1")||endsWith(request, "error_400.html")||endsWith(request, "healthcheck.html")){
				return true;
			}
		}
		response.sendRedirect("/error_400.html");
		return false;
			
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
