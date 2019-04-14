package com.lwl.context.observableDesignSpring;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author lwl
 * @date 2019/3/6 9:10
 * @description
 */
@Component
public class MailSendListener implements ApplicationListener<MailSendEvent> {
	@Override
	public void onApplicationEvent(MailSendEvent event) {
		System.out.println("------监听到mailSend");
	}
}