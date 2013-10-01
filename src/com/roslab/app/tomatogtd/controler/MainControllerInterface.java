package com.roslab.app.tomatogtd.controler;

import java.util.ArrayList;

import com.roslab.app.tomatogtd.enity.TimerState;
import com.roslab.app.tomatogtd.enity.TodaysTodoItem;
import com.roslab.app.tomatogtd.enity.TodoListState;

/***
 * 主控制器抽象接口
 * @author SR1
 */
public interface MainControllerInterface {
	
	/**
	 * 获取计时器状态
	 * @return 表示计时器状态的一个实例
	 */
	public TimerState getTimerState();

	/**
	 * 启动计时器
	 */
	public void startTimer();
	
	/**
	 * 停止计时器
	 */
	public void stopTimer();
	
	/***
	 * 设置待办列表状态
	 * @param state 待办列表状态对象
	 */
	public void setTodoListState(TodoListState state);
	
	/***
	 * 获取待办列表状态 
	 * @return 待办列表状态对象
	 */
	public TodoListState getTodoListState();
	
	/***
	 * 获取本日待办列表
	 * @return 本日待办列表
	 */
	public ArrayList<TodaysTodoItem> getTodayTodsList();
}
