package com.lwl.boot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * date  2019/4/14
 * author liuwillow
 **/
@Controller
@RequestMapping("/my")
public class MyController {
	@GetMapping("")
	@ResponseBody
	public String hello(){
		System.out.println("-----------进入hello--------------");
		return "HELLO";
	}
}
