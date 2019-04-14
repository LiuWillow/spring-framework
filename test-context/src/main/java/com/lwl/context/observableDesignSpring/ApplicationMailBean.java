package com.lwl.context.observableDesignSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author lwl
 * @date 2019/3/6 9:45
 * @description
 */
@Component
public class ApplicationMailBean {
	@Autowired
	private ApplicationContext applicationContext;
	public void sendMail(){
		applicationContext.publishEvent(new ApplicationMailEvent(applicationContext));
	}
}