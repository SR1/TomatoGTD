package com.roslab.app.tomatogtd.enity;

public class TodaysTodoItem {

	private String title;
	private String remark;
	private String startTime;
	private String endTime;
	private int[] tomato = new int[3];
	private int total = 0;
	private int tomatoDone = 0;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int[] getTomato() {
		return tomato;
	}

	public void setTomatoOne(int one) {
		this.tomato[0] = one;
		countTotal();
	}

	public void setTomatoTwo(int two) {
		this.tomato[1] = two;
		countTotal();
	}

	public void setTomatoThree(int three) {
		this.tomato[2] = three;
		countTotal();
	}

	public int getTomatoDone() {
		return tomatoDone;
	}

	public void doneTomato() {
		if (tomatoDone < total) {
			tomatoDone++;
		}
	}

	private void countTotal() {
		total = tomato[0] + tomato[1] + tomato[2];
	}
}
