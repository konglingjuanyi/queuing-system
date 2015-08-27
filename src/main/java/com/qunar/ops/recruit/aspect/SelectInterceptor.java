package com.qunar.ops.recruit.aspect;

import javax.annotation.PostConstruct;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SelectInterceptor {
	
	@PostConstruct
	public void start(){
		System.err.println("8888888888888888888888888888888888888888888888888");
	}
	
	 @Pointcut("execution(* com.qunar.ops.recruit.service.JoinService.*(..))")
	 private void log() {
	 }

	 @Before("log()")
	 public void logInterceptor_before() {
	  System.out.println("Before LOG:"+" info has loged to file");
	 }
	 @After("log() && args(a)")
	 public void logInterceptor_after(String a) {

	  System.out.println("After LOG:"+a+" info has loged to file");
	 }
}
