package com.lwl.context.observableDesignSpring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * @author lwl
 * @date 2019/3/6 9:29
 * @description
 */
public class SpringMailEvent extends ApplicationContextEvent {
	/**
	 * Create a new ContextStartedEvent.
	 *
	 * @param source the {@code ApplicationContext} that the event is raised for
	 *               (must not be {@code null})
	 */
	public SpringMailEvent(ApplicationContext source) {
		super(source);
	}
}