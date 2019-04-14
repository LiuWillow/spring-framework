package com.lwl.boot;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

/**
 * date  2019/4/14
 * author liuwillow
 **/
public class MyBootApplication {
	public static void run() throws ServletException, LifecycleException {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(9888);
		String basePath = MyBootApplication.class.getResource("/").getPath();
		//这里要把html文件放到父目录下的src/main/webapp，因为springboot就是这么做的
		File file = new File("src/main/webapp");
		//告诉tomcat你的webapp在哪
		Context context = tomcat.addWebapp("/", file.getAbsolutePath());

		//告诉tomcat你的classes文件在哪
		WebResourceRoot resourceRoot = new StandardRoot(context);
		resourceRoot.addPreResources(new DirResourceSet(resourceRoot, "/WEB-INF/classes",
				basePath, "/"));

		context.setResources(resourceRoot);

		tomcat.start();
		tomcat.getServer().await();

	}
}
