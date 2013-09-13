package com.roslab.app.tomatogtd.controler;

import java.util.Date;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.roslab.app.tomatogtd.tool.Tools;

public class Timer {
	
	public static final String TAG = "Timer";

	public interface OnTimerStateChangeListener {
		/**
		 * 计时器启动时执行
		 */
		public void onTimerStart();
		/**
		 * 计时器中断时执行
		 */
		public void onTimerStop();
		/**
		 * 计时器启动时，每一次循环执行
		 */
		public void onTimerLoop();
		/**
		 * 计时器计时完成时执行
		 */
		public void onTimeUp();
	}

	private OnTimerStateChangeListener onTimerStateChangeListener;
	private TimerHandler mHandler;

	public Timer(OnTimerStateChangeListener listener) {
		this.onTimerStateChangeListener = listener;
	}

	public void start() {
		if (mHandler == null)
			if (onTimerStateChangeListener != null)
				onTimerStateChangeListener.onTimerStart();
			mHandler = new TimerHandler(onTimerStateChangeListener);
		if (!mHandler.isStart()) {
			mHandler.start();
		}
		Log.v(TAG, "start-->");
	}

	public boolean isStart() {
		Log.v(TAG, "isStart-->");
		if(mHandler!=null)
			return mHandler.isStart();
		return false;
	}

	public void stop() {
		if (onTimerStateChangeListener != null)
			onTimerStateChangeListener.onTimerStop();
		if (mHandler != null) {
			if (mHandler.isStart())
				mHandler.stop();
			mHandler = null;
		}
		Log.v(TAG, "stop-->");
	}

	public OnTimerStateChangeListener getOnTimerStateChangeListener() {
		Log.v(TAG, "getOnTimerStateChangeListener-->");
		return onTimerStateChangeListener;
	}

	public void setOnTimerStateChangeListener(
			OnTimerStateChangeListener onTimerStateChangeListener) {
		this.onTimerStateChangeListener = onTimerStateChangeListener;
		Log.v(TAG, "setOnTimerStateChangeListener-->");
	}

	public String getRemainTimeInMinutes() {
		if(mHandler!=null && mHandler.isStart())
			return mHandler.getRemainTimeInSecond();
		return "";
	}

	class TimerHandler extends Handler {

		public static final String TAG = "TimerHandler";

		public static final int UPDATE_TIMER_VIEW = 1;
		public static final int STOP_TIMER = 2;
		public static final long TomatoTime = 1000 * 60 * 5;
		// public static final long TomatoTime = 1000 * 60 * 25;
		private long startTime;
		private boolean isStart = false;
		private OnTimerStateChangeListener listener;

		public TimerHandler(OnTimerStateChangeListener listener) {
			this.listener = listener;
		}

		public void start() {
			sendEmptyMessage(UPDATE_TIMER_VIEW);
			startTime = new Date().getTime();
			isStart = true;
			Log.v(TAG, "start-->");
		}

		public void stop() {
			removeMessages(UPDATE_TIMER_VIEW);
			isStart = false;
			Log.v(TAG, "stop-->");
		}

		protected boolean isTimeUp() {
			Log.v(TAG, "isTimeUp-->");
			long currentTime = new Date().getTime();
			return currentTime > (startTime + TomatoTime);
		}

		public String getRemainTimeInSecond() {
			long currentTime = new Date().getTime();
			long remainTime = startTime - currentTime + TomatoTime;
			return Tools.TransToString(remainTime);
		}

		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case UPDATE_TIMER_VIEW:

				if (isTimeUp()) {
					listener.onTimeUp();
					stop();
				} else {
					if(listener!=null)
						listener.onTimerLoop();
					sendEmptyMessageDelayed(UPDATE_TIMER_VIEW,
							100);
				}
				Log.v(TAG, "handleMessage-->UPDATE_TIMER_VIEW");
				break;
			}
		}

		public boolean isStart() {
			Log.v(TAG, "isStart-->");
			return isStart;
		}
	}
}
