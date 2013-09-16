/*package com.roslab.app.tomatogtd.services;

import com.roslab.app.tomatogtd.R;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;
import android.util.Log;

public class AlertService extends Service {
	
	public static final String TAG = "AlertService";
	
	private MediaPlayer alert;

	public void onCreate() {
		super.onCreate();
		alert = MediaPlayer.create(this, R.raw.tictac);
		alert.setLooping(true);
		Log.v(TAG, "On onCreate--->");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		alert.start();
		super.onStartCommand(intent, flags, startId);
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		if(alert!=null)
			alert.release();
			
		super.onDestroy();
	}

	public IBinder onBind(Intent arg0) {
		Log.v(TAG, "On onBind--->");
		return null;
	}
}*/
