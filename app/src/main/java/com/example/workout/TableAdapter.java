package com.example.workout;

import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TableAdapter implements ListAdapter {

    private TableItem[] cells;
    private Set<DataSetObserver> observers;
    private AppCompatActivity context;

    public TableAdapter(List<Integer> layouts, List<String> contents, List<Integer> rowIds, AppCompatActivity context) {
        int length = Math.min(layouts.size(), contents.size());
        cells = new TableItem[length];
        for (int i = 0; i < length; i ++) {
            cells[i] = new TableItem(layouts.get(i), contents.get(i), rowIds.get(i));
        }
        observers = new HashSet<>();
        this.context = context;
    }

    @Override
    public boolean areAllItemsEnabled() {
        for (int i = 0; i < cells.length; i++) {
            if (!isEnabled(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        TableItem cell = cells[position];
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        observers.remove(observer);
    }

    @Override
    public int getCount() {
        return cells.length;
    }

    @Override
    public Object getItem(int position) {
        return cells[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        TableItem item = (TableItem) getItem(position);
        if (convertView == null) {
            convertView = inflater.inflate(item.layout, parent, false);
            switch (item.layout) {
                case R.layout.listitem:
                    TextView text = convertView.findViewById(R.id.label);
                    text.setText(item.data);
                    break;
                case R.layout.listitementernumber:
                    EditText editor = convertView.findViewById(R.id.enteredNumber);
                    editor.setText(item.data);
                    editor.setOnKeyListener(new NumberEnterListener(context, NumberEnterListener.NumberContext.WEIGHTEDIT));
                    TextView rowIdAccess = convertView.findViewById(R.id.rowId);
                    rowIdAccess.setText(Integer.toString(item.rowId));
                    break;
            }
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return cells[position].layout==R.layout.listitementernumber?1:0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return getCount()==0;
    }
}

class TableItem {
    int layout;
    String data;
    int rowId;

    public TableItem(int layout, String data, int rowId) {
        this.layout = layout;
        this.data = data;
        this.rowId = rowId;
    }
}
