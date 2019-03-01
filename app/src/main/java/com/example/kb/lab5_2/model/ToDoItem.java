package com.example.kb.lab5_2.model;

import android.util.Log;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class ToDoItem implements Serializable {
    String title;
    String description;
    //String dueDate;
    long dueDate;

    public ToDoItem(String title_, String description_, long dueDate_) {
        this.title = title_;
        this.description = description_;
        this.dueDate = dueDate_;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueDateAsString() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dueDate);
        Log.d("DATE", dueDate + "");
        String day = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
        String month = Integer.toString(cal.get(Calendar.MONTH)+1);
        String year = Integer.toString(cal.get(Calendar.YEAR));
        String date = day + "." + month + "." + year;
        return date;
    }
}
