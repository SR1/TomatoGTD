package com.roslab.app.tomatogtd.fragment;

import com.roslab.app.tomatogtd.R;
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

public class AddTodosFragment extends DialogFragment implements OnClickListener {

	/***
	 * 声明组件
	 */
	EditText subject;
	TextView unplan;
	EditText remark;
	Button submit;
	Button cancel;

	/***
	 * 初始化、设置组件
	 */
	private void initializeComponent(View view) {
		subject = (EditText) view.findViewById(R.id.addTodosSubject);
		unplan = (TextView) view.findViewById(R.id.addTodosUnplan);
		remark = (EditText) view.findViewById(R.id.addTodosRemark);
		submit = (Button) view.findViewById(R.id.addSubmit);
		cancel = (Button) view.findViewById(R.id.addCancel);

		submit.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}

	public static AddTodosFragment newInstance() {
		AddTodosFragment fragment = new AddTodosFragment();
		fragment.setStyle(STYLE_NO_TITLE, STYLE_NORMAL);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_add_todos_dialogue,
				container, true);
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
		case R.id.addSubmit:
			String subjectText = subject.getText().toString();
			String remarkText = remark.getText().toString();
			MainService mainService = MainService.getController(getActivity());
			if (mainService.addTodos(subjectText, remarkText)) {
				dismiss();
			}
			break;
		case R.id.addCancel:
			dismiss();
			break;
		default:
			break;
		}

	}

}
