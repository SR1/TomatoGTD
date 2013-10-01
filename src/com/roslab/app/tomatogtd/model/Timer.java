package com.roslab.app.tomatogtd.model;

public class Timer extends Thread implements TimerModelInterface {

	public static final String TAG = "Timer";

	private long startTime;
	private long duringTime = 25 * 60 * 1000;

	private boolean isStart = false;
	private OnTimeUpListener listener;

	@Override
	public long getRemainingTime() {
		long remain = startTime + duringTime - System.currentTimeMillis();
		if (remain > 0)
			return remain;
		else
			return 0;
	}

	@Override
	public void startTimer() {
		if (!isStart) {
			this.startTime = System.currentTimeMillis();
			isStart = true;
			super.start();
		}
	}

	@Override
	public void stopTimer() {
		isStart = false;
	}

	@Override
	public void run() {
		while (isStart && startTime + duringTime > System.currentTimeMillis()) {
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (listener != null)
			listener.onTimeUp();
		isStart = false;
	}

	@Override
	public boolean isStart() {
		return isStart;
	}


	@Override
	public void setOnTimeUpListener(OnTimeUpListener listener) {
		this.listener = listener;
	}
}
