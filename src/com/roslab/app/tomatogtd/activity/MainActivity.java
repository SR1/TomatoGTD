package com.roslab.app.tomatogtd.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

	/**
	 * ���ط��ؼ��¼���ʹӦ�ý����̨����
	 */
	@Override
	public void onBackPressed() {
		moveTaskToBack(false);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

}
