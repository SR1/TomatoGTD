package com.roslab.app.tomatogtd.enity;

public class AllTodosItem {
	
	int id;
	String subject;
	String remark;
	String addTime;
	String dueTime;
	String doneTime;
	boolean isUnPlaned;
	int color;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getDoneTime() {
		return doneTime;
	}
	public void setDoneTime(String doneTime) {
		this.doneTime = doneTime;
	}
	public boolean isUnPlaned() {
		return isUnPlaned;
	}
	public void setUnPlaned(boolean isUnPlaned) {
		this.isUnPlaned = isUnPlaned;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
}
