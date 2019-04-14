package com.lwl.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author lwl
 * @date 2019/2/19 15:27
 * @description
 */
@Component
public class MyRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("-----------执行自定义registryPostProcessor的方法postProcessBeanDefinitionRegistry--------");
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("--------------执行自定义registryPostProcessor父接口beanPostProcessor的方法postProcessBeanFactory-------------------------");
	}
}