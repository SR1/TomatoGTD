/*package com.roslab.app.tomatogtd.services;

import com.roslab.app.tomatogtd.controler.TimerThread;
import com.roslab.app.tomatogtd.interfaces.OnTimerStateChangeListener;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class TimerService extends Service implements OnTimerStateChangeListener{
	
	public static final String TAG = "TimerService";

	private TimerServiceBinder binder;
	private TimerThread timer;

	@Override
	public IBinder onBind(Intent intent) {
		Log.v(TAG, "onBind-->");
		binder = new TimerServiceBinder();
		return binder;
	}

	@Override
	public void onCreate() {
		Log.v(TAG, "onCreate-->");
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		Log.v(TAG, "onDestroy-->");
		if (timer.isRun())
			timer.stopTimer();
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.v(TAG, "onStartCommand-->");

		timer = TimerThread.getInstance();
		return START_STICKY;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.v(TAG, "onUnbind-->");
		return super.onUnbind(intent);
	}

	// ������ʱ��
	public void startTimer() {
		Log.v(TAG, "startTimer-->");
		if (!timer.isRun())
		{
			timer.setOnTimerStateChangeListener(this);
			timer.start();
		}
	}
	
	// ��Ӽ�����
	public void addOnTimerStateChangeListener(OnTimerStateChangeListener listener){
		Log.v(TAG, "addOnTimerStateChangeListener-->");
		timer.setOnTimerStateChangeListener(listener);
	}
	
	// �Ƴ�������
	public void removeOnTimerStateChangeListener(OnTimerStateChangeListener listener){
		Log.v(TAG, "removeOnTimerStateChangeListener-->");
		timer.removeOnTimerStateChangeListener(listener);
	}

	// ֹͣ��ʱ��
	public void stopTimer() {
		Log.v(TAG, "stopTimer-->");
		if (timer.isRun())
			timer.stopTimer();
	}
	
	// ���ؼ�ʱ������״̬
	public boolean isTimerStart() {
		Log.v(TAG, "isTimerStart-->");
		return timer.isRun();
	}

	public class TimerServiceBinder extends Binder {

		public TimerService getService() {
			return TimerService.this;
		}
	}

	// ������ʱ��Ч����
	private void launchTictacSoundService() {
		Log.v(TAG, "launchTictacSoundService-->");
		Intent intent = new Intent(this, AlertService.class);
		startService(intent);

	}

	// ֹͣ��ʱ��Ч����
	private void stopTictacSoundService() {
		Log.v(TAG, "stopTictacSoundService-->");
		Intent intent = new Intent(this, AlertService.class);
		stopService(intent);

	}

	@Override
	public void onTimerStart() {
		Log.v(TAG, "onTimerStart-->");
		launchTictacSoundService();
	}

	@Override
	public void onTimerStop() {
		Log.v(TAG, "onTimerStop-->");
		stopTictacSoundService();
	}

	@Override
	public void onTimerLoop() {
		Log.v(TAG, "onTimerLoop-->");
	}

	@Override
	public void onTimeUp() {
		Log.v(TAG, "onTimeUp-->");
		stopTictacSoundService();
	}
}*/