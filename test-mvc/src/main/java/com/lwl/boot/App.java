package com.lwl.boot;

import org.apache.catalina.LifecycleException;

import javax.servlet.ServletException;

/**
 * date  2019/4/14
 * author liuwillow
 **/
public class App {
	public static void main(String[] args) throws ServletException, LifecycleException {
		MyBootApplication.run();
	}
}
