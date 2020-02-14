package com.example.workout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
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
    SaveButtonClickListener saveListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = this;
        final NumberEnterListener dayNumberListener = new NumberEnterListener(this, NumberEnterListener.NumberContext.DAYNUMBER);

        SQLiteDatabase mydatabase;
        if (AppWideResourceWrapper.sqlitedbIsSet()) {
            mydatabase = AppWideResourceWrapper.getSqlitedb();
        } else {
            mydatabase = openOrCreateDatabase(getString(R.string.table_workout), MODE_PRIVATE, null);
            AppWideResourceWrapper.setSqlitedb(mydatabase);
        }
        if (false) {
            InputStream sqlStream = null;
            BufferedReader sqlReader = null;
            StringWriter writer = null;
            try {
                sqlStream = context.getResources().openRawResource(R.raw.workouts);
                sqlReader = new BufferedReader(new InputStreamReader(sqlStream, getString(R.string.charSetUTF8)));
                writer = new StringWriter();
                char[] buffer = new char[2048];
                int n;
                while ((n = sqlReader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
                String rawSql = writer.toString();
                String[] dbSetup = rawSql.split(getString(R.string.newline));
                for (String s: dbSetup) {
                    mydatabase.execSQL(s);
                }
                sqlStream.close();
                sqlReader.close();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
            }
        }

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
        saveChanges.setOnClickListener(new SaveButtonClickListener(saveChanges));

        Button prevDay = findViewById(R.id.prevDay);
        prevDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).incrementEditText(edittext, -1, 28);
            }
        });

        Button nextDay = findViewById(R.id.nextDay);
        nextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).incrementEditText(edittext, 1, 1);
            }
        });
    }

    public static String staticGetString(int locator) {
        return AppWideResourceWrapper.getGlobalContext().getString(locator);
    }

    protected void setGridView(String[] workoutList, final Integer[] rowIds) {
        final int numberOfCells = workoutList.length;

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
        TableAdapter adapter = new TableAdapter(new CustomList<Integer>(layouts), new CustomList<String>(workoutList), new CustomList<Integer>(rowIdRule), this);

        GridView gridView = (GridView) findViewById(R.id.workoutGrid);
        gridView.setAdapter(adapter);
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
        BaseInputConnection inputConnection = new BaseInputConnection(target, true);
        inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
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