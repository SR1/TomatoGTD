package com.roslab.app.tomatogtd.fragment;

import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.enity.TodaysTodoItem;
import com.roslab.app.tomatogtd.tool.RandomColorId;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TodaysTodoFragment extends Fragment {

	public static String TAG = "TodaysTodoFragment";

	private static TodaysTodoFragment mTodaysTodoFragment;
	private TodaysTodoItem mTodaysTodoItem;
	private int[] tomatoTimerLayout = {R.layout.tomato_square,R.layout.tomato_circle,R.layout.tomato_circle};
	

	public static TodaysTodoFragment newInstance(TodaysTodoItem todaysTodoItem) {
		mTodaysTodoFragment = new TodaysTodoFragment();
		mTodaysTodoFragment.mTodaysTodoItem = todaysTodoItem;

		Log.v(TAG, "newInstance-->");
		return mTodaysTodoFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_todays_todo_item,
				null);
		
		TodaysTodoViewHolder holder = new TodaysTodoViewHolder();

		holder.todays_todo_background = (LinearLayout)layout.findViewById(R.id.todays_todo_background);
		holder.todays_todo_title = (TextView)layout.findViewById(R.id.todays_todo_title);
		holder.todays_todo_start_date = (TextView)layout.findViewById(R.id.todays_todo_start_date);
		holder.todays_todo_end_date = (TextView)layout.findViewById(R.id.todays_todo_end_date);
		holder.todays_todo_remark = (TextView)layout.findViewById(R.id.todays_todo_remark);
		holder.todays_todo_timer_line = (ProgressBar)layout.findViewById(R.id.todays_todo_timer_line);
		holder.todays_todo_tomato = new LinearLayout[3];
		holder.todays_todo_tomato[0]= (LinearLayout)layout.findViewById(R.id.todays_todo_tomato_1st);
		holder.todays_todo_tomato[1] = (LinearLayout)layout.findViewById(R.id.todays_todo_tomato_2nd);
		holder.todays_todo_tomato[2] = (LinearLayout)layout.findViewById(R.id.todays_todo_tomato_3rd);
		
		if(mTodaysTodoItem!=null)
		{
			holder.todays_todo_background.setBackgroundColor(getResources().getColor(RandomColorId.getColorId()));
			holder.todays_todo_title.setText(mTodaysTodoItem.getTitle());
			holder.todays_todo_start_date.setText(getString(R.string.todays_todo_start_date, mTodaysTodoItem.getStartTime()));
			holder.todays_todo_end_date.setText(getString(R.string.todays_todo_end_date, mTodaysTodoItem.getEndTime()));
			holder.todays_todo_remark.setText(mTodaysTodoItem.getRemark());
			
			for(int i=0;i<mTodaysTodoItem.getTomato().length;i++)
			{
				int count=0;
				for(int v=0;v<mTodaysTodoItem.getTomato()[i];v++) {
					View square = inflater.inflate(tomatoTimerLayout[i], null);
					if(v<mTodaysTodoItem.getTomatoDone())
						square.findViewById(R.id.done).setVisibility(View.VISIBLE);
					holder.todays_todo_tomato[i].addView(square);
					count++;
				}
			}
			
		}
		Log.v(TAG, "onCreateView-->");
		return layout;
	}

	static class TodaysTodoViewHolder {
		public LinearLayout todays_todo_background;
		public TextView todays_todo_title;
		public TextView todays_todo_start_date;
		public TextView todays_todo_end_date;
		public TextView todays_todo_remark;
		public ProgressBar todays_todo_timer_line;
		public LinearLayout[] todays_todo_tomato;
	}
}
