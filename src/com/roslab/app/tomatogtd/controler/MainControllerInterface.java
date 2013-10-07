package com.roslab.app.tomatogtd.controler;

import java.util.ArrayList;

import com.roslab.app.tomatogtd.activity.ValidateViewHandler;
import com.roslab.app.tomatogtd.enity.AllTodosItem;
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
	public void startTimer(int todaysTodoId);
	
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

	/***
	 * 添加内部中断
	 * @param listener 操作完成后调用这个监听器的方法
	 */
	public void addInnerInterrupt(int todaysTodoId);
	
	/***
	 * 添加外部中断
	 * @param listener 操作完成后调用这个监听器的方法
	 */
	public void addOutterInterrupt(int todaysTodoId);
	
	/***
	 * 添加待办事项
	 * @param subject 待办事项的主题
	 * @param remark 待办事项的备注
	 * @return 操作是否成功
	 */
	public boolean addTodos(String subject, String remark);
	
	/***
	 * 获取所有未完成待办
	 * @return 所有未完成待办列表，长度为0时表示列表空
	 */
	public ArrayList<AllTodosItem> getAllUnfinishTodos();
	
	/***
	 * 注册刷新界面的handler
	 * @param handler
	 */
	public void registerValidateViewHandler(ValidateViewHandler handler);
	
	/***
	 * 取消注册刷新界面的handler
	 * @param handler
	 */
	public void removeValidateViewHandler(ValidateViewHandler handler);
}
