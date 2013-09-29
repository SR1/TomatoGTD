package com.roslab.app.tomatogtd.controler;

import com.roslab.app.tomatogtd.enity.TimerState;

import android.app.Activity;

public interface MainControllerInterface {
	
	/**
	 * ��ȡ�����������������������
	 * ���ô����Activity����һ���ٷ���
	 * @param activity 
	 * @return ������
	 */
	public MainControllerInterface getController(Activity activity);
	
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
}
