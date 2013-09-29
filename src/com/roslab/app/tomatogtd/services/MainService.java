package com.roslab.app.tomatogtd.services;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.roslab.app.tomatogtd.controler.MainControllerInterface;
import com.roslab.app.tomatogtd.enity.TimerState;

public class MainService extends Service implements MainControllerInterface {
	private static MainService mainService = null;

	@Override
	public MainControllerInterface getController(Activity activity) {
		if (mainService != null)
			return mainService;
		Intent intent = new Intent(activity, MainService.class);
		activity.startService(intent);
		return mainService;
	}

	private MainService() {
		super();
		mainService = this;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public TimerState getTimerState() {
		return null;
	}

	@Override
	public void startTimer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopTimer() {
		// TODO Auto-generated method stub
		
	}

}
