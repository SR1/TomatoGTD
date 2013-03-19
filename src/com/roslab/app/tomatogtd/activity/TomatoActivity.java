package com.roslab.app.tomatogtd.activity;

import java.util.Date;

import com.roslab.app.tomatogtd.R;
import com.roslab.app.tomatogtd.database.DatabaseHelper;
import com.roslab.app.tomatogtd.database.DatabaseOperator;
import com.roslab.app.tomatogtd.tool.Tools;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

//主界面Activity
public class TomatoActivity extends Activity
{
	//TODO 测试输出
	TextView testText;
	
	private ListView listView;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tomato_activity);
		
		//TODO 初始化测试输出TextView
		testText = (TextView)findViewById(R.id.testText);
		listView = (ListView)findViewById(R.id.tomato_view);
		
		updateFinishEvent();
	}
	
	//TODO 更新 已完成番茄 显示列表
	public void updateFinishEvent()
	{
		DatabaseOperator dbo = new DatabaseOperator(this);
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1,
				dbo.queryTodayEvent()));
	}
}
