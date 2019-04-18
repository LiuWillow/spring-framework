package com.lwl.context.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lwl
 * @date 2019/2/28 9:30
 * @description
 */
@Service
public class CircleService1Impl implements CircleService1{
	@Autowired
	private CircleService2 circleService2;

	public CircleService1Impl(){}
	public CircleService1Impl(String s){}
	public CircleService1Impl(Integer i, String s){}

	@Override
	public void query() {
		System.out.println("----query------");
	}
}