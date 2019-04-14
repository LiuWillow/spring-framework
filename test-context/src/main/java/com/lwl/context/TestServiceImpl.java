package com.lwl.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * date  2019/2/17
 * author liuwillow
 **/
@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private Bean1 bean1;
	@Autowired
	private MyImportedService importedService;
	@Override
	public void query(){
		System.out.println("query");
	}
}
