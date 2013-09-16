/*package com.roslab.app.tomatogtd.controler;

import java.util.ArrayList;
import java.util.Date;

import com.roslab.app.tomatogtd.interfaces.OnTimerStateChangeListener;

public class TimerThread extends Thread {

	public static final int SLEEP_DURING = 500;

	private long startTime;
	private long duringTime = 1000 * 60 * 25;
	private boolean isRun = false;
	private ArrayList<OnTimerStateChangeListener> listener = new ArrayList<OnTimerStateChangeListener>();

	private volatile static TimerThread timer;

	// 获取计时器线程唯一实例
	public static TimerThread getInstance() {
		if (timer == null) {
			synchronized (TimerThread.class) {
				if (timer == null) {
					timer = new TimerThread();
					;
				}
			}
		}
		return timer;
	}

	// 私有化计时器线程
	private TimerThread() {
	}

	// 计时器运行中
	@Override
	public void run() {

		isRun = true;
		Date date;
		date = new Date();
		startTime = date.getTime();
		for (OnTimerStateChangeListener l : listener) {
			l.onTimerStart();
		}
		date = new Date();
		while (date.getTime() < (startTime + duringTime)) {
			if (isRun) {
				try {
					for (OnTimerStateChangeListener l : listener) {
						l.onTimerLoop();
					}
					Thread.sleep(SLEEP_DURING);
					date = new Date();
				} catch (Exception e) {
				}
			}
		}
		for (OnTimerStateChangeListener l : listener) {
			l.onTimeUp();
		}
	}

	// 添加计时器监听器
	public void setOnTimerStateChangeListener(
			OnTimerStateChangeListener listener) {
		this.listener.add(listener);
		if (isRun)
			listener.onTimerStart();
	}

	// 移除计时器监听器
	public void removeOnTimerStateChangeListener(
			OnTimerStateChangeListener listener) {
		this.listener.remove(listener);
	}

	// 停止计时器
	public void stopTimer() {
		isRun = false;
		for (OnTimerStateChangeListener l : listener) {
			l.onTimerStop();
		}
	}

	// 获取剩余时间
	public long getRemainTime() {
		Date date = new Date();
		return startTime + duringTime - date.getTime();
	}

	// 返回线程运行状态
	public boolean isRun() {
		return isRun;
	}
}
*/