package com.roslab.app.tomatogtd.controler;

import java.util.Date;

import android.R.bool;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.devspark.appmsg.AppMsg;
import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.enity.TodaysTodoItem;
import com.roslab.app.tomatogtd.tool.Tools;

public class Timer {
	
	public static final String TAG = "Timer";

	public interface OnTimerStateChangeListener {
		public void onTimerStart();

		public void onTimerStop();

		public void onTimeUp();
	}

	private OnTimerStateChangeListener onTimerStateChangeListener;
	private TodaysTodoItem item;
	private FragmentActivity activity;
	private TimerHandler mHandler;

	public Timer(FragmentActivity activity, TodaysTodoItem item,
			OnTimerStateChangeListener listener) {
		this.activity = activity;
		this.item = item;
		this.onTimerStateChangeListener = listener;
	}

	public void start() {
		if (mHandler == null)
			if (onTimerStateChangeListener != null)
				onTimerStateChangeListener.onTimerStart();
			mHandler = new TimerHandler(activity, item,
					onTimerStateChangeListener);
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

	class TimerHandler extends Handler {

		public static final String TAG = "TimerHandler";

		public static final int UPDATE_TIMER_VIEW = 1;
		public static final int STOP_TIMER = 2;
		public static final long TomatoTime = 1000 * 60 * 25;
		private TodaysTodoItem item;
		private FragmentActivity activity;
		private long startTime;
		private boolean isStart = false;
		private OnTimerStateChangeListener listener;

		public TimerHandler(FragmentActivity activity, TodaysTodoItem item,
				OnTimerStateChangeListener listener) {
			this.activity = activity;
			this.item = item;
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

		protected String getRemainTimeInMinutes() {
			long currentTime = new Date().getTime();
			long remainTime = startTime - currentTime + TomatoTime;
			return Tools.TransToMinute(remainTime);
		}

		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case UPDATE_TIMER_VIEW:
				String text = activity.getString(
						R.string.todays_todo_timer_remain_message,
						item.getTitle(), getRemainTimeInMinutes());
				AppMsg appMsg = AppMsg.makeText(activity, text,
						AppMsg.STYLE_CONFIRM);
				appMsg.show();

				if (isTimeUp()) {
					item.addTomatoDone();
					listener.onTimeUp();
					stop();
				} else {
					sendEmptyMessageDelayed(UPDATE_TIMER_VIEW,
							AppMsg.LENGTH_LONG);
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
