package com.roslab.app.tomatogtd.services;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.roslab.app.tomatogtd.controler.MainControllerInterface;
import com.roslab.app.tomatogtd.database.DatabaseOperator;
import com.roslab.app.tomatogtd.enity.TimerState;
import com.roslab.app.tomatogtd.enity.TodaysTodoItem;
import com.roslab.app.tomatogtd.enity.TodoListState;

public class MainService extends Service implements MainControllerInterface {

	public static final String TAG = "MainService";

	private static MainService mainService = null;
	private TodoListState todoListState;

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
		return null;
	}

	@Override
	public void startTimer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopTimer() {
		// TODO Auto-generated method stub

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
}
