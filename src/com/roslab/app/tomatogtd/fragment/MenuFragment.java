package com.roslab.app.tomatogtd.fragment;

import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.interfaces.MenuOperation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuFragment extends Fragment implements OnClickListener{

	public static final String TAG = "MenuFragment";

	MenuOperation menu;
	Button todaysTodos;
	Button addTodos;
	Button pickTodos;
	
	
	public static MenuFragment newInstance(MenuOperation menu) {
		MenuFragment fragment = new MenuFragment();
		fragment.menu = menu;
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(
				R.layout.fragment_menu, null, false);
		return layout;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		todaysTodos = (Button)view.findViewById(R.id.todaysTodosButton);
		addTodos = (Button)view.findViewById(R.id.addTodosButton);
		pickTodos = (Button)view.findViewById(R.id.chooseTodoButton);

		todaysTodos.setOnClickListener(this);
		addTodos.setOnClickListener(this);
		pickTodos.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.todaysTodosButton:
			getFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new TodaysTodoFragment()).commit();
			break;
		case R.id.addTodosButton:
			AddTodosFragment.newInstance().show(getFragmentManager(), "addTodos");
			break;
		case R.id.chooseTodoButton:
			getFragmentManager().beginTransaction()
					.replace(R.id.content_frame, ChooseWhatTodoFragment.newInstance()).commit();
			break;

		default:
			break;
		}
		if(menu!=null)
			menu.closeMenu();
	}
}
