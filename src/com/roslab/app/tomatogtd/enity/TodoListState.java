package com.roslab.app.tomatogtd.enity;

public class TodoListState {
	
	int currentPosition;
	boolean isTodoListChange = true;

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}

	public boolean isTodoListChange() {
		return isTodoListChange;
	}

	public void setTodoListChange(boolean isTodoListChange) {
		this.isTodoListChange = isTodoListChange;
	}
}
