package com.roslab.app.tomatogtd.controler;

import com.roslab.app.tomatogtd.activity.MainActivity;
import com.roslab.app.tomatogtd.controler.StartActivity.OnAfterStartActivityListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

public class StartActivity {
	
	public static final int MainActivity = 1;
	public OnAfterStartActivityListener listener;

	public static void launchMainActivity(Activity activity, OnAfterStartActivityListener listener) {
		StartActivityHandler handler = new StartActivityHandler(activity,listener);
		handler.sendEmptyMessage(MainActivity);
	}

	public static void launchMainActivityDelayed(Activity activity, OnAfterStartActivityListener listener, int delayMillis) {
		StartActivityHandler handler = new StartActivityHandler(activity,listener);
		handler.sendEmptyMessageDelayed(MainActivity, delayMillis);
	}
	
	public interface OnAfterStartActivityListener{
		public void onStartedActivity();
	}
}

class StartActivityHandler extends Handler {
	
	Activity activity;
	OnAfterStartActivityListener listener;
	
	public StartActivityHandler(Activity activity, OnAfterStartActivityListener listener){
		this.activity = activity;
		this.listener = listener;
	}
	
	@Override
	public void handleMessage(Message msg) {
		switch (msg.what) {
		case StartActivity.MainActivity:
			Intent intent = new Intent(activity,MainActivity.class);
			activity.startActivity(intent);
			break;
		}
		if(listener!=null)
			listener.onStartedActivity();
	}
}
