package com.lwl.context.observaleDesignCustom;

/**
 * @author lwl
 * @date 2019/4/3 16:26
 * @description
 */
public class MovieEvent {
	private String context;
	private int type;
	//事件源，表示这个事件是由哪个源发生的
	private Object source;

	public MovieEvent(Object source){
		this.source = source;
	}


	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}