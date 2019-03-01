package com.example.kb.lab5_2.model.db;

import android.provider.BaseColumns;

public final class ToDoItemContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private ToDoItemContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "ToDoItems";
        public static final String COLUMN_NAME_TITLE = "Name";
        public static final String COLUMN_NAME_DUEDATE = "DueDate";
        public static final String COLUMN_NAME_DESCRIPTION = "Description";
    }
}