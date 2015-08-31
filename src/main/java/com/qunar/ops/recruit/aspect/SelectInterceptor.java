package com.qunar.ops.recruit.aspect;

import java.util.LinkedList;

import javax.annotation.PostConstruct;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SelectInterceptor {
	LinkedList l = new LinkedList();
	@PostConstruct
	public void start(){
//		System.err.println("8888888888888888888888888888888888888888888888888");
	}
	
	 @Pointcut("execution(* com.qunar.ops.recruit.service.JoinService.*(..))")
	 private void log() {
	 }

	 @Before("log()")
	 public void logInterceptor_before() {
	 }
	 @After("log() && args(a)")
	 public void logInterceptor_after(String a) {
	 }
}
