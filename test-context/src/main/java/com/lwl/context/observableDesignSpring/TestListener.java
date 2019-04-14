package com.lwl.context.observableDesignSpring;

import com.lwl.context.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lwl
 * @date 2019/3/6 9:28
 * @description
 */
public class TestListener {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.start();
		context.getBean(ApplicationMailBean.class).sendMail();
	}
}