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

    String creation = "CREATE TABLE IF NOT EXISTS DayThemes (Day INT, Theme VARCHAR(22))\n";
    String [] insertion = ("INSERT INTO DayThemes (Day, Theme) VALUES (1, 'Full Body')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (2, 'Rest')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (3, 'Full Body')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (4, 'Rest')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (5, 'Full Body')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (6, 'Rest')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (7, 'Rest')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (8, 'Upper Body')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (9, 'Lower Body')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (10, 'Rest')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (11, 'Upper Body')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (12, 'Lower Body')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (13, 'Rest')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (14, 'Rest')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (15, 'Push')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (16, 'Pull')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (17, 'Legs')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (18, 'Push')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (19, 'Pull')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (20, 'Legs')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (21, 'Rest')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (22, 'Chest, Triceps, Calves')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (23, 'Legs &amp; Abs')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (24, 'Rest')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (25, 'Shoulders &amp; Calves')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (26, 'Back, Biceps &amp; Abs')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (27, 'Rest')\n" +
            "INSERT INTO DayThemes (Day, Theme) VALUES (28, 'Rest')").split("\n");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = this;
        ArrayList<String> data = new ArrayList<String>(28);
        setContentView(R.layout.activity_high_level_view);
        SQLiteDatabase mydatabase = openOrCreateDatabase("Workouts",MODE_PRIVATE,null);
        mydatabase.execSQL(creation);
        Cursor c = mydatabase.rawQuery("SELECT Day, Theme FROM DayThemes ORDER BY Day", null);
        if (!c.moveToFirst()) {
            for (String s: insertion) {mydatabase.execSQL(s);}
            c = mydatabase.rawQuery("SELECT Day, Theme FROM DayThemes ORDER BY Day", null);
        }
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
        highLevelView.setOnClickListener(new View.OnClickListener() {public void onClick(View v) { Intent intent = new Intent(context, MainActivity.class); startActivity(intent); }});

        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
}
