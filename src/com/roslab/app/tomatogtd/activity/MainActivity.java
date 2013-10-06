package com.roslab.app.tomatogtd.activity;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.fragment.MenuFragment;
import com.roslab.app.tomatogtd.fragment.TodaysTodoFragment;
import com.roslab.app.tomatogtd.interfaces.MenuOperation;
import com.roslab.app.tomatogtd.services.MainService;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class MainActivity extends FragmentActivity implements MenuOperation {

	public static final String TAG = "MainActivity";

	private SlidingMenu menu;

	/**
	 * 拦截返回键事件，使应用进入后台运行
	 */
	@Override
	public void onBackPressed() {
		// 点击返回键关闭滑动菜单
		if (menu.isMenuShowing()) {
			menu.showContent();
		} else {
			// 拦截返回键使Activity进入后台
			moveTaskToBack(false);
			// 使Activity销毁
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
		// 设置滑动菜单的属性值
		menu = new SlidingMenu(this);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

		// 设置滑动菜单的视图界面
		menu.setMenu(R.layout.activity_main_menu);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, MenuFragment.newInstance(this)).commit();
	}

	@Override
	public void openMenu() {
		if (menu != null && !menu.isMenuShowing())
			menu.showMenu();
	}

	@Override
	public void closeMenu() {
		if (menu != null && menu.isMenuShowing())
			menu.showContent();
	}
}
