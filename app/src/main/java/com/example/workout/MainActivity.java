package com.example.workout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Debug;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static String [] initialList = {
            staticGetString(R.string.header_title),
            staticGetString(R.string.header_sets),
            staticGetString(R.string.header_reps),
            staticGetString(R.string.header_weight)
    };
    SaveButtonClickListener saveListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = this;
        final NumberEnterListener dayNumberListener = new NumberEnterListener(this, NumberEnterListener.NumberContext.DAYNUMBER);

        final SQLiteDatabase mydatabase = AppWideResourceWrapper.getSqlitedb();

        DatabaseUpdater db = DatabaseUpdater.getInstance(mydatabase);
        db.start();

        final EditText edittext = (EditText) findViewById(R.id.myNumber);
        edittext.setOnKeyListener(dayNumberListener);

        int startDay = getIntent().getIntExtra(getString(R.string.clickedDayExtra), -1);
        if (startDay == -1) {
            setGridView(initialList, new Integer[0]);
        } else {
            edittext.setText(Integer.toString(startDay));
            dayNumberListener.onKey(findViewById(R.id.content), KeyEvent.KEYCODE_ENTER, (new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER)));
        }

        final Button highLevelView = (Button) findViewById(R.id.jumpToHighLevel);
        highLevelView.setOnClickListener(new View.OnClickListener() {public void onClick(View v) { Intent intent = new Intent(context, HighLevelView.class); startActivity(intent); }});

        final Button saveChanges = (Button) findViewById(R.id.saveChanges);
        saveListener = new SaveButtonClickListener(saveChanges);
        saveChanges.setOnClickListener(saveListener);

        Button prevDay = findViewById(R.id.prevDay);
        prevDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).incrementEditText(edittext, -1, 28);
                dayNumberListener.onKey(findViewById(R.id.content), KeyEvent.KEYCODE_ENTER, (new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER)));
            }
        });

        Button nextDay = findViewById(R.id.nextDay);
        nextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).incrementEditText(edittext, 1, 1);
                dayNumberListener.onKey(findViewById(R.id.content), KeyEvent.KEYCODE_ENTER, (new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER)));
            }
        });
    }

    protected void setGridView(final String[] workoutList, final Integer[] rowIds) {
        final int numberOfCells = workoutList.length;
        final MainActivity context = this;

        Thread setView = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Rule<Integer> layouts = new Rule<Integer>() {
                    @Override
                    public Integer getIndex(int position) {
                        return (position%4==3&&position!=3)?R.layout.listitementernumber:R.layout.listitem;
                    }

                    @Override
                    public int getSize() {
                        return numberOfCells;
                    }
                };
                Rule<Integer> rowIdRule = new Rule<Integer>() {
                    @Override
                    public Integer getIndex(int position) {
                        if (position < 4) {
                            return 0;
                        }
                        int rowNumber = (position/4)-1;
                        return rowIds[rowNumber];
                    }

                    @Override
                    public int getSize() {
                        return numberOfCells;
                    }
                };
                final TableAdapter adapter = new TableAdapter(new CustomList<Integer>(layouts), new CustomList<String>(workoutList), new CustomList<Integer>(rowIdRule), context);

                runOnUiThread(new Thread() {
                    @Override
                    public void run() {
                        GridView gridView = (GridView) findViewById(R.id.workoutGrid);
                        gridView.setAdapter(adapter);
                    }
                });
            }
        };
        setView.start();
    }

    protected void setGridView(ArrayList<String> workoutList, ArrayList<Integer> rowIds) {
        setGridView(toStringArray(workoutList), toIntegerArray(rowIds));
    }

    public void incrementEditText(EditText target, int incValue, int initializeIfEmpty) {
        String prior = target.getText().toString();
        int after;
        if (prior.isEmpty()) {
            after = initializeIfEmpty;
        } else {
            int before;
            before = Integer.parseInt(prior);
            after = before + incValue;
        }
        target.setText(Integer.toString(after));
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String[] toStringArray(List<String> list) {
        String [] result = new String [list.size()];
        for (int i = 0; i < list.size(); i ++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public static Integer[] toIntegerArray(List<Integer> list) {
        Integer [] result = new Integer [list.size()];
        for (int i = 0; i < list.size(); i ++) {
            result[i] = list.get(i);
        }
        return result;
    }
}