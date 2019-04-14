package com.lwl.context.observableDesignJdk;

/**
 * @author lwl
 * @date 2019/4/3 16:43
 * @description
 */
public class TestJdk {
	public static void main(String[] args) {
		MovieJdk movieJdk = new MovieJdk();
		MasterJdk masterJdk = new MasterJdk();
		movieJdk.addObserver(masterJdk);
		movieJdk.addObserver(new FattigerJdk());
		// 可以随时移除
//		movieJdk.deleteObserver(masterJdk);
		movieJdk.move();
	}
}