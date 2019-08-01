package com.sigilius.habits.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class TaskDbHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = TaskDbHelper.class.getName();
    public static final String DB_NAME = "com.sigilius.habits.db";
    public static final int DB_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TaskContract.TaskEntry.TABLE_NAME + " (" +
            TaskContract.TaskEntry._ID + " INTEGER_PRIMARY_KEY," +
            TaskContract.TaskEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
            TaskContract.TaskEntry.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP +
            TaskContract.TaskEntry.COLUMN_NAME_MEDICATIONS + TEXT_TYPE + COMMA_SEP +
            TaskContract.TaskEntry.COLUMN_NAME_HOUR_TAKEN  + " INTEGER )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TaskContract.TaskEntry.TABLE_NAME;

    public TaskDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void deleteDatabase(String DB_NAME) {
        this.deleteDatabase(DB_NAME);
    }

}
