package com.roslab.app.tomatogtd.view;

import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.enity.AllTodosItem;
import com.roslab.app.tomatogtd.fragment.ChooseWhatTodoFragment;
import com.roslab.app.tomatogtd.services.MainService;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class AddTodaysTodosDialog extends Dialog implements OnClickListener {

	/***
	 * 声明状态、数据
	 */
	int allTodoId;
	String subject;

	/***
	 * 声明组件
	 */
	TextView chooseMessage;
	EditText chooseEstimate;
	Button chooseAdd;
	Button chooseRestart;

	public AddTodaysTodosDialog(Context context) {
		super(context);
	}

	public static AddTodaysTodosDialog newInstance(Context context,
			AllTodosItem item) {
		AddTodaysTodosDialog dialog = new AddTodaysTodosDialog(context);
		dialog.allTodoId = item.getId();
		dialog.subject = item.getSubject();
		return dialog;
	}

	/***
	 * 初始化、设置组件
	 */
	private void initializeComponent() {
		chooseMessage = (TextView) findViewById(R.id.chooseMessage);
		chooseEstimate = (EditText) findViewById(R.id.chooseEstimate);
		chooseAdd = (Button) findViewById(R.id.chooseAdd);
		chooseRestart = (Button) findViewById(R.id.chooseRestart);

		chooseMessage.setText(getContext().getString(
				R.string.choose_add_message, subject));

		chooseAdd.setOnClickListener(this);
		chooseRestart.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragment_add_todays_todos_dialogue);
		initializeComponent();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.chooseAdd:
			int firstEstimate = Integer.parseInt(chooseEstimate.getText()
					.toString());
			MainService mainService = MainService
					.getController(getOwnerActivity());
			if (mainService.addTodaysTodos(allTodoId, firstEstimate)) {
				((FragmentActivity) getContext())
						.getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame,
								ChooseWhatTodoFragment.newInstance()).commit();
				dismiss();
			}
			break;
		case R.id.chooseRestart:
			((FragmentActivity) getOwnerActivity())
					.getSupportFragmentManager()
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
