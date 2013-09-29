package com.roslab.app.tomatogtd.database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.roslab.app.tomatogtd.enity.TodaysTodoItem;
import com.roslab.app.tomatogtd.tool.RandomColorId;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;

public class DatabaseOperator {
	
	public static final String TAG = "DatabaseOperator";
	
	private DatabaseHelper dbh;
	private SQLiteDatabase db;

	private static final long A_DAY_LONG = 24 * 60 * 60 * 1000;

	// 构造函数
	public DatabaseOperator(Context context) {
		dbh = new DatabaseHelper(context);
	}

	public void insertTodo() {

	}

	public ArrayList<TodaysTodoItem> queryTodaysTodoList() {
		Log.v(TAG, "queryTodaysTodoList-->");
		ArrayList<TodaysTodoItem> todaysTodoList = new ArrayList<TodaysTodoItem>();

		db = dbh.getReadableDatabase();
		// TODO 数据库查询条件
		String selection = null;
		String[] selectionArg = null;

		Cursor cursor = db.query(DatabaseHelper.Table_AllTodo, null, selection,
				selectionArg, null, null, "id desc");

		int titleIndex = cursor.getColumnIndex(DatabaseHelper.AllTodo_Title);
		int remarkIndex = cursor.getColumnIndex(DatabaseHelper.AllTodo_Remark);

		int i=0;
		for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
			TodaysTodoItem item = new TodaysTodoItem();
			
			item.setTitle(cursor.getString(titleIndex));
			item.setRemark(cursor.getString(remarkIndex));
			item.setColorId(RandomColorId.getColorId(i));
			todaysTodoList.add(item);
			i++;
			Log.v(TAG, "todaysTodoList-->"+item);
		}

		// 关闭结果集和数据库
		cursor.close();
		db.close();
		Log.v(TAG, "todaysTodoList-->"+todaysTodoList);
		return todaysTodoList;
	}


	// 插入新番茄
	public void insertTodo(ContentValues values) {
		Log.v(TAG, "insertTodo-->");

		insertSampleData();
/*		db = dbh.getWritableDatabase();
		db.insert(DatabaseHelper.Table_FinishEvent, "id", values);
		db.close();*/
	}

	// TODO 插入样本数据
	public void insertSampleData() {
		Log.v(TAG, "insertSampleData-->");
		db = dbh.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(DatabaseHelper.AllTodo_Remark, "备注备注~~~");
		cv.put(DatabaseHelper.AllTodo_Title,"标题~~~");
		if(db.insert(DatabaseHelper.Table_AllTodo, "id", cv)!=-1)
			Log.v(TAG, "insertSampleDataVVV5-->");
		else
			Log.v(TAG, "insertSampleDataFail-->");
		
		db.close();
	}

	// 将时间转换为标准显示格式
	private String transTimeToFormal(long militime) {

		Date day = new Date(militime);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

		return sdf.format(day);
	}

}
