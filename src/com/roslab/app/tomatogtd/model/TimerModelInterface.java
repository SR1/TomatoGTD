package com.roslab.app.tomatogtd.model;

/***
 * 计时器模型
 * 
 * @author SR1
 */
public interface TimerModelInterface {

	/***
	 * 启动计时器
	 */
	public void startTimer();

	/***
	 * 停止计时器
	 */
	public void stopTimer();

	/***
	 * 获取剩余时间(毫秒)
	 * @return 剩余时间
	 */
	public long getRemainingTime();
	
	/***
	 * 返回计时器状态
	 * @return 计时器状态
	 */
	public boolean isStart();

	/***
	 * 设置计时器计时结束监听器
	 */
	public void setOnTimeUpListener(OnTimeUpListener listener);

	/***
	 * 计时器计时结束监听器
	 * @author SR1
	 */
	public interface OnTimeUpListener {
		/***
		 * 计时器计时结束时候调用
		 */
		public void onTimeUp();
	}
}
