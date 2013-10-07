package com.roslab.app.tomatogtd.controler;

import java.util.ArrayList;

import com.roslab.app.tomatogtd.activity.ValidateViewHandler;
import com.roslab.app.tomatogtd.enity.AllTodosItem;
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
	public void startTimer(int todaysTodoId);
	
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

	/***
	 * ����ڲ��ж�
	 * @param listener ������ɺ��������������ķ���
	 */
	public void addInnerInterrupt(int todaysTodoId);
	
	/***
	 * ����ⲿ�ж�
	 * @param listener ������ɺ��������������ķ���
	 */
	public void addOutterInterrupt(int todaysTodoId);
	
	/***
	 * ��Ӵ�������
	 * @param subject �������������
	 * @param remark ��������ı�ע
	 * @return �����Ƿ�ɹ�
	 */
	public boolean addTodos(String subject, String remark);
	
	/***
	 * ��ȡ����δ��ɴ���
	 * @return ����δ��ɴ����б�����Ϊ0ʱ��ʾ�б��
	 */
	public ArrayList<AllTodosItem> getAllUnfinishTodos();
	
	/***
	 * ע��ˢ�½����handler
	 * @param handler
	 */
	public void registerValidateViewHandler(ValidateViewHandler handler);
	
	/***
	 * ȡ��ע��ˢ�½����handler
	 * @param handler
	 */
	public void removeValidateViewHandler(ValidateViewHandler handler);
}
