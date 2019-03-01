package com.example.kb.lab5_2.ui;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kb.lab5_2.R;
import com.example.kb.lab5_2.model.ToDoItem;
import com.example.kb.lab5_2.model.ToDoModel;
import com.example.kb.lab5_2.model.db.ToDoItemContract;
import com.example.kb.lab5_2.model.db.ToDoItemDbHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<ToDoItem> toDoList = new ArrayList<>();
    ToDoModel model = null;
    ListView listView = null;
    ToDoItemArrayAdapter adapter;
    private int sortingMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(getBaseContext(), AddNewActivity.class);
                startActivity(intent);
            }
        });
        listView = findViewById(R.id.list_view);
        model = new ToDoModel(this);

        final ListView listView = findViewById(R.id.list_view);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                final int index = pos;
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Remove item");
                alertDialog.setMessage("Do you really want to remove this item? ");
                alertDialog.setPositiveButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        model.removeItemFromDb(toDoList.get(index));
                        Log.v("long clicked","pos: " + index);
                        updateList(sortingMode);
                    }
                });
                alertDialog.show();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new ToDoItemArrayAdapter(this, toDoList);
        listView.setAdapter(adapter);
        updateList(sortingMode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.order_by_date) {
            sortingMode = 1;
            updateList(sortingMode);
            return true;
        } else if (id == R.id.order_by_added) {
            sortingMode = 0;
            updateList(sortingMode);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateList(int mode) {
        toDoList.clear();
        Cursor cursor = model.getListItems();
        if(mode == 0) {
            cursor = model.getListItems();
        } else if (mode == 1) {
            cursor = model.getListItemsByDate();
        }

        if (cursor.getColumnCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    String title = cursor.getString(cursor.getColumnIndex(ToDoItemContract.FeedEntry.COLUMN_NAME_TITLE));
                    String desc = cursor.getString(cursor.getColumnIndex(ToDoItemContract.FeedEntry.COLUMN_NAME_DESCRIPTION));
                    long dDate = cursor.getLong(cursor.getColumnIndex(ToDoItemContract.FeedEntry.COLUMN_NAME_DUEDATE));
                    Log.d("DATE", dDate + "");
                    ToDoItem itemToAdd = new ToDoItem(title,desc,dDate);
                    toDoList.add(itemToAdd);
                    Log.d("DB", title + " " + desc + " " + dDate);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }

}
