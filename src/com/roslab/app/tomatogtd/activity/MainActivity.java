package com.roslab.app.tomatogtd.activity;

import java.util.Date;

import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.database.DatabaseHelper;
import com.roslab.app.tomatogtd.database.DatabaseOperator;
import com.roslab.app.tomatogtd.tool.Tools;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class MainActivity extends ActivityGroup {

	private Button btnStart;
	private TextView btnCancel;
	private TextView texShow;
	private Handler mHandler;
	private Thread timer;
	private long startTime;
	private long limitTime;
	private long endTime;
	private boolean isTimerEnd;

	public static TabHost tab_host;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//初始化各变量
		texShow = (TextView)findViewById(R.id.showText);
		btnStart = (Button)findViewById(R.id.btnStart);
		btnCancel = (TextView)findViewById(R.id.btnCancel);
		
		//启动一个番茄 按钮响应
		btnStart.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Log.v("before start service","");
				Intent in = new Intent();
				in.setAction("com.roslab.app.tomatogtd.services.AlertService");
				startService(in);
				Log.v("after start service","");
				//隐藏按钮
				btnStart.setVisibility(View.GONE);
				//显示计时器
				showTimer();
			}
		});
		
		tab_host = (TabHost)findViewById(R.id.edit_item_tab_host);
		tab_host.setup(this.getLocalActivityManager());
		
		TabSpec ts1 = tab_host.newTabSpec("tomato");
		ts1.setIndicator("番茄");
		ts1.setContent(new Intent(this,TomatoActivity.class));
		tab_host.addTab(ts1);
		
		TabSpec ts2 = tab_host.newTabSpec("potato");
		ts2.setIndicator("土豆");
		ts2.setContent(new Intent(this,test.class));
		tab_host.addTab(ts2);
		
		TabSpec ts3 = tab_host.newTabSpec("Summary");
		ts3.setIndicator("统计");
		ts3.setContent(new Intent(this,test.class));
		tab_host.addTab(ts3);
		
		TabSpec ts4 = tab_host.newTabSpec("Note");
		ts4.setIndicator("记事");
		ts4.setContent(new Intent(this,test.class));
		tab_host.addTab(ts4);
		
		tab_host.setCurrentTab(0);
	}	//显示计时器
	private void showTimer()
	{
		//放弃这个番茄 按钮响应
		btnCancel.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {
				//创建确认框
				AlertDialog comfirm = new AlertDialog.Builder(MainActivity.this)
				.setTitle("番茄+").setMessage("真的要放弃这个番茄吗？")
				.setPositiveButton("放弃!", new DialogInterface.OnClickListener()
				{
					//确认放弃这个番茄 按钮响应
					public void onClick(DialogInterface dialog, int which)
					{
						//复位显示界面
						reset();
					}
				}).setNegativeButton("取消",null).create();
				//显示确认框
				comfirm.show();
			}
		});
		
		//显示计时器界面
		((LinearLayout)findViewById(R.id.TimerLayout)).setVisibility(View.VISIBLE);
		
		//启动计时器
		startTimer();
	}
	
	//复位显示界面
	private void reset()
	{
		//结束计时器线程
		isTimerEnd = true;
		//更新 已完成番茄 显示列表
	//TODO	updateFinishEvent();
		//隐藏计时器界面
		((LinearLayout)findViewById(R.id.TimerLayout)).setVisibility(View.GONE);
		//显示 开启一个番茄 按钮
		btnStart.setVisibility(View.VISIBLE);
	}
	
	//启动计时器
	private void startTimer()
	{
		startTime = (new Date()).getTime();
		limitTime = 25*60*1000;
		isTimerEnd = false;
		mHandler = new Handler();
		
		//创建计时器线程
		timer = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!isTimerEnd)
				{
					try{
						//通过Handler更新
						mHandler.post(new Runnable()
						{
							public void run()
							{
								//更新计时器显示
								updateTimer();
							}
						});
						Thread.sleep(1000);
					}catch(InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		});
		//启动线程
		timer.start();
	}
	
	//更新计时器显示
	private void updateTimer()
	{
		//计算番茄剩余时间
		long nowTime = new Date().getTime();
		long pastTime = nowTime - startTime;
		long remainTime = limitTime-pastTime;
		
		//判断番茄是否已结束
		if(remainTime>0)
			texShow.setText(Tools.TransToString(remainTime));
		else
		{
			isTimerEnd = true;
			showFinishDialog();

			Intent in = new Intent();
			in.setAction("com.roslab.app.service.AlertService");
			startService(in);
		}
	}
	
	//显示番茄完成对话框
	private void showFinishDialog()
	{
		final Dialog finish = new Dialog(this);
		finish.setContentView(R.layout.dialog_finish);
		finish.setTitle("番茄+");
		
		//输入任务描述有效时按钮处理
		((Button)finish.findViewById(R.id.finish_ok)).setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				String event = ((EditText)finish.findViewById(R.id.finish_message)).getText().toString();
				if(!(event.equals("")))
				{
					addNewEvent(event);
					finish.dismiss();
				}
				reset();
			}
		});
		
		((TextView)finish.findViewById(R.id.finish_cancel)).setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				reset();
				finish.dismiss();
			}
		});
		finish.show();
	}
	
	//添加新完成番茄进数据库
	private void addNewEvent(String event)
	{
		//番茄确切完成时间
		endTime = new Date().getTime();
		//构造ContentValues对象
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.FinalEvent_StartTime,startTime);
		values.put(DatabaseHelper.FinalEvent_EndTime,endTime);
		values.put(DatabaseHelper.FinalEvent_Event,event);
		//插入数据
		DatabaseOperator dbo = new DatabaseOperator(this);
		dbo.insertNewEvent(values);
	}
}
