package com.example.kb.lab5_2.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.kb.lab5_2.R;
import com.example.kb.lab5_2.model.ToDoItem;
import com.example.kb.lab5_2.model.ToDoModel;

import java.util.Calendar;

public class AddNewActivity extends AppCompatActivity {

    ToDoModel model = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        model = new ToDoModel(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save_list_item) {
            EditText nameInput = findViewById(R.id.name_input);
            EditText descInput = findViewById(R.id.description_input);
            DatePicker dueDate = findViewById(R.id.dueDate);
            String title = nameInput.getText().toString();
            String description = descInput.getText().toString();
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, dueDate.getDayOfMonth());
            cal.set(Calendar.MONTH, dueDate.getMonth());
            cal.set(Calendar.YEAR, dueDate.getYear());
            long date = cal.getTimeInMillis();
            Log.d("DATE", date + "");
            ToDoItem itemToAdd = new ToDoItem(title, description, date);
            model.addItemToDb(itemToAdd);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
