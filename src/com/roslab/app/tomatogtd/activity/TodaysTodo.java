package com.roslab.app.tomatogtd.activity;

import java.util.ArrayList;

import com.devspark.appmsg.AppMsg;
import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.adapter.TodaysTodoAdapter;
import com.roslab.app.tomatogtd.database.DatabaseHelper;
import com.roslab.app.tomatogtd.database.DatabaseOperator;
import com.roslab.app.tomatogtd.enity.TodaysTodoItem;
import com.roslab.app.tomatogtd.view.UnderlinePageIndicator;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.TextView;

public class TodaysTodo extends FragmentActivity implements OnClickListener,
		OnLongClickListener {

	public static final String TAG = "TodaysTodo";

	private ViewPager mViewPager;
	private UnderlinePageIndicator mIndicator;
	private TodaysTodoAdapter mAdapter;
	private TextView startTimer;
	private TextView giveupTimer;
	private TextView innerInterrupt;
	private TextView outterInterrupt;
	private TextView shield;
	private TextView timerText;
	private ArrayList<TodaysTodoItem> todaysTodoList;
	private int currentTimingItem;
	private DatabaseOperator database;
	
	/**
	 * 拦截返回键事件，使应用进入后台运行
	 */
	@Override
	public void onBackPressed() {
		moveTaskToBack(false);
	}

	/**
	 * 初始化控件
	 */
	private void initComm() {
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mIndicator = (UnderlinePageIndicator) findViewById(R.id.indicator);
		startTimer = (TextView) findViewById(R.id.todays_todo_start_tomato_timer);
		innerInterrupt = (TextView) findViewById(R.id.todays_todo_inner_interrupt);
		outterInterrupt = (TextView) findViewById(R.id.todays_todo_outter_interrupt);
		giveupTimer = (TextView) findViewById(R.id.todays_todo_giveup_tomato_timer);
		shield = (TextView) findViewById(R.id.todays_todo_shield);
		timerText = (TextView) findViewById(R.id.todays_todo_timer);
		
		database = new DatabaseOperator(this);
		Log.v(TAG, "initComm--->");
	}

	/**
	 *  为必要的控件设计监听器
	 */
	private void initListener() {
		innerInterrupt.setOnClickListener(this);
		outterInterrupt.setOnClickListener(this);
		startTimer.setOnLongClickListener(this);
		giveupTimer.setOnLongClickListener(this);
		innerInterrupt.setOnLongClickListener(this);
		outterInterrupt.setOnLongClickListener(this);

		Log.v(TAG, "initListener--->");
	}

	// 初始化待办列表
	protected void initTodaysTodoList() {
		// TODO
		todaysTodoList = database.queryTodaysTodoList();

		Log.v(TAG, "initTodaysTodoList--->");
	}

	// 初始化显示
	private void initViewPager() {
		mAdapter = new TodaysTodoAdapter(getSupportFragmentManager(),
				todaysTodoList);
		mViewPager.setAdapter(mAdapter);
		mIndicator.setViewPager(mViewPager);
		mIndicator.setFades(false);

		Log.v(TAG, "initViewPager--->");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initComm();
		initListener();
		initTodaysTodoList();
		Log.v(TAG, "onCreate-->");
	}

	@Override
	protected void onStart() {

		initViewPager();
		super.onStart();
		Log.v(TAG, "onStart-->");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.v(TAG, "onResume-->");
	}

	@Override
	protected void onPause() {
		Log.v(TAG, "onPause-->");
		super.onPause();
	}

	// implement of OnClickListener
	@Override
	public void onClick(View v) {

		AppMsg appMsg;
		TodaysTodoItem currentItem = todaysTodoList.get(mViewPager
				.getCurrentItem());

		switch (v.getId()) {
		case R.id.todays_todo_inner_interrupt:
			appMsg = AppMsg.makeText(this, "click todays_todo_inner_interrupt",
					AppMsg.STYLE_INFO);
			appMsg.show();
			currentItem.addInnerInterrupt();
			mAdapter.notifyDataSetChanged();
			break;
		case R.id.todays_todo_outter_interrupt:
			appMsg = AppMsg.makeText(this,
					"click todays_todo_outter_interrupt", AppMsg.STYLE_INFO);
			appMsg.show();
			currentItem.addOutterInterrupt();
			mAdapter.notifyDataSetChanged();
			break;
		}

		Log.v(TAG, "onClick-->");
	}

	// implement of OnLongClickListener
	@Override
	public boolean onLongClick(View v) {

		AppMsg appMsg;

		switch (v.getId()) {
		case R.id.todays_todo_start_tomato_timer:
			appMsg = AppMsg.makeText(this,
					getString(R.string.todays_todo_start_tomato_timer),
					AppMsg.STYLE_INFO);
			appMsg.show();
			break;
		case R.id.todays_todo_giveup_tomato_timer:
			appMsg = AppMsg.makeText(this,
					getString(R.string.todays_todo_giveup_tomato_timer_notice),
					AppMsg.STYLE_ALERT);
			appMsg.show();
			break;
		case R.id.todays_todo_inner_interrupt:
			appMsg = AppMsg
					.makeText(this, "long click todays_todo_inner_interrupt",
							AppMsg.STYLE_INFO);
			appMsg.show();
			//TODO
			database.insertSampleData();
			mAdapter.notifyDataSetChanged();
			break;
		case R.id.todays_todo_outter_interrupt:
			appMsg = AppMsg.makeText(this,
					"long click todays_todo_outter_interrupt",
					AppMsg.STYLE_INFO);
			appMsg.show();
			break;
		}

		Log.v(TAG, "onLongClick-->");
		return true;
	}
}
