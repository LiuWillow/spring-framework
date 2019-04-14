package com.lwl.context;

import java.lang.reflect.Field;

/**
 * @author lwl
 * @date 2019/3/1 15:00
 * @description
 */
public class TestSet {
	public static void main(String[] args) throws IllegalAccessException {
		Set1 set1 = new Set1();
		Field[] declaredFields = set1.getClass().getDeclaredFields();
		Set2 set2 = new Set2();
		Field declaredField = declaredFields[0];
		declaredField.setAccessible(true);
		declaredField.set(set1, set2);

		Field[] set2Fileds = set2.getClass().getDeclaredFields();
		Field set2Filed = set2Fileds[0];
		set2Filed.setAccessible(true);
		set2Filed.set(set2, set1);

	}
}