package com.example.workout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
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

    static String [] initialList = {"Title","Sets","Reps","Weight"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = this;

        SQLiteDatabase mydatabase;
        if (AppWideResourceWrapper.sqlitedbIsSet()) {
            mydatabase = AppWideResourceWrapper.getSqlitedb();
        } else {
            mydatabase = openOrCreateDatabase("Workouts", MODE_PRIVATE, null);
            AppWideResourceWrapper.setSqlitedb(mydatabase);
        }
        if (true) {
            InputStream sqlStream = null;
            BufferedReader sqlReader = null;
            StringWriter writer = null;
            try {
                sqlStream = context.getResources().openRawResource(R.raw.workouts);
                sqlReader = new BufferedReader(new InputStreamReader(sqlStream, "UTF-8"));
                writer = new StringWriter();
                char[] buffer = new char[2048];
                int n;
                while ((n = sqlReader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
                String rawSql = writer.toString();
                String[] dbSetup = rawSql.split("\n");
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
        edittext.setOnKeyListener(new NumberEnterListener(this));

        final Button highLevelView = (Button) findViewById(R.id.jumpToHighLevel);
        highLevelView.setOnClickListener(new View.OnClickListener() {public void onClick(View v) { Intent intent = new Intent(context, HighLevelView.class); startActivity(intent); }});

        setGridView(initialList);
    }

    protected void setGridView(String[] workoutList) {
        /*ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.listitem, workoutList) {
            public View getView(int position, View convertView, ViewGroup parent) {
                return convertView;
            }
        };*/
        TableAdapter adapter = new TableAdapter();

        GridView gridView = (GridView) findViewById(R.id.workoutGrid);
        gridView.setAdapter(adapter);
    }

    protected void setGridView(ArrayList<String> workoutList) {
        setGridView(toArray(workoutList));
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

    public static String[] toArray(List<String> list) {
        String [] result = new String [list.size()];
        for (int i = 0; i < list.size(); i ++) {
            result[i] = list.get(i);
        }
        return result;
    }
}