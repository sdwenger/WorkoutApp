package com.example.workout;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NumberEnterListener implements View.OnKeyListener {

    enum NumberContext{
        DAYNUMBER, WEIGHTEDIT
    }

    AppCompatActivity mainActivity;

    final NumberContext nc;

    public NumberEnterListener(AppCompatActivity mainActivity, NumberContext nc) {
        this.mainActivity = mainActivity;
        this.nc = nc;
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        switch(nc) {
            case DAYNUMBER:
                return onKeyDayView(v, keyCode, event);
            case WEIGHTEDIT:
                return onKeyEditWeight(v, keyCode, event);
        }
        return false;
    }

    public boolean onKeyEditWeight(View v, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            View parentView = (ViewGroup) v.getParent();
            EditText editor = parentView.findViewById(R.id.enteredNumber);
            TextView rowIdAccess = parentView.findViewById(R.id.rowId);
            int weight = Integer.parseInt(editor.getText().toString());
            int rowId = Integer.parseInt(rowIdAccess.getText().toString());
            ((MainActivity)mainActivity).saveListener.enqueueUpdate(rowId, weight);
            ((MainActivity) mainActivity).hideKeyboard(mainActivity);
            return true;
        }
        return false;
    }

    public boolean onKeyDayView(View v, int keyCode, KeyEvent event) {
        EditText self = (EditText) mainActivity.findViewById(R.id.myNumber);
        if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            ArrayList<String> tables = new ArrayList<String>();
            ArrayList<Integer> rowIds = new ArrayList<Integer>();
            SQLiteDatabase mydatabase = AppWideResourceWrapper.getSqlitedb();
            String dayString = self.getText().toString();
            Integer rawNumber = Integer.parseInt(dayString);
            Integer dayNumber = reduce(rawNumber);
            if (rawNumber != dayNumber) {
                self.setText(dayNumber.toString());
            }
            Cursor c = mydatabase.rawQuery(mainActivity.getString(R.string.strenghQueryByDay), new String[]{dayNumber.toString()});
            if (c.moveToFirst()) {
                for (String s : MainActivity.initialList) {
                    tables.add(s);
                }
                while (!c.isAfterLast()) {
                    tables.add(c.getString(c.getColumnIndex(mainActivity.getString(R.string.header_title))));
                    tables.add(c.getString(c.getColumnIndex(mainActivity.getString(R.string.header_sets))));
                    tables.add(c.getString(c.getColumnIndex(mainActivity.getString(R.string.header_repstring))));
                    String weight = c.getString(c.getColumnIndex(mainActivity.getString(R.string.header_weight)));
                    if (weight == null) {
                        weight = mainActivity.getString(R.string.zero);
                    }
                    tables.add(weight);
                    rowIds.add(c.getInt(c.getColumnIndex(mainActivity.getString(R.string.header_rowid))));
                    c.moveToNext();
                }
            } else {
                tables.add(mainActivity.getString(R.string.rest));
            }
            MainActivity asMain = ((MainActivity)mainActivity);
            if (asMain.saveListener != null) {
                asMain.saveListener.clearQueue();
            }
            asMain.setGridView(tables, rowIds);
            asMain.hideKeyboard(mainActivity);
            return true;
        }
        else {
            return false;
        }
    }

    public static Integer reduce(Integer number) {
        Integer result = number%28;
        return result==0?28:result;
    }
}
