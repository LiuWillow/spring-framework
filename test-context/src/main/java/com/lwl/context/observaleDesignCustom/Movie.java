package com.lwl.context.observaleDesignCustom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lwl
 * @date 2019/4/3 16:19
 * @description
 */
public class Movie implements Runnable {
	private List<MovieListener> listeners = new ArrayList<>();
	public void addListener(MovieListener movieListener){
		listeners.add(movieListener);
	}

	public void play(){
		System.out.println("电影开始播放");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		final MovieEvent movieEvent = new MovieEvent(this);
		movieEvent.setContext("onePeople");
		movieEvent.setType(1);
		listeners.forEach(listener -> listener.m(movieEvent));
	}

	@Override
	public void run() {
		play();
	}
}