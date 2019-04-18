package com.lwl.context.test;

import com.lwl.context.AppConfig;
import com.lwl.context.service.CircleService1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * date  2019/2/17
 * author liuwillow
 **/
public class Test {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(AppConfig.class);
		CircleService1 bean = applicationContext.getBean(CircleService1.class);
		bean.query();
	}
}
