package com.roslab.app.tomatogtd.database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.roslab.app.tomatogtd.database.AddTodosOperatorBuilder.CannotBeNullException;
import com.roslab.app.tomatogtd.enity.AllTodosItem;
import com.roslab.app.tomatogtd.enity.TodaysTodoItem;
import com.roslab.app.tomatogtd.model.DatabaseOperatorModel;
import com.roslab.app.tomatogtd.tool.RandomColorId;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseOperator implements DatabaseOperatorModel {

	public static final String TAG = "DatabaseOperator";

	private Context context;

	public DatabaseOperator(Context context) {
		this.context = context;
	}

	@Override
	public boolean addTodo(String subject, String remark, long dueTime) {
		try {
			AddTodosOperatorBuilder operator = new AddTodosOperatorBuilder(
					subject);
			operator.setRemark(remark);
			operator.setDueTime(dueTime);
			return operator.excute(context);
		} catch (CannotBeNullException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addUnPlanedTodo(String subject, String remark, long dueTime) {
		try {
			AddTodosOperatorBuilder operator = new AddTodosOperatorBuilder(
					subject);
			operator.setRemark(remark);
			operator.setDueTime(dueTime);
			operator.setIsUnPlaned(true);
			return operator.excute(context);
		} catch (CannotBeNullException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<TodaysTodoItem> getTodaysTodoList() {

		final String queryTodaysTodoList = "SELECT date today, todaysTodos.id, allTodoId, "
				+ "subject, remark, addTime, dueTime, firstEstimate, "
				+ "secondEstimate, thirdEstimate, "
				+ "innerInterrupt, outterInterrupt "
				+ "FROM allTodos, todaysTodos "
				+ "WHERE allTodoId=allTodos.id "
				+ "AND date=(date('now','localtime'));";

		ArrayList<TodaysTodoItem> todaysTodoList = new ArrayList<TodaysTodoItem>();
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase sqliteDatabase = databaseHelper.getReadableDatabase();

		Cursor cursor = sqliteDatabase.rawQuery(queryTodaysTodoList, null);

		int todayIndex = cursor.getColumnIndex("today");
		int idIndex = cursor.getColumnIndex("id");
		int allTodoIdIndex = cursor.getColumnIndex("allTodoId");
		int subjectIndex = cursor.getColumnIndex("subject");
		int remarkIndex = cursor.getColumnIndex("remark");
		int addTimeIndex = cursor.getColumnIndex("addTime");
		int dueTimeIndex = cursor.getColumnIndex("dueTime");
		int firstEstimateIndex = cursor.getColumnIndex("firstEstimate");
		int secondEstimateIndex = cursor.getColumnIndex("secondEstimate");
		int thirdEstimateIndex = cursor.getColumnIndex("thirdEstimate");
		int innerInterruptIndex = cursor.getColumnIndex("innerInterrupt");
		int outterInterruptIndex = cursor.getColumnIndex("outterInterrupt");

		int i = 0;
		for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
			TodaysTodoItem item = new TodaysTodoItem();

			item.setToday(cursor.getString(todayIndex));
			item.setId(cursor.getInt(idIndex));
			item.setAllTodoId(cursor.getInt(allTodoIdIndex));
			item.setSubject(cursor.getString(subjectIndex));
			item.setRemark(cursor.getString(remarkIndex));
			item.setAddTime(cursor.getString(addTimeIndex));
			item.setDueTime(cursor.getString(dueTimeIndex));
			item.setFirstEstimate(cursor.getInt(firstEstimateIndex));
			item.setSecondEstimate(cursor.getInt(secondEstimateIndex));
			item.setThirdEstimate(cursor.getInt(thirdEstimateIndex));
			item.setInnerInterrupt(cursor.getInt(innerInterruptIndex));
			item.setOutterInterrupt(cursor.getInt(outterInterruptIndex));
			item.setColor(RandomColorId.getColorId(i));

			todaysTodoList.add(item);
			i++;
			Log.v(TAG, "todaysTodoList-->" + item);
		}

		// 关闭结果集和数据库
		cursor.close();
		sqliteDatabase.close();
		databaseHelper.close();
		return todaysTodoList;
	}

	@Override
	public boolean updateSubject(int allTodosId, String subject) {

		// final String update =
		// "UPDATE allTodos SET subject='%s' WHERE id=%d;";
		final String where = "id=?";

		ContentValues contentValues = new ContentValues();
		contentValues.put(DatabaseHelper.TABLE_ALLTODOS_SUBJECT, subject);

		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase sqliteDatabase = databaseHelper.getReadableDatabase();
		boolean isSuccess = (sqliteDatabase.update(
				DatabaseHelper.TABLE_ALLTODOS, contentValues, where,
				new String[] { String.valueOf(allTodosId) }) > 0);
		sqliteDatabase.close();
		databaseHelper.close();
		return isSuccess;
	}

	@Override
	public boolean updateRemark(int allTodosId, String remark) {

		// final String update = "UPDATE allTodos SET remark='%s' WHERE id=%d;";
		final String where = "id=?";

		ContentValues contentValues = new ContentValues();
		contentValues.put(DatabaseHelper.TABLE_ALLTODOS_REMARK, remark);

		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase sqliteDatabase = databaseHelper.getReadableDatabase();
		boolean isSuccess = (sqliteDatabase.update(
				DatabaseHelper.TABLE_ALLTODOS, contentValues, where,
				new String[] { String.valueOf(allTodosId) }) > 0);
		sqliteDatabase.close();
		databaseHelper.close();
		return isSuccess;
	}

	@Override
	public boolean updateDueTime(int allTodosId, long dueTime) {

		// final String update =
		// "UPDATE allTodos SET dueTime='%s' WHERE id=%d;";
		final String where = "id=?";

		Date date = new Date(dueTime);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss", Locale.getDefault());
		String dueTimeFormat = dateFormat.format(date);

		ContentValues contentValues = new ContentValues();
		contentValues.put(DatabaseHelper.TABLE_ALLTODOS_DUETIME, dueTimeFormat);

		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase sqliteDatabase = databaseHelper.getReadableDatabase();
		boolean isSuccess = (sqliteDatabase.update(
				DatabaseHelper.TABLE_ALLTODOS, contentValues, where,
				new String[] { String.valueOf(allTodosId) }) > 0);
		sqliteDatabase.close();
		databaseHelper.close();
		return isSuccess;
	}

	@Override
	public boolean addDoneTime(int allTodosId, long doneTime) {

		// final String update =
		// "UPDATE allTodos SET doneTime='%s WHERE id=%d;";
		final String where = "id=?";

		Date date = new Date(doneTime);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss", Locale.getDefault());
		String doneTimeFormat = dateFormat.format(date);

		ContentValues contentValues = new ContentValues();
		contentValues.put(DatabaseHelper.TABLE_ALLTODOS_DONETIME,
				doneTimeFormat);

		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase sqliteDatabase = databaseHelper.getReadableDatabase();
		boolean isSuccess = (sqliteDatabase.update(
				DatabaseHelper.TABLE_ALLTODOS, contentValues, where,
				new String[] { String.valueOf(allTodosId) }) > 0);
		sqliteDatabase.close();
		databaseHelper.close();
		return isSuccess;
	}

	@Override
	public boolean deleteTodos(int allTodosId) {

		// final String update =
		// "UPDATE allTodos SET isDelete='%s' WHERE id=%d;";
		final String where = "id=?";

		ContentValues contentValues = new ContentValues();
		contentValues.put(DatabaseHelper.TABLE_ALLTODOS_ISDELETE, true);

		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase sqliteDatabase = databaseHelper.getReadableDatabase();
		boolean isSuccess = (sqliteDatabase.update(
				DatabaseHelper.TABLE_ALLTODOS, contentValues, where,
				new String[] { String.valueOf(allTodosId) }) > 0);
		sqliteDatabase.close();
		databaseHelper.close();
		return isSuccess;
	}

	@Override
	public boolean addInnerInterrupt(int todaysTodoId) {

		final String update = "UPDATE todaysTodos SET innerInterrupt=innerInterrupt+1 WHERE id=%d;";

		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase sqliteDatabase = databaseHelper.getReadableDatabase();
		boolean isSuccess = false;
		try {
			sqliteDatabase.execSQL(String.format(update, todaysTodoId));
			isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
			isSuccess = false;
		}
		sqliteDatabase.close();
		databaseHelper.close();
		return isSuccess;
	}

	@Override
	public boolean addOutterInterrupt(int todaysTodoId) {

		final String update = "UPDATE todaysTodos SET outterInterrupt=outterInterrupt+1 WHERE id=%d;";

		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase sqliteDatabase = databaseHelper.getReadableDatabase();
		boolean isSuccess = false;
		try {
			sqliteDatabase.execSQL(String.format(update, todaysTodoId));
			isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
			isSuccess = false;
		}
		sqliteDatabase.close();
		databaseHelper.close();
		return isSuccess;
	}

	@Override
	public boolean addTodaysTodo(int allTodoId, int firstEstimate) {

		// final String insert = 
		// 		"INSERT INTO todaysTodos(allTodoId, firstEstimate) VALUES(%d, %d);";

		ContentValues contentValues = new ContentValues();
		contentValues
				.put(DatabaseHelper.TABLE_TODAYSTODOS_ALLTODOID, allTodoId);
		contentValues.put(DatabaseHelper.TABLE_TODAYSTODOS_FIRSTESTIMATE,
				firstEstimate);

		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase sqliteDatabase = databaseHelper.getReadableDatabase();
		boolean isSuccess = (sqliteDatabase.insert(
				DatabaseHelper.TABLE_TODAYSTODOS, null, contentValues) != -1);
		sqliteDatabase.close();
		databaseHelper.close();
		return isSuccess;
	}

	@Override
	public boolean addTodaysTodoSecondEstimate(int todaysTodoId,
			int secondEstimate) {
		// String update = "UPDATE todaysTodos SET secondEstimate=%d WHERE id=%d;";
		final String where = "id=?";

		ContentValues contentValues = new ContentValues();
		contentValues.put(DatabaseHelper.TABLE_TODAYSTODOS_SECONDESTIMATE,
				secondEstimate);

		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase sqliteDatabase = databaseHelper.getReadableDatabase();
		boolean isSuccess = (sqliteDatabase.update(
				DatabaseHelper.TABLE_TODAYSTODOS, contentValues, where,
				new String[] { String.valueOf(todaysTodoId) }) > 0);
		sqliteDatabase.close();
		databaseHelper.close();
		return isSuccess;
	}

	@Override
	public boolean addTodaysTodoThirdEstimate(int todaysTodoId,
			int thirdEstimate) {
		// String update = "UPDATE todaysTodos SET thirdEstimate=%d WHERE id=%d;";
		final String where = "id=?";

		ContentValues contentValues = new ContentValues();
		contentValues.put(DatabaseHelper.TABLE_TODAYSTODOS_THIRDSTIMATE,
				thirdEstimate);

		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase sqliteDatabase = databaseHelper.getReadableDatabase();
		boolean isSuccess = (sqliteDatabase.update(
				DatabaseHelper.TABLE_TODAYSTODOS, contentValues, where,
				new String[] { String.valueOf(todaysTodoId) }) > 0);
		sqliteDatabase.close();
		databaseHelper.close();
		return isSuccess;
	}

	@Override
	public ArrayList<AllTodosItem> getAllUndoneTodos() {

		final String queryAllUndoneTodos = 
				"SELECT id, subject, remark, " +
				"addTime, dueTime, isUnPlaned " +
				"FROM allTodos " +
				"WHERE isDelete='false' " +
				"AND doneTime=0;";

		ArrayList<AllTodosItem> todaysTodoList = new ArrayList<AllTodosItem>();
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase sqliteDatabase = databaseHelper.getReadableDatabase();

		Cursor cursor = sqliteDatabase.rawQuery(queryAllUndoneTodos, null);

		int idIndex = cursor.getColumnIndex("id");
		int subjectIndex = cursor.getColumnIndex("subject");
		int remarkIndex = cursor.getColumnIndex("remark");
		int addTimeIndex = cursor.getColumnIndex("addTime");
		int dueTimeIndex = cursor.getColumnIndex("dueTime");
		int isUnPlanedIndex = cursor.getColumnIndex("isUnPlaned");

		int i = 0;
		for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
			AllTodosItem item = new AllTodosItem();

			item.setId(cursor.getInt(idIndex));
			item.setSubject(cursor.getString(subjectIndex));
			item.setRemark(cursor.getString(remarkIndex));
			item.setAddTime(cursor.getString(addTimeIndex));
			item.setDueTime(cursor.getString(dueTimeIndex));
			item.setUnPlaned(Boolean.parseBoolean(cursor.getString(isUnPlanedIndex)));
			item.setDueTime(cursor.getString(dueTimeIndex));
			item.setColor(RandomColorId.getColorId(i));

			todaysTodoList.add(item);
			i++;
		}

		// 关闭结果集和数据库
		cursor.close();
		sqliteDatabase.close();
		databaseHelper.close();
		return todaysTodoList;
	}

	@Override
	public ArrayList<AllTodosItem> getAllDoneTodos() {

		final String queryAllDoneTodos = 
				"SELECT id, subject, remark, " +
				"addTime, dueTime, doneTime, isUnPlaned " +
				"FROM allTodos " +
				"WHERE isDelete='false' " +
				"AND doneTime<>0 " +
				"ORDER BY doneTime DESC, id DESC;";

		ArrayList<AllTodosItem> todaysTodoList = new ArrayList<AllTodosItem>();
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase sqliteDatabase = databaseHelper.getReadableDatabase();

		Cursor cursor = sqliteDatabase.rawQuery(queryAllDoneTodos, null);

		int idIndex = cursor.getColumnIndex("id");
		int subjectIndex = cursor.getColumnIndex("subject");
		int remarkIndex = cursor.getColumnIndex("remark");
		int addTimeIndex = cursor.getColumnIndex("addTime");
		int dueTimeIndex = cursor.getColumnIndex("dueTime");
		int doneTimeIndex = cursor.getColumnIndex("doneTime");
		int isUnPlanedIndex = cursor.getColumnIndex("isUnPlaned");

		int i = 0;
		for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
			AllTodosItem item = new AllTodosItem();

			item.setId(cursor.getInt(idIndex));
			item.setSubject(cursor.getString(subjectIndex));
			item.setRemark(cursor.getString(remarkIndex));
			item.setAddTime(cursor.getString(addTimeIndex));
			item.setDueTime(cursor.getString(dueTimeIndex));
			item.setDoneTime(cursor.getString(doneTimeIndex));
			item.setUnPlaned(Boolean.parseBoolean(cursor.getString(isUnPlanedIndex)));
			item.setColor(RandomColorId.getColorId(i));

			todaysTodoList.add(item);
			i++;
		}

		// 关闭结果集和数据库
		cursor.close();
		sqliteDatabase.close();
		databaseHelper.close();
		return todaysTodoList;
	}
}
