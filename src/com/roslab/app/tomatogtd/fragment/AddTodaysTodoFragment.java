package com.roslab.app.tomatogtd.fragment;

import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.enity.AllTodosItem;
import com.roslab.app.tomatogtd.services.MainService;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddTodaysTodoFragment extends DialogFragment implements
		OnClickListener {

	/***
	 * 声明状态、数据
	 */
	AllTodosItem item;

	/***
	 * 声明组件
	 */
	TextView chooseMessage;
	EditText chooseEstimate;
	Button chooseAdd;
	Button chooseRestart;

	/***
	 * 初始化、设置组件
	 */
	private void initializeComponent(View view) {
		chooseMessage = (TextView) view.findViewById(R.id.chooseMessage);
		chooseEstimate = (EditText) view.findViewById(R.id.chooseEstimate);
		chooseAdd = (Button) view.findViewById(R.id.chooseAdd);
		chooseRestart = (Button) view.findViewById(R.id.chooseRestart);

		chooseMessage.setText(getString(R.string.choose_add_message,
				item.getSubject()));

		chooseAdd.setOnClickListener(this);
		chooseRestart.setOnClickListener(this);
	}

	public static AddTodaysTodoFragment newInstance(AllTodosItem item) {
		AddTodaysTodoFragment fragment = new AddTodaysTodoFragment();
		fragment.item = item;
		fragment.setStyle(STYLE_NO_TITLE, STYLE_NORMAL);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(
				R.layout.fragment_add_todays_todos_dialogue, container, true);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initializeComponent(view);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.chooseAdd:
			int firstEstimate = Integer.parseInt(chooseEstimate.getText()
					.toString());
			MainService mainService = MainService.getController(getActivity());
			if (mainService.addTodaysTodos(item.getId(), firstEstimate)) {
				getFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame,
								ChooseWhatTodoFragment.newInstance()).commit();
				dismiss();
			}
			break;
		case R.id.chooseRestart:
			getFragmentManager()
					.beginTransaction()
					.replace(R.id.content_frame,
							ChooseWhatTodoFragment.newInstance()).commit();
			dismiss();
			break;
		default:
			break;
		}

	}
}
