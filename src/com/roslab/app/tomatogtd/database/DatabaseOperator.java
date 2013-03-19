package com.roslab.app.tomatogtd.database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;

public class DatabaseOperator
{
	private DatabaseHelper dbh;
	private SQLiteDatabase db;
	
	
	private static final long A_DAY_LONG = 24*60*1000;
	
	//���캯��
	public DatabaseOperator(Context context)
	{
		dbh = new DatabaseHelper(context);
	}
	
	//��ѯ����ɷ�����
	//TODO ��ѯ������ɷ�����
	public ArrayList<String> queryTodayEvent()
	{
		Date today = new Date();
		
		//��ȡÿ�տ�ʼ��ʱ��ͽ���ʱ��
		long start = new Date(today.getYear(),today.getMonth(),today.getDay()).getTime();
		long end = start + A_DAY_LONG;
		
		db = dbh.getReadableDatabase();
		
		//TODO ���ݿ��ѯ����
		String selection = null;//"startTime>? AND endTime<?";
		String[] selectionArg =null;// {""+start,""+end};
		
		Cursor cursor = db.query(DatabaseHelper.Table_FinishEvent, null, selection,
				selectionArg, null, null, "id desc");

		int startIndex = cursor.getColumnIndex(DatabaseHelper.FinalEvent_StartTime);
		int endIndex = cursor.getColumnIndex(DatabaseHelper.FinalEvent_EndTime);
		int eventIndex = cursor.getColumnIndex(DatabaseHelper.FinalEvent_Event);
		
		//TODO ��ѯ���
		ArrayList<String> result = new ArrayList<String>();
		for (cursor.moveToFirst();!(cursor.isAfterLast());cursor.moveToNext())
		{
			long startTime = cursor.getLong(startIndex);
			long endTime   = cursor.getLong(endIndex);
			String event   = cursor.getString(eventIndex);
			
			result.add(tansToFormalResult(startTime, endTime, event));
		}
		
		//�رս���������ݿ�
		cursor.close();
		db.close();
		return result;
	}
	
	//�����·���
	public void insertNewEvent(ContentValues values)
	{
		
		db = dbh.getWritableDatabase();
		db.insert(DatabaseHelper.Table_FinishEvent, "id", values);
		db.close();
	}
	
	//TODO ������������
	public void insertSampleData()
	{
		db = dbh.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(DatabaseHelper.FinalEvent_StartTime,2580004);
		cv.put(DatabaseHelper.FinalEvent_EndTime,  3808524);
		cv.put(DatabaseHelper.FinalEvent_Event,"��������1");
		db.insert(DatabaseHelper.Table_FinishEvent, "id", cv);
		cv.clear();
		cv.put(DatabaseHelper.FinalEvent_StartTime,2580004);
		cv.put(DatabaseHelper.FinalEvent_EndTime,  3808524);
		cv.put(DatabaseHelper.FinalEvent_Event,"��������2");
		db.insert(DatabaseHelper.Table_FinishEvent, "id", cv);
		db.close();
	}
	
	private String tansToFormalResult(long startTime,long endTime,String event)
	{
		StringBuilder re = new StringBuilder();
		re.append(transTimeToFormal(startTime));
		re.append("-");
		re.append(transTimeToFormal(endTime));
		re.append(":");
		re.append(event);
		re.append("  ���!");
		return re.toString();
	}
	
	//��ʱ��ת��Ϊ��׼��ʾ��ʽ
	private String transTimeToFormal(long militime)
	{
		
		Date day = new Date(militime);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
		return sdf.format(day);
	}
	
}
