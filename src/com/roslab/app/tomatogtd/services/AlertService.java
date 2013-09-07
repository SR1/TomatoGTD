package com.roslab.app.tomatogtd.services;

import com.roslab.app.tomatogtd.R;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;
import android.util.Log;

public class AlertService extends Service {
	private MediaPlayer alert;

	public void onCreate() {
		Log.v("AlertService onCreate", "");
		super.onCreate();
		alert = MediaPlayer.create(this, R.raw.alert);
		alert.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				mp.release();
				AlertService.this.stopSelf();
			}
		});
	}

	@SuppressWarnings("deprecation")
	public void onStart(Intent intent, int startId) {
		Log.v("AlertService onStart", "");
		super.onStart(intent, startId);
		alert.start();
	}

	public IBinder onBind(Intent arg0) {
		return null;
	}
}
