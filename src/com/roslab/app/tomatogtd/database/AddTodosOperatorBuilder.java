package com.roslab.app.tomatogtd.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/***
 * ��Ӵ������
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
	 * ��ӱ�ע
	 * 
	 * @param remark
	 *            ��ע
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/***
	 * ��ӽ�ֹ����
	 * 
	 * @param dueTime
	 *            ��ֹʱ��
	 */
	public void setDueTime(long dueTime) {
		this.dueTime = dueTime;
	}

	/***
	 * �Ƿ�ƻ����¼�
	 * 
	 * @param isUnPlaned
	 *            �Ƿ�ƻ����¼�
	 */
	public void setIsUnPlaned(boolean isUnPlaned) {
		this.isUnPlaned = isUnPlaned;
	}

	/***
	 * ִ�в������
	 * 
	 * @return �����ɹ����
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
