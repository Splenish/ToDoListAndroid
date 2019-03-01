package com.example.kb.lab5_2.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kb.lab5_2.R;
import com.example.kb.lab5_2.model.ToDoItem;

import java.util.ArrayList;

public class ToDoItemArrayAdapter extends ArrayAdapter<ToDoItem> {

    public ToDoItemArrayAdapter(Context context, ArrayList<ToDoItem> listItems) {
        super(context, 0,listItems);
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ToDoItem item = getItem(position);
        if(convertView == null) {
            int layoutId = 0;
            layoutId = R.layout.todo_list_item;
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }
        TextView title = convertView.findViewById(R.id.title);
        TextView description = convertView.findViewById(R.id.description);
        TextView dueDate = convertView.findViewById(R.id.due_date);
        title.setText(String.valueOf(item.getTitle()));
        description.setText(String.valueOf(item.getDescription()));
        //dueDate.setText(String.valueOf(item.getDueDate()));
        dueDate.setText(String.valueOf(item.getDueDateAsString()));
        return convertView;

    }
}
