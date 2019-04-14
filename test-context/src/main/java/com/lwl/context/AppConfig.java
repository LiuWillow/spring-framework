package com.lwl.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * date  2019/2/17
 * author liuwillow
 **/
@Configuration
@ComponentScan("com.lwl")
@Import(MyImportRegistrar.class)
public class AppConfig {
	@Bean
	public Bean1 getBean1(){
		return new Bean1();
	}

	@Bean
	public Bean2 getBean2(){
		getBean1();
		return new Bean2();
	}
}
