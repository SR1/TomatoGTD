package com.roslab.app.tomatogtd.fragment;

import java.util.ArrayList;

import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.adapter.TodaysTodoAdapter;
import com.roslab.app.tomatogtd.database.DatabaseOperator;
import com.roslab.app.tomatogtd.enity.TodaysTodoItem;
import com.roslab.app.tomatogtd.services.MainService;
import com.roslab.app.tomatogtd.view.UnderlinePageIndicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TodaysTodoFragment extends Fragment {

	public static final String TAG = "TodaysTodoFragment";
	
	int currentPosition = 0;
	
	ViewPager vp;
	ArrayList<TodaysTodoItem> todolist;
	UnderlinePageIndicator mIndicator;
	MainService mService;

	private void initializeComponent(View view){
		vp = (ViewPager)view.findViewById(R.id.viewpager);
		mIndicator = (UnderlinePageIndicator)view.findViewById(R.id.indicator);
		mService = MainService.getController(getActivity());
		if(mService!=null)
			Log.v(TAG, "initializeComponent-->get MainService");
	}
	
	private void initializeData(){
		DatabaseOperator dbo = new DatabaseOperator(getActivity());
		todolist = dbo.queryTodaysTodoList();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_todays_todo, null);
		initializeComponent(layout);
		return layout;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initializeData();
		vp.setAdapter(new TodaysTodoAdapter(getFragmentManager(), todolist));
		mIndicator.setViewPager(vp);
		mIndicator.setFades(false);
	}
	
	@Override
	public void onResume() {
		//vp.setCurrentItem(currentPosition, false);
		//mIndicator.setCurrentItem(currentPosition);
		super.onResume();
		Log.v(TAG, "onResume-->"+currentPosition);
	}

	@Override
	public void onPause() {
		currentPosition = vp.getCurrentItem();
		super.onPause();
		Log.v(TAG, "onPause-->"+currentPosition);
	}
	
}
