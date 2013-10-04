package com.roslab.app.tomatogtd.fragment;

import java.util.ArrayList;

import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.activity.ValidateViewHandler;
import com.roslab.app.tomatogtd.activity.ValidateViewHandler.OnValidateListener;
import com.roslab.app.tomatogtd.adapter.TodaysTodoAdapter;
import com.roslab.app.tomatogtd.enity.TimerState;
import com.roslab.app.tomatogtd.enity.TodaysTodoItem;
import com.roslab.app.tomatogtd.enity.TodoListState;
import com.roslab.app.tomatogtd.interfaces.OnOperationDoneListener;
import com.roslab.app.tomatogtd.model.ScreenOnModel;
import com.roslab.app.tomatogtd.services.MainService;
import com.roslab.app.tomatogtd.tool.Tools;
import com.roslab.app.tomatogtd.view.UnderlinePageIndicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TodaysTodoFragment extends Fragment implements OnValidateListener,
		OnLongClickListener, OnClickListener {

	public static final String TAG = "TodaysTodoFragment";

	/***
	 * 声明状态、数据
	 */
	MainService mService;
	TodoListState todoListState;
	TimerState timerState;
	ArrayList<TodaysTodoItem> todolist;
	TodaysTodoAdapter todaysTodoAdapter;
	ValidateViewHandler handler;
	ScreenOnModel screenOn;

	/***
	 * 声明组件
	 */
	ViewPager vp;
	UnderlinePageIndicator mIndicator;
	TextView timerText;
	TextView shield;
	TextView start;
	TextView giveup;
	TextView innerInterrupt;
	TextView outterInterrupt;

	/***
	 * 初始化、设置组件
	 */
	private void initializeComponent(View view) {

		// 实例化组件
		vp = (ViewPager) view.findViewById(R.id.viewpager);
		mIndicator = (UnderlinePageIndicator) view.findViewById(R.id.indicator);
		timerText = (TextView) view.findViewById(R.id.todays_todo_timer);
		shield = (TextView) view.findViewById(R.id.todays_todo_shield);
		start = (TextView) view
				.findViewById(R.id.todays_todo_start_tomato_timer);
		giveup = (TextView) view
				.findViewById(R.id.todays_todo_giveup_tomato_timer);
		innerInterrupt = (TextView) view
				.findViewById(R.id.todays_todo_inner_interrupt);
		outterInterrupt = (TextView) view
				.findViewById(R.id.todays_todo_outter_interrupt);

		innerInterrupt.setOnClickListener(this);
		outterInterrupt.setOnClickListener(this);

		start.setOnLongClickListener(this);
		giveup.setOnLongClickListener(this);
		innerInterrupt.setOnLongClickListener(this);
		outterInterrupt.setOnLongClickListener(this);

	}

	/***
	 * 初始化数据
	 */
	private void initializeData() {

		mService = MainService.getController(getActivity());
		if (mService != null)
			Log.v(TAG, "initializeComponent-->get MainService");

		todolist = mService.getTodayTodsList();

		todoListState = mService.getTodoListState();
		screenOn = new ScreenOnModel(getActivity());
	}
	
	/***
	 * 更新待办列表数据
	 * @param isUpdateCurrentItem 是否显示回原来的位置
	 */
	private void updateTodoList(boolean isUpdateCurrentItem){
		int current = 0;
		if(isUpdateCurrentItem)
			current = vp.getCurrentItem();
		todolist = mService.getTodayTodsList();
		todaysTodoAdapter = new TodaysTodoAdapter(
				getFragmentManager(), todolist);
		vp.setAdapter(todaysTodoAdapter);
		vp.setCurrentItem(current, false);	
	}

	/***
	 * 根据数据初始化组件
	 */
	public void initializeComponentState() {
		vp.setAdapter(new TodaysTodoAdapter(getFragmentManager(), todolist));
		todaysTodoAdapter = new TodaysTodoAdapter(getFragmentManager(),
				todolist);
		mIndicator.setViewPager(vp);
		mIndicator.setFades(false);
	}

	/***
	 * 恢复状态信息
	 */
	public void resumeState() {
		vp.setCurrentItem(todoListState.getCurrentPosition(), false);
	}

	/***
	 * 存储状态信息
	 */
	public void saveState() {
		todoListState.setCurrentPosition(vp.getCurrentItem());
		// 因为state的引用有MainService持有，
		// Activity和MainService共用同一个state
		// 因此下面这个语句执行不执行都没有关系
		// mService.setTodoListState(todoListState);
		screenOn.off();
	}

	/***
	 * 定时刷新界面
	 */
	public void startUpdateView() {
		handler = new ValidateViewHandler(this);
		handler.sendEmptyMessage(ValidateViewHandler.UPDATE_LOOP);
	}

	/***
	 * 停止刷新界面
	 */
	public void stopUpdateView() {
		handler.removeMessages(ValidateViewHandler.UPDATE_LOOP);
	}

	/***
	 * 计时器停止时的界面显示
	 */
	public void timerStopView() {
		shield.setVisibility(View.GONE);
		start.setVisibility(View.VISIBLE);
		giveup.setVisibility(View.GONE);
		timerText.setVisibility(View.GONE);

		screenOn.off();
	}

	/***
	 * 计时器运行时的界面显示
	 */
	public void timerRuningView() {
		shield.setVisibility(View.VISIBLE);
		start.setVisibility(View.GONE);
		giveup.setVisibility(View.VISIBLE);
		timerText.setVisibility(View.VISIBLE);
		timerText.setText(Tools.TransToString(timerState.getRemainTime()));

		screenOn.on();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_todays_todo, null);
		initializeComponent(layout);
		return layout;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initializeData();
		initializeComponentState();
	}

	@Override
	public void onResume() {
		startUpdateView();
		resumeState();
		super.onResume();
		Log.v(TAG, "onResume-->");
	}

	@Override
	public void onPause() {
		stopUpdateView();
		saveState();
		super.onPause();
		Log.v(TAG, "onPause-->");
	}

	@Override
	public void onValidate() {
		timerState = mService.getTimerState();
		if (timerState.isStart()) {
			timerRuningView();
		} else {
			timerStopView();
		}
	}

	@Override
	public boolean onLongClick(View v) {
		switch (v.getId()) {
		case R.id.todays_todo_start_tomato_timer:
			mService.startTimer();
			handler.sendEmptyMessage(ValidateViewHandler.UPDATE_NOW);
			break;

		case R.id.todays_todo_giveup_tomato_timer:
			mService.stopTimer();
			handler.sendEmptyMessage(ValidateViewHandler.UPDATE_NOW);
			break;
		case R.id.todays_todo_inner_interrupt:
			if (mService.getTimerState().isStart()) {
				mService.addInnerInterrupt(todolist.get(vp.getCurrentItem())
						.getId(), new OnOperationDoneListener() {
					@Override
					public void onOperationDone() {
						updateTodoList(true);
					}
				});
			} else {
				Toast.makeText(getActivity(),
						getActivity().getString(R.string.please_start_timer),
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.todays_todo_outter_interrupt:
			if (mService.getTimerState().isStart()) {
				mService.addOutterInterrupt(todolist.get(vp.getCurrentItem())
						.getId(), new OnOperationDoneListener() {
					@Override
					public void onOperationDone() {
						updateTodoList(true);
					}
				});
			} else {
				Toast.makeText(getActivity(),
						getActivity().getString(R.string.please_start_timer),
						Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.todays_todo_inner_interrupt:
			if (mService.getTimerState().isStart()) {
				mService.addInnerInterrupt(todolist.get(vp.getCurrentItem())
						.getId(), new OnOperationDoneListener() {
					@Override
					public void onOperationDone() {
						updateTodoList(true);
					}
				});
			} else {
				Toast.makeText(getActivity(),
						getActivity().getString(R.string.please_start_timer),
						Toast.LENGTH_SHORT).show();
			}
		case R.id.todays_todo_outter_interrupt:
			if (mService.getTimerState().isStart()) {
				mService.addOutterInterrupt(todolist.get(vp.getCurrentItem())
						.getId(), new OnOperationDoneListener() {
					@Override
					public void onOperationDone() {
						updateTodoList(true);
					}
				});
			} else {
				Toast.makeText(getActivity(),
						getActivity().getString(R.string.please_start_timer),
						Toast.LENGTH_SHORT).show();
			}
		default:
			break;
		}
	}
}
