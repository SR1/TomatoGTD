package com.roslab.app.tomatogtd.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	public static final String TAG = "DatabaseHelper";
	
    private static final String DATABASE_NAME = "tomato.db";
    private static final int DATABASE_VERSION = 0;
	
	public static final String TABLE_ALLTODOS = "allTodos";
	public static final String TABLE_ALLTODOS_ID = "id";
	public static final String TABLE_ALLTODOS_SUBJECT = "subject";
	public static final String TABLE_ALLTODOS_REMARK = "remark";
	public static final String TABLE_ALLTODOS_ADDTIME = "addTime";
	public static final String TABLE_ALLTODOS_DUETIME = "dueTime";
	public static final String TABLE_ALLTODOS_DONETIME = "doneTime";
	public static final String TABLE_ALLTODOS_ISUNPLANED = "isUnPlaned";
	public static final String TABLE_ALLTODOS_ISDELETE = "isDelete";
	
	private static final String CREATE_TABLE_ALLTODOS =
			"CREATE TABLE IF NOT EXISTS allTodos (" +
			"id INTEGER PRIMARY KEY, " +
			"subject TEXT NOT NULL, " +
			"remark TEXT NOT NULL DEFAULT '', " +
			"addTime DATETIME NOT NULL DEFAULT (datetime('now','localtime')), " +
			"dueTime DATETIME NOT NULL DEFAULT 0, " +
			"doneTime DATETIME NOT NULL DEFAULT 0, " +
			"isUnPlaned BOOLEAN DEFAULT false, " +
			"isDelete BOOLEAN DEFAULT false);";
	
	public static final String TABLE_TODAYSTODOS = "todaysTodos";
	public static final String TABLE_TODAYSTODOS_ID = "id";
	public static final String TABLE_TODAYSTODOS_ALLTODOID = "allTodoId";
	public static final String TABLE_TODAYSTODOS_DATE = "date";
	public static final String TABLE_TODAYSTODOS_FIRSTESTIMATE = "firstEstimate";
	public static final String TABLE_TODAYSTODOS_SECONDESTIMATE = "secondEstimate";
	public static final String TABLE_TODAYSTODOS_THIRDSTIMATE = "thirdEstimate";
	public static final String TABLE_TODAYSTODOS_FINISHNUMBER = "finishNumber";
	public static final String TABLE_TODAYSTODOS_INNERINTERRUPT = "innerInterrupt";
	public static final String TABLE_TODAYSTODOS_OUTTERINTERRUPT = "outterInterrupt";
	
	private static final String CREATE_TABLE_TODAYSTODOS =
		"CREATE TABLE IF NOT EXISTS todaysTodos (" +
		"id INTEGER PRIMARY KEY, " +
		"allTodoId INTEGER NOT NULL REFERENCES  allTodos(id), " +
		"date DATE NOT NULL DEFAULT (date('now','localtime')), " +
		"firstEstimate INTEGER NOT NULL DEFAULT 0, " +
		"secondEstimate INTEGER NOT NULL DEFAULT 0, " +
		"thirdEstimate INTEGER NOT NULL DEFAULT 0, " +
		"finishNumber INTEGER NOT NULL DEFAULT 0, " +
		"innerInterrupt INTEGER NOT NULL DEFAULT 0, " +
		"outterInterrupt INTEGER NOT NULL DEFAULT 0);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
    	Log.v(TAG, "onCreate--->");
        db.execSQL(CREATE_TABLE_ALLTODOS);
        db.execSQL(CREATE_TABLE_TODAYSTODOS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion,
            int currentVersion)
    {
    	Log.v(TAG, "onUpgrade--->");
    	//更新数据库时删除旧有数据
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALLTODOS);
        onCreate(db);
    }
}
