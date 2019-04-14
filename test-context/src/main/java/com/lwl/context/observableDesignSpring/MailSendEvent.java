package com.lwl.context.observableDesignSpring;

import org.springframework.context.ApplicationEvent;

/**
 * @author lwl
 * @date 2019/3/6 9:11
 * @description
 */
public class MailSendEvent extends ApplicationEvent {
	/**
	 * Create a new ApplicationEvent.
	 *
	 * @param source the object on which the event initially occurred (never {@code null})
	 */
	public MailSendEvent(Object source) {
		super(source);
	}
}