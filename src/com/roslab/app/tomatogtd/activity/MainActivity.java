package com.roslab.app.tomatogtd.activity;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.fragment.TodaysTodoFragment;
import com.roslab.app.tomatogtd.services.MainService;
import com.roslab.app.tomatogtd.view.AddTodosDialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

	public static final String TAG = "MainActivity";

	private SlidingMenu menu;

	/**
	 * ���ط��ؼ��¼���ʹӦ�ý����̨����
	 */
	@Override
	public void onBackPressed() {
		// ������ؼ��رջ����˵�
		if (menu.isMenuShowing()) {
			menu.showContent();
		} else {
			// ���ط��ؼ�ʹActivity�����̨
			moveTaskToBack(false);
			// ʹActivity����
			// super.onBackPressed();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("main");
		initialMenu();
		if (MainService.getController(this) != null)
			Log.v(TAG, "onCreate-->get MainService");
	}

	private void initialMenu() {
		setContentView(R.layout.activity_main_context);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, new TodaysTodoFragment()).commit();
		// ���û����˵�������ֵ
		menu = new SlidingMenu(this);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

		// ���û����˵�����ͼ����
		menu.setMenu(R.layout.activity_main_menu);
		Button btn = (Button) findViewById(R.id.addTodosButton);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (menu.isMenuShowing()) {
					menu.showContent();
				}
				Dialog a = new AddTodosDialog(MainActivity.this);
				a.show();
			}
		});
		// getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame,
		// new SampleListFragment()).commit();
	}
}
