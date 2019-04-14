package com.lwl.boot;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * date  2019/4/13
 * author liuwillow 在spring源码当中已经有一个实现了ServletContainerInitializer的
 * 类SpringServletContainerInitializer，tomcat启动时会扫描出这个类，调用其中的onStartUp方法，
 * 而这个方法中遍历了所有实现了WebApplicationInitializer接口的类，并调用这个接口的onStartup方法，
 * 因此使用者只需要实现这个类即可，不必再手动配置services中的文件
 **/
public class MyWebApplicationInitializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		//初始化spring容器
		AnnotationConfigWebApplicationContext configWebApplicationContext =
				new AnnotationConfigWebApplicationContext();
		configWebApplicationContext.register(AppConfig.class);
		configWebApplicationContext.refresh();

		//添加servlet
		DispatcherServlet dispatcherServlet = new DispatcherServlet(configWebApplicationContext);
		ServletRegistration.Dynamic app = servletContext.addServlet("app", dispatcherServlet);
		app.setLoadOnStartup(1);
		//controller里的要加app前缀才能访问到
		app.addMapping("/app/*");
	}
}
