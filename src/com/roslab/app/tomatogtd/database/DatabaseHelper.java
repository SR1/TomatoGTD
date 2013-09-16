package com.roslab.app.tomatogtd.database;

import java.util.Date;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	public static final String TAG = "DatabaseHelper";

	public static final String Table_FinishEvent = "finishEvent";
	public static final String FinalEvent_StartTime = "startTime";
	public static final String FinalEvent_EndTime = "endTime";
	public static final String FinalEvent_Event = "event";
	
	public static final String Table_TodaysTodo = "todaysTodo";
	
	public static final String Table_AllTodo = "allTodo";
	public static final String AllTodo_Title = "title";
	public static final String AllTodo_Remark = "remark";
	
	
	
    private static final String DATABASE_NAME = "tomato.db";
    private static final int DATABASE_VERSION = 5;
    
    public static String insertMe = "INSERT INTO finishEvent " +
    								"(startTime, endTime, event) VALUES ";
    public static String selectMe = "INSERT (startTime, endTime, event) FROM " +
    								"finishEvent";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
    	Log.v(TAG, "onCreate--->");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Table_AllTodo + " (" +
                   "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                   "title String, " +
                   "remark TEXT);");
        
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion,
            int currentVersion)
    {
    	Log.v(TAG, "onUpgrade--->");
    	//更新数据库时删除旧有数据
    	//TODO 更新数据库时备份现有数据库内容
        db.execSQL("DROP TABLE IF EXISTS " + Table_FinishEvent);
        db.execSQL("DROP TABLE IF EXISTS " + Table_TodaysTodo);
        db.execSQL("DROP TABLE IF EXISTS " + Table_AllTodo);
        onCreate(db);
    }
}
