package com.roslab.app.tomatogtd.fragment;

import com.roslab.app.tomatogtd.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ErrorFragment extends Fragment {

	public static final String TAG = "ErrorFragment";

	/***
	 * 声明组件
	 */
	TextView face_tv;
	TextView msg_tv;

	/***
	 * 声明状态、数据
	 */
	String face;
	String message;

	/***
	 * 初始化、设置组件
	 */
	private void initializeComponent(View view) {
		face_tv = (TextView) view.findViewById(R.id.error_face);
		msg_tv = (TextView) view.findViewById(R.id.error_message);

	}

	protected void initializeView() {
		if(face!=null && !"".equals(face.trim()))
			face_tv.setText(face);
		if(message!=null && !"".equals(message.trim()))
			msg_tv.setText(message);
	}
	public static ErrorFragment newInstance(String face, String message) {
		ErrorFragment fragment = new ErrorFragment();
		
		fragment.face = face;
		fragment.message = message;
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater
				.inflate(R.layout.fragment_error, null);
		return layout;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initializeComponent(view);
		initializeView();
	}

}
