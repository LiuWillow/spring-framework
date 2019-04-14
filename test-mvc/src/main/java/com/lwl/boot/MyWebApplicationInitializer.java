package com.lwl.boot;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * date  2019/4/13
 * author liuwillow
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
