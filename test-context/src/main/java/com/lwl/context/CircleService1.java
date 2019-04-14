package com.lwl.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lwl
 * @date 2019/2/28 9:30
 * @description
 */
@Service
public class CircleService1 {
	@Autowired
	private CircleService2 circleService2;

	public CircleService1(){}
	public CircleService1(String s){}
	public CircleService1(Integer i, String s){}

}