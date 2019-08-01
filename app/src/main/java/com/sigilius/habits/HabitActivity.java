package com.sigilius.habits;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sigilius.habits.db.TaskContract;
import com.sigilius.habits.db.TaskDbHelper;

import java.sql.RowId;


public class HabitActivity extends AppCompatActivity {
    private static final String LOG_TAG = HabitActivity.class.getName();

    TaskDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habit_activity);

        mDbHelper = new TaskDbHelper(this);

        insertRow("2016-08-22", "Lots of meds", 11);
    }

    /**
     * There is a single insert method that adds at least two values of two different
     datatypes (e.g. INTEGER, STRING) into the database using a ContentValues object
     and the insert() method.
     */
    public void insertRow(String date, String medications, int hour) {

        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.COLUMN_NAME_DATE, date);
        values.put(TaskContract.TaskEntry.COLUMN_NAME_MEDICATIONS, medications);
        values.put(TaskContract.TaskEntry.COLUMN_NAME_HOUR_TAKEN, hour);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                TaskContract.TaskEntry.TABLE_NAME,
                TaskContract.TaskEntry.COLUMN_NAME_NULLABLE,
                values);

        db.close();
    }

    /**
     * There is a single read method that returns a Cursor object.
     It should get the data repository in read and use the query() method
     to retrieve at least one column of data.
     */
    public Cursor readDb(String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                TaskContract.TaskEntry._ID,
                TaskContract.TaskEntry.COLUMN_NAME_DATE,
                TaskContract.TaskEntry.COLUMN_NAME_UPDATED };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                TaskContract.TaskEntry.COLUMN_NAME_UPDATED + " DESC";

        Cursor c = db.query(
                TaskContract.TaskEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        db.close();
        return c;
    }


    /**
     * There is a single delete method that deletes all the entries from the table.
     */
    public void deleteEntries () {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + TaskContract.TaskEntry.TABLE_NAME);
    }

    /**
     * There is a single update method that updates at least one value in one column in the table.
     */

     public void updateDb (String date, RowId rowId) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.COLUMN_NAME_DATE, date);

        // Which row to update, based on the ID
        String selection = TaskContract.TaskEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(rowId) };

        int count = db.update(
        TaskContract.TaskEntry.TABLE_NAME,
            values,
            selection,
            selectionArgs);

     }

}
