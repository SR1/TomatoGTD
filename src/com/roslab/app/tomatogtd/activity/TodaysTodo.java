package com.roslab.app.tomatogtd.activity;

import java.util.ArrayList;
import java.util.Date;

import com.devspark.appmsg.AppMsg;
import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.adapter.TodaysTodoAdapter;
import com.roslab.app.tomatogtd.enity.TodaysTodoItem;
import com.roslab.app.tomatogtd.services.AlertService;
import com.roslab.app.tomatogtd.tool.Tools;
import com.roslab.app.tomatogtd.view.CirclePageIndicator;
import com.roslab.app.tomatogtd.view.LinePageIndicator;
import com.roslab.app.tomatogtd.view.UnderlinePageIndicator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
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
	private TimerHandler mHandler;
	private TextView startTimer;
	private TextView giveupTimer;
	private TextView innerInterrupt;
	private TextView outterInterrupt;
	private PowerManager powerManager;
	private WakeLock wakeLock;
	private ArrayList<TodaysTodoItem> todaysTodoList;

	private void initComm() {
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mIndicator = (UnderlinePageIndicator) findViewById(R.id.indicator);
		startTimer = (TextView) findViewById(R.id.todays_todo_start_tomato_timer);
		innerInterrupt = (TextView) findViewById(R.id.todays_todo_inner_interrupt);
		outterInterrupt = (TextView) findViewById(R.id.todays_todo_outter_interrupt);
		giveupTimer = (TextView) findViewById(R.id.todays_todo_giveup_tomato_timer);
		// ÆÁÄ»³£ÁÁ
		this.powerManager = (PowerManager) this
				.getSystemService(Context.POWER_SERVICE);
		this.wakeLock = this.powerManager.newWakeLock(
				PowerManager.FULL_WAKE_LOCK, "My Lock");
		initListener();
		Log.v(TAG, "initComm--->");
	}

	// TODO
	private void initListener() {
		innerInterrupt.setOnClickListener(this);
		outterInterrupt.setOnClickListener(this);

		startTimer.setOnLongClickListener(this);
		giveupTimer.setOnLongClickListener(this);
		innerInterrupt.setOnLongClickListener(this);
		outterInterrupt.setOnLongClickListener(this);
		// set button viewsable state
		setStartButtonUsable(true);

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
		todaysTodoList.add(item);

		item = new TodaysTodoItem();
		item.setTitle("ÔÄ¶ÁÍø¹ÜËæ±ÊµÚÁù¡¢ÆßÕÂ");
		item.setStartTime("2013/09/09");
		item.setEndTime("----/--/--");
		item.setTomatoOne(2);
		item.setTomatoTwo(3);
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

	public void updateCurrentViewPager() {

		int currentPosition = mViewPager.getCurrentItem();
		initViewPager();
		mViewPager.setCurrentItem(currentPosition, false);

		Log.v(TAG, "updateCurrentViewPager & setCuttentItem--->");
	}

	public void setStartButtonUsable(boolean usable) {
		if (usable) {
			startTimer.setVisibility(View.VISIBLE);
			giveupTimer.setVisibility(View.GONE);
		} else {
			startTimer.setVisibility(View.GONE);
			giveupTimer.setVisibility(View.VISIBLE);
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
		Log.v(TAG, "onStart-->");
	}

	@Override
	protected void onResume() {
		super.onResume();
		initViewPager();
		Log.v(TAG, "onResume-->");
	}

	@Override
	protected void onPause() {
		this.wakeLock.release();
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
		TodaysTodoItem currentItem = todaysTodoList.get(mViewPager
				.getCurrentItem());

		switch (v.getId()) {
		case R.id.todays_todo_start_tomato_timer:
			appMsg = AppMsg.makeText(this,
					"click todays_todo_start_tomato_timer", AppMsg.STYLE_INFO);
			appMsg.show();
			if (mHandler == null) {
				mHandler = new TimerHandler(currentItem);
				mHandler.start();
			}
			setStartButtonUsable(false);
			break;
		case R.id.todays_todo_giveup_tomato_timer:
			appMsg = AppMsg
					.makeText(this, getString(R.string.todays_todo_giveup_tomato_timer_notice),
							AppMsg.STYLE_ALERT);
			appMsg.show();
			if (mHandler != null) {
				mHandler.sendEmptyMessage(TimerHandler.STOP_TIMER);
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

	class TimerHandler extends Handler implements Runnable {

		public static final int UPDATE_TIMER_VIEW = 1;
		public static final int STOP_TIMER = 2;
		public static final long TomatoTime = 1000 * 60 * 25;
		private TodaysTodoItem item;
		private long startTime;

		public TimerHandler(TodaysTodoItem item) {
			this.item = item;
		}

		public void start() {
			sendEmptyMessage(UPDATE_TIMER_VIEW);
			startTime = new Date().getTime();
		}

		protected boolean isTimeUp() {
			long currentTime = new Date().getTime();
			return currentTime > (startTime + TomatoTime);
		}

		protected String getRemainTimeInMinutes() {
			long currentTime = new Date().getTime();
			long remainTime = startTime - currentTime + TomatoTime;
			return Tools.TransToMinute(remainTime);
		}

		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case UPDATE_TIMER_VIEW:
				String text = getString(
						R.string.todays_todo_timer_remain_message,
						item.getTitle(), getRemainTimeInMinutes());
				AppMsg appMsg = AppMsg.makeText(TodaysTodo.this, text,
						AppMsg.STYLE_CONFIRM);
				appMsg.show();

				if (isTimeUp()) {
					TodaysTodo.this.wakeLock.release();
					item.addTomatoDone();
					mHandler = null;
				} else {
					sendEmptyMessageDelayed(UPDATE_TIMER_VIEW,
							AppMsg.LENGTH_LONG);
				}
				setStartButtonUsable(false);
				
				//TODO
				wakeLock.acquire();

				Log.v(TAG, "handleMessage-->UPDATE_TIMER_VIEW");
				break;
			case STOP_TIMER:
				removeMessages(UPDATE_TIMER_VIEW);
				setStartButtonUsable(true);
				//TODO
				wakeLock.release();
				mHandler = null;

				Log.v(TAG, "handleMessage-->STOP_TIMER");
				break;
			}
		}

		@Override
		public void run() {

		}
	}
}
