package com.roslab.app.tomatogtd.view;

import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.services.MainService;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class AddTodosDialog extends Dialog implements OnClickListener {

	/***
	 * 声明组件
	 */
	EditText subject;
	TextView unplan;
	EditText remark;
	Button submit;
	Button cancel;

	public AddTodosDialog(Context context) {
		super(context);
	}

	public static AddTodosDialog newInstance(Context context) {
		AddTodosDialog dialog = new AddTodosDialog(context);
		return dialog;
	}

	/***
	 * 初始化、设置组件
	 */
	private void initializeComponent() {
		subject = (EditText) findViewById(R.id.addTodosSubject);
		unplan = (TextView) findViewById(R.id.addTodosUnplan);
		remark = (EditText) findViewById(R.id.addTodosRemark);
		submit = (Button) findViewById(R.id.addSubmit);
		cancel = (Button) findViewById(R.id.addCancel);
		
		submit.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragment_add_todos_dialogue);
		initializeComponent();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.addSubmit:
			String subjectText = subject.getText().toString();
			String remarkText = remark.getText().toString();
			MainService mainService = MainService.getController(getOwnerActivity());
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
