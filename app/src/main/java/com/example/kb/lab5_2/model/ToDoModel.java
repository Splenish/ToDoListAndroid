package com.example.kb.lab5_2.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kb.lab5_2.model.db.ToDoItemContract;
import com.example.kb.lab5_2.model.db.ToDoItemDbHelper;

public class ToDoModel {

    ToDoItemDbHelper mDbHelper = null;

    public ToDoModel(Context context) {
        this.mDbHelper = new ToDoItemDbHelper(context);
    }

    public void addItemToDb(ToDoItem addable) {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ToDoItemContract.FeedEntry.COLUMN_NAME_TITLE, addable.title);
        values.put(ToDoItemContract.FeedEntry.COLUMN_NAME_DESCRIPTION, addable.description);
        values.put(ToDoItemContract.FeedEntry.COLUMN_NAME_DUEDATE, addable.dueDate);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ToDoItemContract.FeedEntry.TABLE_NAME, null, values);
    }

    public void removeItemFromDb(ToDoItem removable) {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String query = "select * from ToDoItems where name = '" + removable.title + "' and Description = '" + removable.description + "'";
        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        String id = res.getString(res.getColumnIndex(ToDoItemContract.FeedEntry._ID));
        //int id = res.getColumnIndex(ToDoItemContract.FeedEntry.COLUMN_NAME_TITLE);
        Log.d("db-remove", "type: " + id);

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        String selection = ToDoItemContract.FeedEntry._ID + " LIKE ?";
        String[] selectionArgs = {id};
        int deletedRows = db.delete(ToDoItemContract.FeedEntry.TABLE_NAME, selection, selectionArgs);
    }


    public Cursor getListItems(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor res = db.rawQuery("select * from ToDoItems", null);
        return res;
    }

    public Cursor getListItemsByDate() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor res = db.rawQuery("select * from ToDoItems order by DueDate asc", null);
        return res;
    };


}
