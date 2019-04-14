package com.lwl.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author lwl
 * @date 2019/3/15 10:42
 * @description
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("------------------执行自定义的postProcessAfterInitialization----------------------");
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("----------------------执行自定义的postProcessBeforeInitialization----------------------------------");
		return bean;
	}
}