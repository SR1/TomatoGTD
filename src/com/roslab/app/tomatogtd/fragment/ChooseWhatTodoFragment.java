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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseWhatTodoFragment extends Fragment implements OnClickListener {

	public static final String TAG = "ChooseWhatTodoFragment";

	/***
	 * ����״̬������
	 */
	MainService mService;
	ArrayList<AllTodosItem> allUndone;
	int next;
	int addAllTodosId;
	int firstEstimate;

	/***
	 * �������
	 */
	TextView choose1;
	TextView choose2;
	Button add1;
	Button add2;

	/***
	 * ��ʼ�����������
	 */
	private void initializeComponent(View view) {
		choose1 = (TextView) view.findViewById(R.id.choose_todos_1);
		choose2 = (TextView) view.findViewById(R.id.choose_todos_2);
		add1 = (Button) view.findViewById(R.id.choose_add_todos_1);
		add2 = (Button) view.findViewById(R.id.choose_add_todos_2);

		choose1.setOnClickListener(this);
		choose2.setOnClickListener(this);
		add1.setOnClickListener(this);
		add2.setOnClickListener(this);

	}

	/***
	 * ��ʼ������
	 */
	private void initializeData() {
		mService = MainService.getController(getActivity());
		allUndone = mService.getAllUnfinishTodos();
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
		choose2.setText(allUndone.get(1).getSubject());
		choose2.setBackgroundColor(getResources().getColor(
				allUndone.get(1).getColor()));
		choose1.setVisibility(View.VISIBLE);
		choose2.setVisibility(View.VISIBLE);
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
			next++;
		} else if (next == allUndone.size()) {
			textView.setVisibility(View.GONE);
			AllTodosItem item = allUndone.get(next-1);
			addAllTodosId = item.getId();
			firstEstimate = 3;
			next++;
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
			next(choose1);
			break;
		case R.id.choose_todos_2:
			next(choose2);
			break;
		case R.id.choose_add_todos_1:
		case R.id.choose_add_todos_2:
			mService.addTodaysTodos(addAllTodosId, firstEstimate);
			initializeData();
			initializeView();
		default:
			break;
		}
	}

}
