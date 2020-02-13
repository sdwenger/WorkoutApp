package com.example.workout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static String [] initialList = {"Title","Sets","Reps"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edittext = (EditText) findViewById(R.id.myNumber);
        edittext.setOnKeyListener(new NumberEnterListener(this));
        final Context context = this;

        final Button highLevelView = (Button) findViewById(R.id.jumpToHighLevel);
        highLevelView.setOnClickListener(new View.OnClickListener() {public void onClick(View v) { Intent intent = new Intent(context, HighLevelView.class); startActivity(intent); }});

        setGridView(initialList);
    }

    protected void setGridView(String[] workoutList) {
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.listitem, workoutList);

        GridView gridView = (GridView) findViewById(R.id.workoutGrid);
        gridView.setAdapter(adapter);
    }

    protected void setGridView(ArrayList<String> workoutList) {
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.listitem, workoutList);

        GridView gridView = (GridView) findViewById(R.id.workoutGrid);
        gridView.setAdapter(adapter);
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
}