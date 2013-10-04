package com.roslab.app.tomatogtd.services;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.controler.MainControllerInterface;
import com.roslab.app.tomatogtd.database.DatabaseOperator;
import com.roslab.app.tomatogtd.enity.TimerState;
import com.roslab.app.tomatogtd.enity.TodaysTodoItem;
import com.roslab.app.tomatogtd.enity.TodoListState;
import com.roslab.app.tomatogtd.interfaces.OnOperationDoneListener;
import com.roslab.app.tomatogtd.model.MediaModel;
import com.roslab.app.tomatogtd.model.Timer;
import com.roslab.app.tomatogtd.model.TimerModelInterface.OnTimeUpListener;

public class MainService extends Service implements MainControllerInterface,
		OnTimeUpListener {

	public static final String TAG = "MainService";

	private static MainService mainService = null;
	private TodoListState todoListState;
	private Timer timer;
	private boolean isGiveUpTimer = false;
	private MediaModel media;

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
		media = new MediaModel(this);
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
			timer.startTimer();
			media.playTicTac();
		}
	}

	@Override
	public void stopTimer() {
		if (timer != null) {
			isGiveUpTimer = true;
			timer.stopTimer();
			timer = null;
		}
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
		DatabaseOperator databaseOperator = new DatabaseOperator(this);
		return databaseOperator.getTodaysTodoList();
	}

	@Override
	public void onTimeUp() {
		media.stopPlayTicTac();
		timer = null;
		if (!isGiveUpTimer) {
			media.playChime();
			isGiveUpTimer = false;
		}
	}

	@Override
	public void addInnerInterrupt(int todaysTodoId,
			OnOperationDoneListener listener) {
		if (!getTimerState().isStart()) {
			Toast.makeText(this, getString(R.string.please_start_timer),
					Toast.LENGTH_SHORT).show();
			return;
		}
		DatabaseOperator databaseOperator = new DatabaseOperator(this);
		databaseOperator.addInnerInterrupt(todaysTodoId);
		if (listener != null)
			listener.onOperationDone();
	}

	@Override
	public void addOutterInterrupt(int todaysTodoId,
			OnOperationDoneListener listener) {
		if (!getTimerState().isStart()) {
			Toast.makeText(this, getString(R.string.please_start_timer),
					Toast.LENGTH_SHORT).show();
			return;
		}
		DatabaseOperator databaseOperator = new DatabaseOperator(this);
		databaseOperator.addOutterInterrupt(todaysTodoId);
		if (listener != null)
			listener.onOperationDone();
	}
}
