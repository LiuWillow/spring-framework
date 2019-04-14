package com.lwl.context.observableDesignSpring;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/**
 * @author lwl
 * @date 2019/3/6 9:29
 * @description
 */
@Component
public class SpringStartListener implements ApplicationListener<ContextStartedEvent> {
	@Override
	public void onApplicationEvent(ContextStartedEvent event) {
		System.out.println("spring start 监听器");
	}
}