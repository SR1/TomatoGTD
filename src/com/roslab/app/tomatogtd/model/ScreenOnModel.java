package com.roslab.app.tomatogtd.model;

import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class ScreenOnModel {

	private PowerManager powerManager;
	private WakeLock wakeLock;
	private boolean isOn = false;

	public ScreenOnModel(Context context) {
		powerManager = (PowerManager) context
				.getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK,
				"Screen On Lock");
	}

	public boolean isOn() {
		return isOn;
	}

	/***
	 * 保持屏幕常亮
	 */
	public void on() {
		if (!isOn)
			wakeLock.acquire();
		isOn = true;
	}

	/***
	 * 关闭屏幕常亮
	 */
	public void off() {
		if (isOn)
			wakeLock.release();
		isOn = false;
	}
}
