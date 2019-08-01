package com.sigilius.habits;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.util.Log;

import com.sigilius.habits.db.TaskContract;
import com.sigilius.habits.db.TaskDbHelper;

public class DatabaseTest extends AndroidTestCase {
    private TaskDbHelper db;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        db = new TaskDbHelper(context);
    }

    @Override
    public void tearDown() throws Exception {
        db.close();
        super.tearDown();
    }

    public void testDropDb(){
        assertTrue(mContext.deleteDatabase(TaskDbHelper.DB_NAME));
    }

    public void testCreateDb() {
        TaskDbHelper taskDbHelper = new TaskDbHelper(mContext);
        SQLiteDatabase db = taskDbHelper.getWritableDatabase();
        assertTrue(db.isOpen());
        db.close();
    }

}