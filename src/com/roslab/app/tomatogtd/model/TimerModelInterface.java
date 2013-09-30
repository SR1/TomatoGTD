package com.roslab.app.tomatogtd.model;

/***
 * ��ʱ��ģ��
 * 
 * @author SR1
 */
public interface TimerModelInterface {

	/***
	 * ������ʱ��
	 */
	public void startTimer();

	/***
	 * ֹͣ��ʱ��
	 */
	public void stopTimer();

	/***
	 * ��ȡʣ��ʱ��(����)
	 * @return ʣ��ʱ��
	 */
	public long getRemainingTime();
	
	/***
	 * ���ؼ�ʱ��״̬
	 * @return ��ʱ��״̬
	 */
	public boolean isStart();

	/***
	 * ���ü�ʱ����ʱ����������
	 */
	public void setOnTimeUpListener(OnTimeUpListener listener);

	/***
	 * ��ʱ����ʱ����������
	 * @author SR1
	 */
	public interface OnTimeUpListener {
		/***
		 * ��ʱ����ʱ����ʱ�����
		 */
		public void onTimeUp();
	}
}
