package com.roslab.app.tomatogtd.activity;

import java.util.ArrayList;

import com.devspark.appmsg.AppMsg;
import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.adapter.TodaysTodoAdapter;
import com.roslab.app.tomatogtd.controler.Timer;
import com.roslab.app.tomatogtd.controler.Timer.OnTimerStateChangeListener;
import com.roslab.app.tomatogtd.enity.TodaysTodoItem;
import com.roslab.app.tomatogtd.services.AlertService;
import com.roslab.app.tomatogtd.view.UnderlinePageIndicator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.TextView;

public class TodaysTodo extends FragmentActivity implements OnClickListener,
		OnLongClickListener, OnTimerStateChangeListener {

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
	private PowerManager powerManager;
	private WakeLock wakeLock;
	private Timer timer;
	private ArrayList<TodaysTodoItem> todaysTodoList;
	private int currentTimingItem;

	private void initComm() {
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mIndicator = (UnderlinePageIndicator) findViewById(R.id.indicator);
		startTimer = (TextView) findViewById(R.id.todays_todo_start_tomato_timer);
		innerInterrupt = (TextView) findViewById(R.id.todays_todo_inner_interrupt);
		outterInterrupt = (TextView) findViewById(R.id.todays_todo_outter_interrupt);
		giveupTimer = (TextView) findViewById(R.id.todays_todo_giveup_tomato_timer);
		shield = (TextView) findViewById(R.id.todays_todo_shield);
		timerText = (TextView) findViewById(R.id.todays_todo_timer);
		// ÆÁÄ»³£ÁÁ¿ØÖÆ
		this.powerManager = (PowerManager) this
				.getSystemService(Context.POWER_SERVICE);
		Log.v(TAG, "initComm--->");
		this.wakeLock = this.powerManager.newWakeLock(
				PowerManager.FULL_WAKE_LOCK, "My Lock");
		this.wakeLock.setReferenceCounted(false);
		Log.v(TAG, "initComm--->");
		initListener();
	}

	// TODO
	private void initListener() {
		innerInterrupt.setOnClickListener(this);
		outterInterrupt.setOnClickListener(this);

		startTimer.setOnLongClickListener(this);
		giveupTimer.setOnLongClickListener(this);
		innerInterrupt.setOnLongClickListener(this);
		outterInterrupt.setOnLongClickListener(this);
		setStartButtonUsable(true);

		Log.v(TAG, "initListener--->");
	}

	protected void initTodaysTodoList() {
		// TODO
		todaysTodoList = new ArrayList<TodaysTodoItem>();
		TodaysTodoItem item;

		item = new TodaysTodoItem();
		item.setTitle("Ï´ÒÂ·þ");
		item.setStartTime("2013/09/10");
		item.setEndTime("2013/09/10");
		item.setTomatoOne(1);
		todaysTodoList.add(item);

		item = new TodaysTodoItem();
		item.setTitle("ÔÄ¶ÁÍø¹ÜËæ±ÊµÚÁù¡¢ÆßÕÂ");
		item.setStartTime("2013/09/09");
		item.setEndTime("2013/09/10");
		item.setTomatoOne(2);
		item.setTomatoTwo(3);
		todaysTodoList.add(item);

		Log.v(TAG, "initTodaysTodoList--->");
		//initViewPager();
	}

	private void initViewPager() {
		mAdapter = new TodaysTodoAdapter(getSupportFragmentManager(),
				todaysTodoList);
		mViewPager.setAdapter(mAdapter);
		mIndicator.setViewPager(mViewPager);
		mIndicator.setFades(false);

		Log.v(TAG, "initViewPager--->");
	}

	public void updateCurrentViewPager() {

		mAdapter.notifyDataSetChanged();
//		int currentPosition = 1;
//		if(mViewPager.getAdapter()!=null)
//			currentPosition = mViewPager.getCurrentItem();
//		initViewPager();
//		mViewPager.setCurrentItem(currentPosition, false);

		Log.v(TAG, "updateCurrentViewPager & setCuttentItem--->");
	}

	public void setStartButtonUsable(boolean usable) {
		if (usable) {
			startTimer.setVisibility(View.VISIBLE);
			giveupTimer.setVisibility(View.GONE);
			shield.setVisibility(View.GONE);
		} else {
			startTimer.setVisibility(View.GONE);
			giveupTimer.setVisibility(View.VISIBLE);
			shield.setVisibility(View.VISIBLE);
		}
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
		if (timer != null) {
			if (timer.isStart())
				this.wakeLock.acquire();
		}
		this.wakeLock.release();
		initViewPager();
		Log.v(TAG, "onStart-->");
	}

	@Override
	protected void onResume() {
		super.onResume();
		mAdapter.notifyDataSetChanged();
		Log.v(TAG, "onResume-->");
	}

	@Override
	protected void onPause() {
		this.wakeLock.release();
		Log.v(TAG, "onPause-->");
		super.onPause();
	}

	private void launchTictacSoundService() {
		Intent intent = new Intent(this, AlertService.class);
		startService(intent);
		
	}

	private void stopTictacSoundService() {
		Intent intent = new Intent(this, AlertService.class);
		stopService(intent);
		
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
			updateCurrentViewPager();
			break;
		case R.id.todays_todo_outter_interrupt:
			appMsg = AppMsg.makeText(this,
					"click todays_todo_outter_interrupt", AppMsg.STYLE_INFO);
			appMsg.show();
			currentItem.addOutterInterrupt();
			updateCurrentViewPager();
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
					getString(R.string.todays_todo_start_tomato_timer), AppMsg.STYLE_INFO);
			appMsg.show();
			if (timer == null) {
				timer = new Timer(this);
			}
			timer.start();
			break;
		case R.id.todays_todo_giveup_tomato_timer:
			appMsg = AppMsg.makeText(this,
					getString(R.string.todays_todo_giveup_tomato_timer_notice),
					AppMsg.STYLE_ALERT);
			appMsg.show();
			if (timer != null) {
				timer.stop();
				timer = null;
			}
			break;
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

		Log.v(TAG, "onLongClick-->");
		return true;
	}

	@Override
	public void onTimerStart() {
		currentTimingItem = mViewPager.getCurrentItem();
		launchTictacSoundService();
		this.wakeLock.acquire();
		setStartButtonUsable(false);
		Log.v(TAG, "onTimerStart-->");
	}

	@Override
	public void onTimerStop() {
		stopTictacSoundService();
		this.wakeLock.release();
		setStartButtonUsable(true);
		timerText.setVisibility(View.GONE);
		Log.v(TAG, "onTimerStop-->");
	}

	@Override
	public void onTimeUp() {
		todaysTodoList.get(currentTimingItem).addTomatoDone();
		stopTictacSoundService();
		updateCurrentViewPager();
		setStartButtonUsable(true);
		timerText.setVisibility(View.GONE);
		Log.v(TAG, "onTimeUp-->");
	}

	@Override
	public void onTimerLoop() {
		String text = getString(
				R.string.todays_todo_timer_remain_message,
				todaysTodoList.get(currentTimingItem).getTitle(), timer.getRemainTimeInMinutes());
		timerText.setText(text);
		timerText.setVisibility(View.VISIBLE);

		Log.v(TAG, "onTimerLoop-->");
	}
}
