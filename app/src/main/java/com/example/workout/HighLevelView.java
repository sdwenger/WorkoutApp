package com.example.workout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
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
        SQLiteDatabase mydatabase = AppWideResourceWrapper.getSqlitedb();
        ArrayList<String> data = new ArrayList<String>(28);
        final Context context = this;
        c = mydatabase.rawQuery("SELECT Day, Theme FROM DayThemes ORDER BY Day", null);
        if (c.moveToFirst()) {
            do {
                data.add(c.getString(c.getColumnIndex("Day")) + ". " + c.getString(c.getColumnIndex("Theme")));
            } while (c.moveToNext());
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.listitem, data);

        GridView gridView = (GridView) findViewById(R.id.themeGrid);
        gridView.setAdapter(adapter);
        final Button highLevelView = (Button) findViewById(R.id.jumpToDetails);
        highLevelView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
