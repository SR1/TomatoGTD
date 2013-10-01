package com.roslab.app.tomatogtd.controler;

import java.util.ArrayList;

import com.roslab.app.tomatogtd.enity.TimerState;
import com.roslab.app.tomatogtd.enity.TodaysTodoItem;
import com.roslab.app.tomatogtd.enity.TodoListState;

/***
 * ������������ӿ�
 * @author SR1
 */
public interface MainControllerInterface {
	
	/**
	 * ��ȡ��ʱ��״̬
	 * @return ��ʾ��ʱ��״̬��һ��ʵ��
	 */
	public TimerState getTimerState();

	/**
	 * ������ʱ��
	 */
	public void startTimer();
	
	/**
	 * ֹͣ��ʱ��
	 */
	public void stopTimer();
	
	/***
	 * ���ô����б�״̬
	 * @param state �����б�״̬����
	 */
	public void setTodoListState(TodoListState state);
	
	/***
	 * ��ȡ�����б�״̬ 
	 * @return �����б�״̬����
	 */
	public TodoListState getTodoListState();
	
	/***
	 * ��ȡ���մ����б�
	 * @return ���մ����б�
	 */
	public ArrayList<TodaysTodoItem> getTodayTodsList();
}
