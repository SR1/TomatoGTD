package com.roslab.app.tomatogtd.activity;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.database.DatabaseOperator;
import com.roslab.app.tomatogtd.fragment.TodaysTodoFragment;
import com.roslab.app.tomatogtd.fragment.TodaysTodoItemFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class MainActivity extends FragmentActivity {
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
			moveTaskToBack(false);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("main");
		initialMenu();

	}

	private void initialMenu() {
		setContentView(R.layout.activity_main_context);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, new TodaysTodoFragment()).commit();
		// ���û����˵�������ֵ

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
		// getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame,
		// new SampleListFragment()).commit();
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
