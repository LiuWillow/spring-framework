package com.lwl.context.observaleDesignCustom;

/**
 * @author lwl
 * @date 2019/4/3 16:33
 * @description
 */
public class Master implements MovieListener {
	public static final int SOFT_MOVIE = 1;
	@Override
	public void m(MovieEvent movieEvent){
		if (movieEvent.getType() == SOFT_MOVIE)
			System.out.println("接收到 1 ，温情片事件，班长掉眼泪");
	}
}