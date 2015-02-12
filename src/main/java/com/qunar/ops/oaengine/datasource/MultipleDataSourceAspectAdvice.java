package com.qunar.ops.oaengine.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MultipleDataSourceAspectAdvice {
	
	@Pointcut("@annotation(com.qunar.ops.oaengine.datasource.Read)")  
    public void readPointcut(){    
    }
	
	@Pointcut("@annotation(com.qunar.ops.oaengine.datasource.Write)")  
    public void writePointcut(){    
    }
	
    @Around("readPointcut()")
    public Object doAroundRead(ProceedingJoinPoint jp) throws Throwable {
    	MultipleDataSource.setDataSourceKey("dataSourceRead");
        return jp.proceed();
    }
    
    @Around("writePointcut()")
    public Object doAroundWrite(ProceedingJoinPoint jp) throws Throwable {
        MultipleDataSource.setDataSourceKey("dataSourceWrite");
        return jp.proceed();
    }
}
