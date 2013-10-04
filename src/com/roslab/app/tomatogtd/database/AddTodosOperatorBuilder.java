package com.roslab.app.tomatogtd.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/***
 * 添加待办操作
 * 
 * @author SR1
 * 
 */
public class AddTodosOperatorBuilder {
	String subject;
	String remark;
	long dueTime;
	boolean isUnPlaned;

	public AddTodosOperatorBuilder(String subject) throws CannotBeNullException {
		if (subject == null)
			throw new CannotBeNullException() ;
		this.subject = subject;
		this.remark = "";
		this.dueTime = 0;
		this.isUnPlaned = false;
	}

	/***
	 * 添加备注
	 * 
	 * @param remark
	 *            备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/***
	 * 添加截止日期
	 * 
	 * @param dueTime
	 *            截止时间
	 */
	public void setDueTime(long dueTime) {
		this.dueTime = dueTime;
	}

	/***
	 * 是否计划外事件
	 * 
	 * @param isUnPlaned
	 *            是否计划外事件
	 */
	public void setIsUnPlaned(boolean isUnPlaned) {
		this.isUnPlaned = isUnPlaned;
	}

	/***
	 * 执行插入操作
	 * 
	 * @return 操作成功与否
	 */
	public final boolean excute(Context context) {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase sqliteDatabase = databaseHelper.getWritableDatabase();

		ContentValues param = new ContentValues();
		param.put(DatabaseHelper.TABLE_ALLTODOS_SUBJECT, subject);
		param.put(DatabaseHelper.TABLE_ALLTODOS_REMARK, remark);
		if (dueTime != 0)
			param.put(DatabaseHelper.TABLE_ALLTODOS_DUETIME, dueTime);
		param.put(DatabaseHelper.TABLE_ALLTODOS_ISUNPLANED, isUnPlaned);

		boolean isSuccess = sqliteDatabase.insert(
				DatabaseHelper.TABLE_ALLTODOS, null, param) != -1;

		sqliteDatabase.close();
		databaseHelper.close();

		return isSuccess;
	}
	public class CannotBeNullException extends Exception{
		private static final long serialVersionUID = 5942129768080627550L;
	}
}
