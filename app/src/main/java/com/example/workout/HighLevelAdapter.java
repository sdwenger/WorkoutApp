package com.example.workout;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HighLevelAdapter extends BaseAdapter {

    private HighLevelItem[] cells;
    private AppCompatActivity context;
    private Calendar cal = Calendar.getInstance();

    public HighLevelAdapter(HighLevelAdapter original) {
        this.context = original.context;
        cells = new HighLevelItem[original.cells.length];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new HighLevelItem(original.cells[i]);
        }
    }

    public HighLevelAdapter(List<Integer> dayNumbers, List<String> titles, AppCompatActivity context) {
        int length = Math.min(dayNumbers.size(), titles.size());
        cells = new HighLevelItem[length];
        for (int i = 0; i < length; i ++) {
            cells[i] = new HighLevelItem(dayNumbers.get(i), titles.get(i));
        }
        setDates();
        this.context = context;
    }

    @Override
    public int getCount() {
        return 28;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        HighLevelItem item = (HighLevelItem) getItem(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listitemwithdate, parent, false);
            TextView text = convertView.findViewById(R.id.label);
            text.setText(item.title);
            TextView hidden = convertView.findViewById(R.id.hiddenInfo);
            hidden.setText(Integer.toString(item.dayNumber));
            TextView date = convertView.findViewById(R.id.dateDisplay);
            cal.setTime(item.workoutDate);
            String dateString = item.dayNumber + context.getString(R.string.dotspace) + (cal.get(Calendar.MONTH)+1) + '/' + cal.get(Calendar.DAY_OF_MONTH) + '/' + cal.get(Calendar.YEAR);
            date.setText(dateString);
            convertView.setBackgroundColor(item.isToday?0xFFFF0000:0xFFFFFFFF);
        }
        return convertView;
    }

    private void setDates() {
        for (int i = 0; i < cells.length; i ++) {
            cells[i].setDate();
        }
    }
}

class HighLevelItem {
    int dayNumber;
    String title;
    Date workoutDate = new Date();
    boolean isToday;

    public HighLevelItem(int dayNumber, String title) {
        this.dayNumber = dayNumber;
        this.title = title;
    }

    public HighLevelItem(HighLevelItem original) {
        this.dayNumber = original.dayNumber;
        this.title = original.title;
        setDate();
    }

    public void setDate() {
        Date today = new Date();
        long now = today.getTime()-AppWideResourceWrapper.timeZoneDelta();
        now -= now%(86400L*1000L);
        today.setTime(now);
        Date targetDate = (Date) AppWideResourceWrapper.retrieveStartDate().clone();
        targetDate.setTime(targetDate.getTime() + 86400L*1000L*(dayNumber-1));
        if (targetDate.before(today)) {
            long delta = today.getTime() - targetDate.getTime();
            long cycles = (long)Math.ceil(delta/(86400L*1000L*28.0));
            targetDate.setTime(targetDate.getTime() + 86400L*1000L*28L*cycles);
        }
        workoutDate.setTime(targetDate.getTime());
        isToday = (targetDate.getTime() == now);
    }
}
