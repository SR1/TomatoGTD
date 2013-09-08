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

	ViewPager mViewPager;
	UnderlinePageIndicator mIndicator;
	TextView startTimer;
	TextView innerInterrupt;
	TextView outterInterrupt;

	private void initComm() {
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mIndicator = (UnderlinePageIndicator) findViewById(R.id.indicator);
		startTimer = (TextView) findViewById(R.id.todays_todo_start_tomato_timer);
		innerInterrupt = (TextView) findViewById(R.id.todays_todo_inner_interrupt);
		outterInterrupt = (TextView) findViewById(R.id.todays_todo_outter_interrupt);

		initListener();
		Log.v(TAG, "on initComm--->");
	}

	// TODO
	private void initListener() {
		startTimer.setOnClickListener(this);
		innerInterrupt.setOnClickListener(this);
		outterInterrupt.setOnClickListener(this);

		Log.v(TAG, "on initListener--->");
	}

	protected void initData() {
		// TODO
		ArrayList<TodaysTodoItem> todaysTodoList = new ArrayList<TodaysTodoItem>();
		TodaysTodoItem item;

		item = new TodaysTodoItem();
		item.setTitle("ÇÀ¿Î");
		item.setStartTime("2013/09/09");
		item.setEndTime("----/--/--");
		item.setTomatoOne(1);
		todaysTodoList.add(item);

		item = new TodaysTodoItem();
		item.setTitle("ÔÄ¶ÁÍø¹ÜËæ±ÊµÚÁù¡¢ÆßÕÂ");
		item.setStartTime("2013/09/09");
		item.setEndTime("----/--/--");
		item.setTomatoOne(2);
		item.setTomatoTwo(3);
//		item.addTomatoDone();
//		item.addTomatoDone();
//		item.addTomatoDone();
		todaysTodoList.add(item);

		mViewPager.setAdapter(new TodaysTodoAdapter(
				getSupportFragmentManager(), todaysTodoList));
		mIndicator.setViewPager(mViewPager);
		mIndicator.setFades(false);
		
		Log.v(TAG, "on initData--->");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initComm();
		initData();
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
		initData();
		Log.v(TAG, "onResume-->");
	}

	// implement of OnClickListener
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.todays_todo_start_tomato_timer:
			AppMsg appMsg = AppMsg.makeText(this, "click todays_todo_start_tomato_timer",AppMsg.STYLE_INFO);
			appMsg.show();
			break;
		case R.id.todays_todo_inner_interrupt:
			break;
		case R.id.todays_todo_outter_interrupt:
			break;
		}

		Log.v(TAG, "onClick-->");
	}

	// implement of OnLongClickListener
	@Override
	public boolean onLongClick(View v) {
		switch (v.getId()) {
		case R.id.todays_todo_inner_interrupt:
			break;
		case R.id.todays_todo_outter_interrupt:
			break;
		}
		return false;
	}
}
