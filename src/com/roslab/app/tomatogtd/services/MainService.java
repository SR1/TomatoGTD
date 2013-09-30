package com.roslab.app.tomatogtd.services;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;
import android.util.Log;

import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.controler.MainControllerInterface;
import com.roslab.app.tomatogtd.database.DatabaseOperator;
import com.roslab.app.tomatogtd.enity.TimerState;
import com.roslab.app.tomatogtd.enity.TodaysTodoItem;
import com.roslab.app.tomatogtd.enity.TodoListState;
import com.roslab.app.tomatogtd.model.Timer;
import com.roslab.app.tomatogtd.model.TimerModelInterface.OnTimeUpListener;

public class MainService extends Service implements MainControllerInterface,
		OnTimeUpListener {

	public static final String TAG = "MainService";

	private static MainService mainService = null;
	private TodoListState todoListState;
	private Timer timer;

	ArrayList<OnTimeUpListener> notifyList = new ArrayList<OnTimeUpListener>();

	public static MainService getController(Activity activity) {
		if (mainService != null)
			return mainService;
		Intent intent = new Intent(activity, MainService.class);
		activity.startService(intent);
		return mainService;
	}

	public MainService() {
		super();
		mainService = this;
		initial();
		Log.v(TAG, "MainService-->");
	}

	/***
	 * 初始化各种状态
	 */
	protected void initial() {
		todoListState = new TodoListState();
		todoListState.setCurrentPosition(0);
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public TimerState getTimerState() {
		TimerState state = new TimerState();
		state.setStart(false);
		state.setRemainTime(0);
		if (timer != null) {
			state.setStart(timer.isStart());
			state.setRemainTime(timer.getRemainingTime());
		}
		return state;
	}

	@Override
	public void startTimer() {
		if (timer == null) {
			timer = new Timer();
			timer.setOnTimeUpListener(this);
		}
		timer.startTimer();
	}

	@Override
	public void stopTimer() {
		if (timer != null)
			timer.stopTimer();
	}

	@Override
	public void setTodoListState(TodoListState state) {
		todoListState = state;
	}

	@Override
	public TodoListState getTodoListState() {
		return todoListState;
	}

	@Override
	public ArrayList<TodaysTodoItem> getTodayTodsList() {
		DatabaseOperator dbo = new DatabaseOperator(this);
		return dbo.queryTodaysTodoList();
	}

	@Override
	public void onTimeUp() {
		MediaPlayer mp = MediaPlayer.create(this, R.raw.chime);
		mp.setLooping(false);
		mp.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.release();
			}
		});
		mp.start();
	}
}
