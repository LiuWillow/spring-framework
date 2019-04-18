package com.lwl.context.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * date  2019/4/16
 * author liuwillow
 **/
@Aspect
public class CircleService2Aspect {
	@Pointcut("execution(* com.lwl.context.service.CircleService1Impl.*(..)")
	public void pc(){}

	@Before("pc")
	public void before(){
		System.out.println("-------before query------");
	}
}
