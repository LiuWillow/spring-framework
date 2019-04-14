package com.lwl.context.observaleDesignCustom;

/**
 * @author lwl
 * @date 2019/4/3 16:19
 * @description
 */
public class EventTest {
	public static void main(String[] args) {
		Movie movie = new Movie();
		movie.addListener(new Master());
		movie.addListener(new Fattiger());
		Thread thread = new Thread(movie);
		thread.start();
	}
}