package com.lwl.boot;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

/**
 * date  2019/4/14
 * author liuwillow 实现这个接口，并放到META-INF/services/java.servlet.servletContainerInitializer中，
 * tomcat启动时会自动调用onstartup方法，前提是项目为一个webApp项目，并且读取@HandlerTypes里的类，将其传给
 * onStartUp方法的set参数
 **/
@HandlesTypes(DemoClass.class)
public class CustomInitializer implements ServletContainerInitializer {
	@Override
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		ServletRegistration.Dynamic myServlet = ctx.addServlet("myServlet", new MyServlet());
		myServlet.addMapping("/myServlet");
	}
}
