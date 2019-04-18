package com.lwl.context.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lwl
 * @date 2019/2/28 9:30
 * @description
 */
@Service
public class CircleService2 {
	@Autowired
	private CircleService1Impl circleService1;

	public CircleService2(){

	}
	public CircleService2(String str, String str2){

	}
	public CircleService2(Integer i, String str){

	}
}