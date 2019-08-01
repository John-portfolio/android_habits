package com.sigilius.habits.db;


import android.provider.BaseColumns;

public final class TaskContract {

    // Prevent accidental instantiation of the class
    public TaskContract() {}

    /* Inner class defining table contents */
    public class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "medication";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_MEDICATIONS = "medications";
        public static final String COLUMN_NAME_HOUR_TAKEN = "hour";
        public static final String COLUMN_NAME_UPDATED = "date";
        public static final String COLUMN_NAME_NULLABLE = "";

    }

}
