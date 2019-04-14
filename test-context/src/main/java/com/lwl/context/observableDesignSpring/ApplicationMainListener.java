package com.lwl.context.observableDesignSpring;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author lwl
 * @date 2019/3/6 9:44
 * @description
 */
@Component
public class ApplicationMainListener implements ApplicationListener<ApplicationMailEvent> {

	@Override
	public void onApplicationEvent(ApplicationMailEvent event) {
		System.out.println("application main listener");
	}
}