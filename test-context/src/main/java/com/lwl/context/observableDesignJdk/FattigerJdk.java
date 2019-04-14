package com.lwl.context.observableDesignJdk;

import java.util.Observable;
import java.util.Observer;

/**
 * @author lwl
 * @date 2019/4/3 16:40
 * @description
 */
public class FattigerJdk implements Observer {
	@Override
	public void update(Observable observable, Object arg) {
		System.out.println("fattiger update");
	}
}