package com.lwl.context.observableDesignJdk;

import java.util.Observable;

/**
 * @author lwl
 * @date 2019/4/3 16:42
 * @description
 */
public class MovieJdk extends Observable {
	public void move(){
		setChanged();
		notifyObservers();
	}
}