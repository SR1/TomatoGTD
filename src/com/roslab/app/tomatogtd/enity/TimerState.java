package com.roslab.app.tomatogtd.enity;

public class TimerState {
	
	private boolean isStart = false;
	private long remainTime;
	private int runningTodaysTodoId = -1;
	
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

	public int getRunningTodaysTodoId() {
		return runningTodaysTodoId;
	}

	public void setRunningTodaysTodoId(int runningTodaysTodoId) {
		this.runningTodaysTodoId = runningTodaysTodoId;
	}
}
