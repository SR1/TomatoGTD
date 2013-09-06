package com.roslab.app.tomatogtd.activity;

import java.util.ArrayList;

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
import android.widget.ProgressBar;
import android.widget.TextView;

public class TodaysTodo extends FragmentActivity {

	static String TAG = "TodaysTodo";
	
	ViewPager mViewPager;
	UnderlinePageIndicator mIndicator;
	
	protected void initComm()
	{
		mViewPager = (ViewPager)findViewById(R.id.viewpager);
		mIndicator = (UnderlinePageIndicator)findViewById(R.id.indicator);
	}
	
	protected void initData()
	{
		ArrayList<TodaysTodoItem> todaysTodoList= new ArrayList<TodaysTodoItem>();
		TodaysTodoItem item = new TodaysTodoItem();
		item.setTitle("社团APP优化");
		item.setStartTime("2013/09/06");
		item.setEndTime("----/--/--");
		todaysTodoList.add(item);
		item = new TodaysTodoItem();
		item.setTitle("阅读网管随笔第五章");
		item.setStartTime("2013/09/06");
		item.setEndTime("----/--/--");
		todaysTodoList.add(item);
		item = new TodaysTodoItem();
		item.setTitle("阅读网管随笔第四章");
		item.setStartTime("2013/09/06");
		item.setEndTime("----/--/--");
		todaysTodoList.add(item);
		item = new TodaysTodoItem();
		item.setTitle("使用ViewPager完成卡片设计");
		item.setStartTime("2013/09/06");
		item.setEndTime("--/--/--");
		item.setRemark("记得早点睡觉！");
		todaysTodoList.add(item);
		mViewPager.setAdapter(new TodaysTodoAdapter(getSupportFragmentManager(),todaysTodoList));
		mIndicator.setViewPager(mViewPager);
		mIndicator.setFades(false);
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
	
	
}
