package com.lwl.context.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.Resource;

/**
 * date  2019/6/15
 * author liuwillow
 **/
public class ResourceService {
	private QualifyService qualifyService1;
	private QualifyService qualifyService2;

	public QualifyService getQualifyService1() {
		return qualifyService1;
	}

	@Resource
	public void setQualifyService1(QualifyService qualifyService1) {
		this.qualifyService1 = qualifyService1;
	}

	public QualifyService getQualifyService2() {
		return qualifyService2;
	}

	@Required
	public void setQualifyService2(QualifyService qualifyService2) {
		this.qualifyService2 = qualifyService2;
	}
}
