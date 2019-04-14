package com.lwl.context.observableDesignSpring;

import org.springframework.context.ApplicationEvent;

/**
 * @author lwl
 * @date 2019/3/6 9:43
 * @description
 */
public class ApplicationMailEvent extends ApplicationEvent {
	private String content;
	/**
	 * Create a new ApplicationEvent.
	 *
	 * @param source the object on which the event initially occurred (never {@code null})
	 */
	public ApplicationMailEvent(Object source) {
		super(source);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}