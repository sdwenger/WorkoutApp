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

import java.util.ArrayList;

public class HighLevelView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_level_view);
        Cursor c;
        SQLiteDatabase mydatabase;
        if (AppWideResourceWrapper.sqlitedbIsSet()) {
            mydatabase = AppWideResourceWrapper.getSqlitedb();
        } else {
            mydatabase = openOrCreateDatabase(getString(R.string.table_workout), MODE_PRIVATE, null);
            AppWideResourceWrapper.setSqlitedb(mydatabase);
        }
        ArrayList<String> data = new ArrayList<String>(28);
        final Context context = this;
        c = mydatabase.rawQuery(getString(R.string.dayThemeQuery), null);
        if (c.moveToFirst()) {
            do {
                data.add(c.getString(c.getColumnIndex(getString(R.string.header_day))) + getString(R.string.dotspace) + c.getString(c.getColumnIndex(getString(R.string.header_theme))));
            } while (c.moveToNext());
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.listitem, data);

        GridView gridView = (GridView) findViewById(R.id.themeGrid);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra(getString(R.string.clickedDayExtra), position+1);
                startActivity(intent);
            }
        });
        final Button highLevelView = (Button) findViewById(R.id.jumpToDetails);
        highLevelView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(outercontext, MainActivity.class);
                outercontext.startActivity(intent);
                outercontext.finish();
            }
        });
        final Button setStartDateActivity = parent.findViewById(R.id.setStartDateButton);
        setStartDateActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(outercontext, SetStartDateActivity.class);
                outercontext.startActivity(intent);
            }
        });
    }
}
