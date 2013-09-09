package com.roslab.app.tomatogtd.activity;

import java.util.ArrayList;

import com.devspark.appmsg.AppMsg;
import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.adapter.TodaysTodoAdapter;
import com.roslab.app.tomatogtd.enity.TodaysTodoItem;
import com.roslab.app.tomatogtd.view.CirclePageIndicator;
import com.roslab.app.tomatogtd.view.LinePageIndicator;
import com.roslab.app.tomatogtd.view.UnderlinePageIndicator;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TodaysTodo extends FragmentActivity implements OnClickListener,
		OnLongClickListener {

	public static final String TAG = "TodaysTodo";

	private ViewPager mViewPager;
	private UnderlinePageIndicator mIndicator;
	private TodaysTodoAdapter mAdapter;
	private TextView startTimer;
	private TextView innerInterrupt;
	private TextView outterInterrupt;
	private ArrayList<TodaysTodoItem> todaysTodoList;

	private void initComm() {
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mIndicator = (UnderlinePageIndicator) findViewById(R.id.indicator);
		startTimer = (TextView) findViewById(R.id.todays_todo_start_tomato_timer);
		innerInterrupt = (TextView) findViewById(R.id.todays_todo_inner_interrupt);
		outterInterrupt = (TextView) findViewById(R.id.todays_todo_outter_interrupt);

		initListener();
		Log.v(TAG, "initComm--->");
	}

	// TODO
	private void initListener() {
		startTimer.setOnClickListener(this);
		innerInterrupt.setOnClickListener(this);
		outterInterrupt.setOnClickListener(this);

		innerInterrupt.setOnLongClickListener(this);
		outterInterrupt.setOnLongClickListener(this);

		Log.v(TAG, "initListener--->");
	}

	protected void initTodaysTodoList() {
		// TODO
		todaysTodoList = new ArrayList<TodaysTodoItem>();
		TodaysTodoItem item;

		item = new TodaysTodoItem();
		item.setTitle("ÇÀ¿Î");
		item.setStartTime("2013/09/09");
		item.setEndTime("----/--/--");
		item.setTomatoOne(1);
		item.addInnerInterrupt();
		item.addInnerInterrupt();
		item.addOutterInterrupt();
		todaysTodoList.add(item);

		item = new TodaysTodoItem();
		item.setTitle("ÔÄ¶ÁÍø¹ÜËæ±ÊµÚÁù¡¢ÆßÕÂ");
		item.setStartTime("2013/09/09");
		item.setEndTime("----/--/--");
		item.setTomatoOne(2);
		item.setTomatoTwo(3);
		item.addOutterInterrupt();
		// item.addTomatoDone();
		// item.addTomatoDone();
		// item.addTomatoDone();
		todaysTodoList.add(item);

		Log.v(TAG, "initTodaysTodoList--->");
		initViewPager();
	}

	private void initViewPager() {
		mAdapter = new TodaysTodoAdapter(getSupportFragmentManager(),
				todaysTodoList);
		mViewPager.setAdapter(mAdapter);
		mIndicator.setViewPager(mViewPager);
		mIndicator.setFades(false);

		Log.v(TAG, "initViewPager--->");
	}

	private void initViewPager(int position) {
		initViewPager();
		mViewPager.setCurrentItem(position,false);
		
		Log.v(TAG, "initViewPager & setCuttentItem--->");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initComm();
		initTodaysTodoList();
		Log.v(TAG, "onCreate-->");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.v(TAG, "onStart-->");
	}

	@Override
	protected void onResume() {
		super.onResume();
		initViewPager();
		Log.v(TAG, "onResume-->");
	}

	// implement of OnClickListener
	@Override
	public void onClick(View v) {

		AppMsg appMsg;
		int positon;

		switch (v.getId()) {
		case R.id.todays_todo_start_tomato_timer:
			appMsg = AppMsg.makeText(this,
					"click todays_todo_start_tomato_timer", AppMsg.STYLE_INFO);
			appMsg.show();
			break;
		case R.id.todays_todo_inner_interrupt:
			appMsg = AppMsg.makeText(this, "click todays_todo_inner_interrupt",
					AppMsg.STYLE_INFO);
			appMsg.show();
			positon = mViewPager.getCurrentItem();
			todaysTodoList.get(positon).addInnerInterrupt();
			initViewPager(positon);
			break;
		case R.id.todays_todo_outter_interrupt:
			appMsg = AppMsg.makeText(this,
					"click todays_todo_outter_interrupt", AppMsg.STYLE_INFO);
			appMsg.show();
			positon = mViewPager.getCurrentItem();
			todaysTodoList.get(positon).addOutterInterrupt();
			initViewPager(positon);
			break;
		}

		Log.v(TAG, "onClick-->");
	}

	// implement of OnLongClickListener
	@Override
	public boolean onLongClick(View v) {

		AppMsg appMsg;

		switch (v.getId()) {
		case R.id.todays_todo_inner_interrupt:
			appMsg = AppMsg
					.makeText(this, "long click todays_todo_inner_interrupt",
							AppMsg.STYLE_INFO);
			appMsg.show();
			break;
		case R.id.todays_todo_outter_interrupt:
			appMsg = AppMsg.makeText(this,
					"long click todays_todo_outter_interrupt",
					AppMsg.STYLE_INFO);
			appMsg.show();
			break;
		}
		return true;
	}
}
