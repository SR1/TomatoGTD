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

	public static final String TAG = "TodaysTodoFragment";

	private static TodaysTodoFragment mTodaysTodoFragment;
	private TodaysTodoItem mTodaysTodoItem;
	private int position;
	private TodaysTodoViewHolder holder;
	private Integer color = null;
	private int[] tomatoTimerLayout = { R.layout.tomato_square,
			R.layout.tomato_circle, R.layout.tomato_circle };

	public static TodaysTodoFragment newInstance(TodaysTodoItem todaysTodoItem,
			int position) {
		mTodaysTodoFragment = new TodaysTodoFragment();
		mTodaysTodoFragment.mTodaysTodoItem = todaysTodoItem;
		mTodaysTodoFragment.position = position;

		Log.v(TAG, "newInstance-->");
		return mTodaysTodoFragment;
	}

	protected void initData() {

		if (mTodaysTodoItem != null) {
			color = getResources().getColor(RandomColorId.getColorId(position));

			holder.todays_todo_background.setBackgroundColor(color);
			holder.todays_todo_title.setText(mTodaysTodoItem.getTitle());
			holder.todays_todo_start_date.setText(getString(
					R.string.todays_todo_start_date,
					mTodaysTodoItem.getStartTime()));
			holder.todays_todo_end_date
					.setText(getString(R.string.todays_todo_end_date,
							mTodaysTodoItem.getEndTime()));
			holder.todays_todo_remark.setText(mTodaysTodoItem.getRemark());

			// 绘制预计番茄钟数
			int count = 0;
			for (int i = 0; i < mTodaysTodoItem.getTomato().length; i++) {
				if (mTodaysTodoItem.getTomato()[i] > 0) {
					for (int v = 0; v < 7; v++) {
						View square = getActivity().getLayoutInflater()
								.inflate(tomatoTimerLayout[i], null);
						// 显示计划的番茄钟
						if (v < mTodaysTodoItem.getTomato()[i]) {
							square.findViewById(R.id.plan).setVisibility(
									View.VISIBLE);
							// 给已经完成的番茄钟搭上×
							if (count < mTodaysTodoItem.getTomatoDone())
								square.findViewById(R.id.done).setVisibility(
										View.VISIBLE);
							count++;
						}
						holder.todays_todo_tomato[i].addView(square);
					}
				}
			}
			
			StringBuffer interrupt = new StringBuffer("");
			for(int i=0;i<mTodaysTodoItem.getInnerInterrupt();i++)
				interrupt.append("' ");
			for(int i=0;i<mTodaysTodoItem.getOutterInterrupt();i++)
				interrupt.append("- ");
			holder.todays_todo_tomato_interrupt.setText(interrupt.toString());

		}
		Log.v(TAG, "initData-->");
	}

	private void initViewHolder(View layout) {

		holder = new TodaysTodoViewHolder();

		holder.todays_todo_background = (LinearLayout) layout
				.findViewById(R.id.todays_todo_background);
		holder.todays_todo_title = (TextView) layout
				.findViewById(R.id.todays_todo_title);
		holder.todays_todo_start_date = (TextView) layout
				.findViewById(R.id.todays_todo_start_date);
		holder.todays_todo_end_date = (TextView) layout
				.findViewById(R.id.todays_todo_end_date);
		holder.todays_todo_remark = (TextView) layout
				.findViewById(R.id.todays_todo_remark);
		holder.todays_todo_timer_line = (ProgressBar) layout
				.findViewById(R.id.todays_todo_timer_line);
		holder.todays_todo_tomato = new LinearLayout[3];
		holder.todays_todo_tomato[0] = (LinearLayout) layout
				.findViewById(R.id.todays_todo_tomato_1st);
		holder.todays_todo_tomato[1] = (LinearLayout) layout
				.findViewById(R.id.todays_todo_tomato_2nd);
		holder.todays_todo_tomato[2] = (LinearLayout) layout
				.findViewById(R.id.todays_todo_tomato_3rd);
		holder.todays_todo_tomato_interrupt = (TextView) layout
				.findViewById(R.id.todays_todo_tomato_interrupt);
		
		Log.v(TAG, "initViewHolder-->");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater
				.inflate(R.layout.fragment_todays_todo_item, null);

		initViewHolder(layout);

		Log.v(TAG, "onCreateView-->");
		return layout;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initData();
	}

	static class TodaysTodoViewHolder {
		public LinearLayout todays_todo_background;
		public TextView todays_todo_title;
		public TextView todays_todo_start_date;
		public TextView todays_todo_end_date;
		public TextView todays_todo_remark;
		public ProgressBar todays_todo_timer_line;
		public TextView todays_todo_tomato_interrupt;
		public LinearLayout[] todays_todo_tomato;
	}
}
