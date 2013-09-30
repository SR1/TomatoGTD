package com.roslab.app.tomatogtd.enity;

public class TimerState {
	
	private boolean isStart = false;
	private long remainTime;
	
	public boolean isStart() {
		return isStart;
	}
	
	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public long getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(long remainTime) {
		this.remainTime = remainTime;
	}
}
