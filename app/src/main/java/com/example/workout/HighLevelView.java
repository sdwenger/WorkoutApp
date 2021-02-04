package com.example.workout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HighLevelView extends AppCompatActivity {

    GridView gridView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final HighLevelView outercontext = this;
        setContentView(R.layout.activity_high_level_view);
        HighLevelInitViewThread thread = new HighLevelInitViewThread(this);
        thread.start();
        final Button highLevelView = findViewById(R.id.jumpToDetails);
        highLevelView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(outercontext, MainActivity.class);
                outercontext.startActivity(intent);
                outercontext.finish();
            }
        });
        final Button setStartDateActivity = findViewById(R.id.setStartDateButton);
        setStartDateActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(outercontext, SetStartDateActivity.class);
                outercontext.startActivity(intent);
            }
        });
    }
}

class HighLevelInitViewThread extends Thread {

    private HighLevelView parent = null;

    public HighLevelInitViewThread(HighLevelView parent) {
        this.parent = parent;
    }

    public void run() {
        final HighLevelView outercontext = this.parent;
        Cursor c = AppWideResourceWrapper.getSqlitedb().rawQuery(AppWideResourceWrapper.staticGetString(R.string.dayThemeQuery), null);
        List<Integer> dayNumbers = new ArrayList<Integer>(28);
        List<String> themes = new ArrayList<String>(28);
        if (c.moveToFirst()) {
            do {
                Integer dayNumber = c.getInt(c.getColumnIndex(AppWideResourceWrapper.staticGetString(R.string.header_day)));
                String theme = c.getString(c.getColumnIndex(AppWideResourceWrapper.staticGetString(R.string.header_theme)));
                dayNumbers.add(dayNumber);
                themes.add(theme);
            } while (c.moveToNext());
        }

        final HighLevelAdapter adapter = new HighLevelAdapter(dayNumbers, themes, parent);

        parent.runOnUiThread(new Runnable() {
             @Override
             public void run() {
                 outercontext.gridView = (GridView) outercontext.findViewById(R.id.themeGrid);
                 outercontext.gridView.setAdapter(adapter);
                 outercontext.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                     @Override
                     public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                         Intent intent = new Intent(outercontext, MainActivity.class);
                         int dayNumber = Integer.valueOf(((TextView) view.findViewById(R.id.hiddenInfo)).getText().toString());
                         intent.putExtra(AppWideResourceWrapper.staticGetString(R.string.clickedDayExtra), dayNumber);
                         outercontext.startActivity(intent);
                         outercontext.finish();
                     }
                 });
             }
        });
    }
}
