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
		
		//��ʼ��������
		texShow = (TextView)findViewById(R.id.showText);
		btnStart = (Button)findViewById(R.id.btnStart);
		btnCancel = (TextView)findViewById(R.id.btnCancel);
		
		//����һ������ ��ť��Ӧ
		btnStart.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Log.v("before start service","");
				Intent in = new Intent();
				in.setAction("com.roslab.app.tomatogtd.services.AlertService");
				startService(in);
				Log.v("after start service","");
				//���ذ�ť
				btnStart.setVisibility(View.GONE);
				//��ʾ��ʱ��
				showTimer();
			}
		});
		
		tab_host = (TabHost)findViewById(R.id.edit_item_tab_host);
		tab_host.setup(this.getLocalActivityManager());
		
		TabSpec ts1 = tab_host.newTabSpec("tomato");
		ts1.setIndicator("����");
		ts1.setContent(new Intent(this,TomatoActivity.class));
		tab_host.addTab(ts1);
		
		TabSpec ts2 = tab_host.newTabSpec("potato");
		ts2.setIndicator("����");
		ts2.setContent(new Intent(this,test.class));
		tab_host.addTab(ts2);
		
		TabSpec ts3 = tab_host.newTabSpec("Summary");
		ts3.setIndicator("ͳ��");
		ts3.setContent(new Intent(this,test.class));
		tab_host.addTab(ts3);
		
		TabSpec ts4 = tab_host.newTabSpec("Note");
		ts4.setIndicator("����");
		ts4.setContent(new Intent(this,test.class));
		tab_host.addTab(ts4);
		
		tab_host.setCurrentTab(0);
	}	//��ʾ��ʱ��
	private void showTimer()
	{
		//����������� ��ť��Ӧ
		btnCancel.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {
				//����ȷ�Ͽ�
				AlertDialog comfirm = new AlertDialog.Builder(MainActivity.this)
				.setTitle("����+").setMessage("���Ҫ�������������")
				.setPositiveButton("����!", new DialogInterface.OnClickListener()
				{
					//ȷ�Ϸ���������� ��ť��Ӧ
					public void onClick(DialogInterface dialog, int which)
					{
						//��λ��ʾ����
						reset();
					}
				}).setNegativeButton("ȡ��",null).create();
				//��ʾȷ�Ͽ�
				comfirm.show();
			}
		});
		
		//��ʾ��ʱ������
		((LinearLayout)findViewById(R.id.TimerLayout)).setVisibility(View.VISIBLE);
		
		//������ʱ��
		startTimer();
	}
	
	//��λ��ʾ����
	private void reset()
	{
		//������ʱ���߳�
		isTimerEnd = true;
		//���� ����ɷ��� ��ʾ�б�
	//TODO	updateFinishEvent();
		//���ؼ�ʱ������
		((LinearLayout)findViewById(R.id.TimerLayout)).setVisibility(View.GONE);
		//��ʾ ����һ������ ��ť
		btnStart.setVisibility(View.VISIBLE);
	}
	
	//������ʱ��
	private void startTimer()
	{
		startTime = (new Date()).getTime();
		limitTime = 25*60*1000;
		isTimerEnd = false;
		mHandler = new Handler();
		
		//������ʱ���߳�
		timer = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!isTimerEnd)
				{
					try{
						//ͨ��Handler����
						mHandler.post(new Runnable()
						{
							public void run()
							{
								//���¼�ʱ����ʾ
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
		//�����߳�
		timer.start();
	}
	
	//���¼�ʱ����ʾ
	private void updateTimer()
	{
		//���㷬��ʣ��ʱ��
		long nowTime = new Date().getTime();
		long pastTime = nowTime - startTime;
		long remainTime = limitTime-pastTime;
		
		//�жϷ����Ƿ��ѽ���
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
	
	//��ʾ������ɶԻ���
	private void showFinishDialog()
	{
		final Dialog finish = new Dialog(this);
		finish.setContentView(R.layout.dialog_finish);
		finish.setTitle("����+");
		
		//��������������Чʱ��ť����
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
	
	//�������ɷ��ѽ����ݿ�
	private void addNewEvent(String event)
	{
		//����ȷ�����ʱ��
		endTime = new Date().getTime();
		//����ContentValues����
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.FinalEvent_StartTime,startTime);
		values.put(DatabaseHelper.FinalEvent_EndTime,endTime);
		values.put(DatabaseHelper.FinalEvent_Event,event);
		//��������
		DatabaseOperator dbo = new DatabaseOperator(this);
		dbo.insertNewEvent(values);
	}
}
