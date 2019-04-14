package com.lwl.context;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author lwl
 * @date 2019/2/19 14:28
 * @description
 */
public class MyImportRegistrar implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		RootBeanDefinition beanDefinition = new RootBeanDefinition(MyImportedServiceImpl.class);
		registry.registerBeanDefinition("importedService", beanDefinition);
	}
}