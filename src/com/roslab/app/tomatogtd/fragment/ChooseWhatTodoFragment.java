package com.roslab.app.tomatogtd.fragment;

import com.roslab.app.tomatogtd.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChooseWhatTodoFragment extends Fragment {

	public static final String TAG = "ChooseWhatTodoFragment";

	public static ChooseWhatTodoFragment newInstance() {
		ChooseWhatTodoFragment fragment = new ChooseWhatTodoFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(
				R.layout.fragment_choose_todos_for_today, null, false);

		return layout;
	}

}
