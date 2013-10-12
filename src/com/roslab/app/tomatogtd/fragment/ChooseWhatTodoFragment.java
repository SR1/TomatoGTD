package com.roslab.app.tomatogtd.fragment;

import java.util.ArrayList;

import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.enity.AllTodosItem;
import com.roslab.app.tomatogtd.services.MainService;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseWhatTodoFragment extends Fragment implements OnClickListener {

	public static final String TAG = "ChooseWhatTodoFragment";

	/***
	 * 声明状态、数据
	 */
	MainService mService;
	ArrayList<AllTodosItem> allUndone;
	int next;
	int addAllTodosId;
	int firstEstimate;
	int current = -1;

	/***
	 * 声明组件
	 */
	TextView choose1;
	TextView choose2;

	/***
	 * 初始化、设置组件
	 */
	private void initializeComponent(View view) {
		choose1 = (TextView) view.findViewById(R.id.choose_todos_1);
		choose2 = (TextView) view.findViewById(R.id.choose_todos_2);

		choose1.setOnClickListener(this);
		choose2.setOnClickListener(this);

	}

	/***
	 * 初始化数据
	 */
	private void initializeData() {
		mService = MainService.getController(getActivity());
		allUndone = mService.getAllUnfinishTodosForChoose();
	}

	protected void initializeView() {
		if (allUndone.size() == 0) {
			Toast.makeText(getActivity(),
					getString(R.string.please_add_new_todos),
					Toast.LENGTH_SHORT).show();
			return;
		}
		if (allUndone.size() == 1) {
			choose1.setText(allUndone.get(0).getSubject());
			choose1.setBackgroundColor(getResources().getColor(
					allUndone.get(0).getColor()));
			choose2.setBackgroundColor(getResources().getColor(
					R.color.flat_text));
			return;
		}
		choose1.setText(allUndone.get(0).getSubject());
		choose1.setBackgroundColor(getResources().getColor(
				allUndone.get(0).getColor()));
		choose1.setTag(0);
		choose2.setText(allUndone.get(1).getSubject());
		choose2.setBackgroundColor(getResources().getColor(
				allUndone.get(1).getColor()));
		choose1.setVisibility(View.VISIBLE);
		choose2.setVisibility(View.VISIBLE);
		choose2.setTag(1);
		next = 2;

	}

	public static ChooseWhatTodoFragment newInstance() {
		ChooseWhatTodoFragment fragment = new ChooseWhatTodoFragment();
		return fragment;
	}

	protected void next(TextView textView) {
		if (next < allUndone.size()) {
			AllTodosItem item = allUndone.get(next);
			textView.setText(item.getSubject());
			textView.setBackgroundColor(getResources()
					.getColor(item.getColor()));
			textView.setTag(next);
			next++;
		} else if (next == allUndone.size()) {
			textView.setVisibility(View.GONE);
			AllTodosItem item = allUndone.get(current);
			AddTodaysTodoFragment.newInstance(item).show(getFragmentManager(),
					"AddTodaysTodoFragment");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(
				R.layout.fragment_choose_todos_for_today, null, false);
		return layout;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initializeComponent(view);
		initializeData();
		initializeView();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.choose_todos_1:
			current = (Integer) choose2.getTag();
			next(choose1);
			break;
		case R.id.choose_todos_2:
			current = (Integer) choose1.getTag();
			next(choose2);
			break;
		default:
			break;
		}
	}

}
