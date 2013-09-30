package com.roslab.app.tomatogtd.fragment;

import java.util.ArrayList;

import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.adapter.TodaysTodoAdapter;
import com.roslab.app.tomatogtd.enity.TodaysTodoItem;
import com.roslab.app.tomatogtd.enity.TodoListState;
import com.roslab.app.tomatogtd.services.MainService;
import com.roslab.app.tomatogtd.view.UnderlinePageIndicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TodaysTodoFragment extends Fragment {

	public static final String TAG = "TodaysTodoFragment";
	
	/***
	 * ����״̬������
	 */
	MainService mService;
	TodoListState state;
	ArrayList<TodaysTodoItem> todolist;
	TodaysTodoAdapter todaysTodoAdapter;
	
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
	private void initializeComponent(View view){
		
		// ʵ�������
		vp = (ViewPager)view.findViewById(R.id.viewpager);
		mIndicator = (UnderlinePageIndicator)view.findViewById(R.id.indicator);
		timerText = (TextView)view.findViewById(R.id.todays_todo_timer);
		shield = (TextView)view.findViewById(R.id.todays_todo_shield);
		start = (TextView)view.findViewById(R.id.todays_todo_start_tomato_timer);
		giveup = (TextView)view.findViewById(R.id.todays_todo_giveup_tomato_timer);
		innerInterrupt = (TextView)view.findViewById(R.id.todays_todo_inner_interrupt);
		outterInterrupt = (TextView)view.findViewById(R.id.todays_todo_outter_interrupt);
	}
	
	/***
	 * ��ʼ������
	 */
	private void initializeData(){
		
		mService = MainService.getController(getActivity());
		if(mService!=null)
		Log.v(TAG, "initializeComponent-->get MainService");
		
		todolist = mService.getTodayTodsList();
		
		state = mService.getTodoListState();
	}
	
	/***
	 * �������ݳ�ʼ�����
	 */
	public void initializeComponentState(){
		vp.setAdapter(new TodaysTodoAdapter(getFragmentManager(), todolist));
		todaysTodoAdapter = new TodaysTodoAdapter(getFragmentManager(), todolist);
		mIndicator.setViewPager(vp);
		mIndicator.setFades(false);
	}
	/***
	 * �ָ�״̬��Ϣ
	 */
	public void resumeState(){
		vp.setCurrentItem(state.getCurrentPosition(), false);
	}
	
	/***
	 * �洢״̬��Ϣ
	 */
	public void saveState(){
		state.setCurrentPosition(vp.getCurrentItem());
		// ��Ϊstate��������MainService���У�
		// Activity��MainService����ͬһ��state
		// �������������ִ�в�ִ�ж�û�й�ϵ
		// mService.setTodoListState(state);
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
		resumeState();
		super.onResume();
		Log.v(TAG, "onResume-->");
	}

	@Override
	public void onPause() {
		saveState();
		super.onPause();
		Log.v(TAG, "onPause-->");
	}
	
}
