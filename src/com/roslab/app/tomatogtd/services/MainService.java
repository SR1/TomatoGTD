package com.roslab.app.tomatogtd.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MainService extends Service {
	
	public static final String TAG = "MainService";

	@Override
	public void onCreate() {

		Log.v(TAG, "onCreate-->");
		super.onCreate();
	}

	@Override
	public void onDestroy() {

		Log.v(TAG, "onDestroy-->");
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Log.v(TAG, "onStartCommand-->");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {

		Log.v(TAG, "onBind-->");
		return null;
	}

}
