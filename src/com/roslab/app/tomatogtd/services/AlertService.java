package com.roslab.app.tomatogtd.services;

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
		alert = MediaPlayer.create(this, R.raw.alert);
		alert.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				mp.release();
				AlertService.this.stopSelf();
			}
		});
		Log.v(TAG, "On onCreate--->");
	}

	@SuppressWarnings("deprecation")
	public void onStart(Intent intent, int startId) {
		Log.v("AlertService onStart", "");
		super.onStart(intent, startId);
		alert.start();
		Log.v(TAG, "On onStart--->");
	}

	public IBinder onBind(Intent arg0) {
		Log.v(TAG, "On onBind--->");
		return null;
	}
}
