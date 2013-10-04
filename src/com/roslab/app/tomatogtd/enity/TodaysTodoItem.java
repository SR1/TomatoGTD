package com.roslab.app.tomatogtd.enity;

public class TodaysTodoItem {
	
	public static final String TAG = "TodaysTodoItem";
	
	String today;
	int id;
	int allTodoId;
	String subject;
	String remark;
	String addTime;
	String dueTime;
	int firstEstimate;
	int secondEstimate;
	int thirdEstimate;
	int finishNumber;
	int innerInterrupt;
	int outterInterrupt;
	int color;
	
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAllTodoId() {
		return allTodoId;
	}
	public void setAllTodoId(int allTodoId) {
		this.allTodoId = allTodoId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getDueTime() {
		return dueTime;
	}
	public void setDueTime(String dueTime) {
		this.dueTime = dueTime;
	}
	public int getFirstEstimate() {
		return firstEstimate;
	}
	public void setFirstEstimate(int firstEstimate) {
		this.firstEstimate = firstEstimate;
	}
	public int getSecondEstimate() {
		return secondEstimate;
	}
	public void setSecondEstimate(int secondEstimate) {
		this.secondEstimate = secondEstimate;
	}
	public int getThirdEstimate() {
		return thirdEstimate;
	}
	public void setThirdEstimate(int thirdEstimate) {
		this.thirdEstimate = thirdEstimate;
	}
	public int getInnerInterrupt() {
		return innerInterrupt;
	}
	public void setInnerInterrupt(int innerInterrupt) {
		this.innerInterrupt = innerInterrupt;
	}
	public int getOutterInterrupt() {
		return outterInterrupt;
	}
	public void setOutterInterrupt(int outterInterrupt) {
		this.outterInterrupt = outterInterrupt;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getFinishNumber() {
		return finishNumber;
	}
	public void setFinishNumber(int finishNumber) {
		this.finishNumber = finishNumber;
	}
}
