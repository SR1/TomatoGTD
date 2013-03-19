package com.roslab.app.tomatogtd.database;

import java.util.Date;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final String Table_FinishEvent = "finishEvent";
	public static final String FinalEvent_StartTime = "startTime";
	public static final String FinalEvent_EndTime = "endTime";
	public static final String FinalEvent_Event = "event";
	
    private static final String DATABASE_NAME = "tomato.db";
    private static final int DATABASE_VERSION = 4;
    
    public static String insertMe = "INSERT INTO finishEvent " +
    								"(startTime, endTime, event) VALUES ";
    public static String selectMe = "INSERT (startTime, endTime, event) FROM " +
    								"finishEvent";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS finishEvent (" +
                   "id INTEGER PRIMARY KEY," +
                   "startTime INTEGER, " +
                   "endTime INTEGER, " +
                   "event TEXT);");
        
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion,
            int currentVersion)
    {
    	//更新数据库时删除旧有数据
    	//TODO 更新数据库时备份现有数据库内容
        db.execSQL("DROP TABLE IF EXISTS finishEvent");
        onCreate(db);
    }
}
