package com.roslab.app.tomatogtd.fragment;

import java.util.ArrayList;

import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.activity.ValidateViewHandler;
import com.roslab.app.tomatogtd.activity.ValidateViewHandler.OnValidateListener;
import com.roslab.app.tomatogtd.adapter.TodaysTodoAdapter;
import com.roslab.app.tomatogtd.enity.TimerState;
import com.roslab.app.tomatogtd.enity.TodaysTodoItem;
import com.roslab.app.tomatogtd.enity.TodoListState;
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
	 * ����״̬������
	 */
	MainService mService;
	TodoListState todoListState;
	TimerState timerState;
	ArrayList<TodaysTodoItem> todolist;
	TodaysTodoAdapter todaysTodoAdapter;
	ValidateViewHandler handler;
	ScreenOnModel screenOn;

	/***
	 * �������
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
	 * ��ʼ�����������
	 */
	private void initializeComponent(View view) {
		// ʵ�������
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
	 * ��ʼ������
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
	 * ���´����б�����
	 * 
	 * @param isUpdateCurrentItem
	 *            �Ƿ���ʾ��ԭ����λ��
	 */
	private void updateTodoList(boolean isUpdateCurrentItem) {
		int current = 0;
		if (isUpdateCurrentItem)
			current = vp.getCurrentItem();
		todolist = mService.getTodayTodsList();
		todaysTodoAdapter = new TodaysTodoAdapter(getChildFragmentManager(),
				todolist);
		vp.setAdapter(todaysTodoAdapter);
		if (current > todolist.size())
			current = todolist.size();
		vp.setCurrentItem(current, false);
		Log.v(TAG, "updateTodoList-->" + todolist);
	}

	/***
	 * �������ݳ�ʼ�����
	 */
	public void initializeComponentState() {
		if (todolist.size() == 0) {
			getFragmentManager()
					.beginTransaction()
					.replace(R.id.content_frame,
							ChooseWhatTodoFragment.newInstance()).commit();
			return;
		}
		todaysTodoAdapter = new TodaysTodoAdapter(getFragmentManager(),
				todolist);
		vp.setAdapter(todaysTodoAdapter);
		mIndicator.setViewPager(vp);
		mIndicator.setFades(false);
	}

	/***
	 * �ָ�״̬��Ϣ
	 */
	public void resumeState() {
		vp.setCurrentItem(todoListState.getCurrentPosition(), false);
	}

	/***
	 * �洢״̬��Ϣ
	 */
	public void saveState() {
		todoListState.setCurrentPosition(vp.getCurrentItem());
		// ��Ϊstate��������MainService���У�
		// Activity��MainService����ͬһ��state
		// �������������ִ�в�ִ�ж�û�й�ϵ
		// mService.setTodoListState(todoListState);
		screenOn.off();
	}

	/***
	 * ��ʱˢ�½���
	 */
	public void startUpdateView() {
		if (handler == null)
			handler = new ValidateViewHandler(this);
		mService.registerValidateViewHandler(handler);
		handler.sendEmptyMessage(ValidateViewHandler.UPDATE_LOOP);
	}

	/***
	 * ֹͣˢ�½���
	 */
	public void stopUpdateView() {
		handler.removeMessages(ValidateViewHandler.UPDATE_LOOP);
		mService.removeValidateViewHandler(handler);
	}

	/***
	 * ��ʱ��ֹͣʱ�Ľ�����ʾ
	 */
	public void timerStopView() {
		shield.setVisibility(View.GONE);
		start.setVisibility(View.VISIBLE);
		giveup.setVisibility(View.GONE);
		timerText.setVisibility(View.GONE);

		screenOn.off();
	}

	/***
	 * ��ʱ������ʱ�Ľ�����ʾ
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
		Log.v(TAG, "onPause-->" + vp.getCurrentItem());
	}

	@Override
	public void onValidate() {
		if (todoListState == null)
			todoListState = mService.getTodoListState();
		if (todoListState.isTodoListChange()) {
			updateTodoList(true);
			todoListState.setTodoListChange(false);
		}
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
			mService.startTimer(todolist.get(vp.getCurrentItem()).getId());
			handler.sendEmptyMessage(ValidateViewHandler.UPDATE_NOW);
			break;

		case R.id.todays_todo_giveup_tomato_timer:
			mService.stopTimer();
			handler.sendEmptyMessage(ValidateViewHandler.UPDATE_NOW);
			break;
		case R.id.todays_todo_inner_interrupt:
			mService.addInnerInterrupt(todolist.get(vp.getCurrentItem())
					.getId());
			break;
		case R.id.todays_todo_outter_interrupt:
			mService.addOutterInterrupt(todolist.get(vp.getCurrentItem())
					.getId());
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
			mService.addInnerInterrupt(todolist.get(vp.getCurrentItem())
					.getId());
			break;
		case R.id.todays_todo_outter_interrupt:
			mService.addOutterInterrupt(todolist.get(vp.getCurrentItem())
					.getId());
			break;
		default:
			break;
		}
	}
}
