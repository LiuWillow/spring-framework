package com.lwl.context;

import org.springframework.context.SmartLifecycle;

/**
 * author liuweilong
 * date 2019/10/23 16:56
 * desc
 */
public class MySmartLifeCircle implements SmartLifecycle {
	@Override
	public boolean isAutoStartup() {
		return false;
	}

	@Override
	public void stop(Runnable callback) {

	}

	@Override
	public void start() {

	}

	@Override
	public void stop() {

	}

	@Override
	public boolean isRunning() {
		return false;
	}

	@Override
	public int getPhase() {
		return 0;
	}
}
