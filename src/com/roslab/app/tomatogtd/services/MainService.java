package com.roslab.app.tomatogtd.services;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.activity.ValidateViewHandler;
import com.roslab.app.tomatogtd.controler.MainControllerInterface;
import com.roslab.app.tomatogtd.database.DatabaseOperator;
import com.roslab.app.tomatogtd.enity.AllTodosItem;
import com.roslab.app.tomatogtd.enity.TimerState;
import com.roslab.app.tomatogtd.enity.TodaysTodoItem;
import com.roslab.app.tomatogtd.enity.TodoListState;
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
	private TimerState state;
	private ValidateViewHandler handler;

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
		resetTimerState();
		media = new MediaModel(this);
	}

	/***
	 * 重置计时器状态对象
	 */
	private void resetTimerState() {
		state = new TimerState();
		state.setRemainTime(0);
		state.setRunningTodaysTodoId(-1);
		state.setStart(false);
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
		if (state == null)
			resetTimerState();
		if (timer != null) {
			state.setStart(timer.isStart());
			state.setRemainTime(timer.getRemainingTime());
		}
		return state;
	}

	@Override
	public void startTimer(int todaysTodoId) {
		state.setRunningTodaysTodoId(todaysTodoId);
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
			DatabaseOperator databaseOperator = new DatabaseOperator(this);
			if (state != null) {
				databaseOperator.addTodaysTodoFinishNumber(state
						.getRunningTodaysTodoId());
				todoListState.setTodoListChange(true);
				Log.v(TAG, "onTimeUp-->" + state.getRunningTodaysTodoId());
			}
		}
		isGiveUpTimer = false;
		resetTimerState();
	}

	@Override
	public void addInnerInterrupt(int todaysTodoId) {
		if (!getTimerState().isStart()) {
			Toast.makeText(this, getString(R.string.please_start_timer),
					Toast.LENGTH_SHORT).show();
			return;
		}
		DatabaseOperator databaseOperator = new DatabaseOperator(this);
		databaseOperator.addInnerInterrupt(todaysTodoId);
		todoListState.setTodoListChange(true);
		if (handler != null)
			handler.sendEmptyMessage(ValidateViewHandler.UPDATE_NOW);
	}

	@Override
	public void addOutterInterrupt(int todaysTodoId) {
		if (!getTimerState().isStart()) {
			Toast.makeText(this, getString(R.string.please_start_timer),
					Toast.LENGTH_SHORT).show();
			return;
		}
		DatabaseOperator databaseOperator = new DatabaseOperator(this);
		databaseOperator.addOutterInterrupt(todaysTodoId);
		todoListState.setTodoListChange(true);
		if (handler != null)
			handler.sendEmptyMessage(ValidateViewHandler.UPDATE_NOW);
	}

	@Override
	public void registerValidateViewHandler(ValidateViewHandler handler) {
		this.handler = handler;
	}

	@Override
	public void removeValidateViewHandler(ValidateViewHandler handler) {
		this.handler = null;
	}

	@Override
	public boolean addTodos(String subject, String remark) {
		if (subject == null || "".equals(subject)) {
			Toast.makeText(this,
					getResources().getString(R.string.please_enter_subject),
					Toast.LENGTH_SHORT).show();
			return false;
		}
		DatabaseOperator databaseOperator = new DatabaseOperator(this);
		return databaseOperator.addTodo(subject, remark, 0);
	}

	@Override
	public ArrayList<AllTodosItem> getAllUnfinishTodos() {
		DatabaseOperator databaseOperator = new DatabaseOperator(this);
		return databaseOperator.getAllUndoneTodos();
	}
}
