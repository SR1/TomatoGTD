package com.roslab.app.tomatogtd.adapter;

import java.util.ArrayList;

import com.roslab.app.tomatogtd.enity.TodaysTodoItem;
import com.roslab.app.tomatogtd.fragment.TodaysTodoFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TodaysTodoAdapter extends FragmentStatePagerAdapter {
	
	private ArrayList<TodaysTodoItem> todaysTodoList;
	private int count;
	
	public TodaysTodoAdapter(FragmentManager fragmentManager, ArrayList<TodaysTodoItem> todaysTodoList) {
		super(fragmentManager);
		this.todaysTodoList = todaysTodoList;
		count = 0;
		if(todaysTodoList!=null)
			count = todaysTodoList.size();
	}

	@Override
	public Fragment getItem(int index) {
		return TodaysTodoFragment.newInstance(todaysTodoList.get(index%count));
	}

	@Override
	public int getCount() {
		return count;
	}

}
