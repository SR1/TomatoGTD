package com.roslab.app.tomatogtd.activity;

import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.controler.StartActivity;
import com.roslab.app.tomatogtd.controler.StartActivity.OnAfterStartActivityListener;
import com.roslab.app.tomatogtd.services.MainService;

import android.app.Activity;
import android.os.Bundle;

public class GuideActivity extends Activity implements OnAfterStartActivityListener{

	public static final String TAG = "GuideActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		MainService.getController(this);
		StartActivity.launchMainActivityDelayed(this, this, 3000);
	}

	@Override
	public void onStartedActivity() {
		this.finish();
	}
}
