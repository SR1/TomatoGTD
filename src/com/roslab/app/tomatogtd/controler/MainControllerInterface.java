package com.roslab.app.tomatogtd.controler;

import com.roslab.app.tomatogtd.enity.TimerState;

import android.app.Activity;

public interface MainControllerInterface {
	
	/**
	 * 获取控制器，如果控制器不存在
	 * 则用传入的Activity创建一个再返回
	 * @param activity 
	 * @return 控制器
	 */
	public MainControllerInterface getController(Activity activity);
	
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
}
