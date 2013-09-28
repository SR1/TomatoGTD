package com.roslab.app.tomatogtd.enity;

public class TimerState {
	
	private boolean isStart = false;
	private long startTime;
	private long duringTime;
	
	public boolean isStart() {
		return isStart;
	}
	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getDuringTime() {
		return duringTime;
	}
	public void setDuringTime(long duringTime) {
		this.duringTime = duringTime;
	}

}
