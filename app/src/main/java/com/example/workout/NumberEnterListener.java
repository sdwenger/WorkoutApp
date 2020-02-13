package com.example.workout;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NumberEnterListener implements View.OnKeyListener {

    AppCompatActivity mainActivity;
    //*
    String tableCreate = "CREATE TABLE IF NOT EXISTS Strength (Day INT, Title VARCHAR(40), Sets INT, RepString VARCHAR(20))\n";
    String []dataInsert = ("INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (1,'Dumbbell Bench Press',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (1,'Lat Pulldown',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (1,'Overhead Dumbbell Press',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (1,'Leg Press',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (1,'Lying Leg Curl',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (1,'Rope Pressdown',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (1,'Barbell Biceps Curl',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (1,'Standing Calf Raise',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (1,'Crunch',3,'15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (3,'Dumbbell Bench Press',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (3,'Lat Pulldown',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (3,'Overhead Dumbbell Press',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (3,'Leg Press',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (3,'Lying Leg Curl',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (3,'Rope Pressdown',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (3,'Barbell Biceps Curl',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (3,'Standing Calf Raise',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (3,'Crunch',3,'15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (5,'Dumbbell Bench Press',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (5,'Lat Pulldown',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (5,'Overhead Dumbbell Press',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (5,'Leg Press',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (5,'Lying Leg Curl',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (5,'Rope Pressdown',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (5,'Barbell Biceps Curl',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (5,'Standing Calf Raise',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (5,'Crunch',3,'15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (8,'Barbell Bench Press',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (8,'Dumbbell Flye',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (8,'Barbell Bent-Over Row',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (8,'Lat Pulldown',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (8,'Overhead Dumbbell Press',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (8,'Dumbbell Lateral Raise',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (8,'Barbell Biceps Curl',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (8,'Preacher Curl with Cable',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (8,'Lying EZ-Bar Triceps Extension',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (8,'Rope Pressdown',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (8,'Crunch',3,'15-20')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (9,'Leg Press',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (9,'Leg Extension',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (9,'Lying Leg Curl',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (9,'Seated Leg Curl',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (9,'Standing Calf Raise',3,'15-20')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (9,'Seated Calf Raise',3,'15-20')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (11,'Barbell Bench Press',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (11,'Dumbbell Flye',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (11,'Barbell Bent-Over Row',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (11,'Lat Pulldown',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (11,'Overhead Dumbbell Press',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (11,'Dumbbell Lateral Raise',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (11,'Barbell Biceps Curl',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (11,'Preacher Curl with Cable',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (11,'Lying EZ-Bar Triceps Extension',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (11,'Rope Pressdown',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (11,'Crunch',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (12,'Leg Press',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (12,'Leg Extension',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (12,'Lying Leg Curl',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (12,'Seated Leg Curl',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (12,'Standing Calf Raise',3,'15-20')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (12,'Seated Calf Raise',3,'15-20')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (15,'Incline Barbell Bench Press',4,'*Perform reps as: 10,10,12,15.')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (15,'Dumbbell Flye',4,'*Perform reps as: 10,10,12,15.')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (15,'Overhead Dumbbell Press',4,'*Perform reps as: 10,10,12,15.')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (15,'Smith Machine Upright Row',4,'8, 8, 10, 12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (15,'Lying EZ-Bar Triceps Extension',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (15,'Dumbbell Kickback',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (16,'Barbell Upright Row',4,'8,8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (16,'Single-Arm Neutral-Grip Dumbbell Row',4,'8,8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (16,'Incline Dumbbell Biceps Curl',4,'8,8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (16,'Preacher Curl with Cable',4,'8,8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (16,'Reverse Crunch',3,'15-20')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (16,'Crunch',3,'15-20')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (17,'Back Squat',4,'8,8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (17,'Leg Press',4,'8,8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (17,'Seated Leg Curl',4,'*Perform reps as: 8, 8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (17,'Romanian Deadlift',4,'8,8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (17,'Standing Calf Raise',3,'25')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (17,'Seated Calf Raise',3,'25')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (18,'Incline Barbell Bench Press',4,'*Perform reps as: 10,10,12,15.')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (18,'Dumbbell Flye',4,'*Perform reps as: 10,10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (18,'Overhead Dumbbell Press',4,'*Perform reps as: 10,10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (18,'Smith Machine Upright Row',4,'*Perform reps as: 8, 8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (18,'Lying EZ-Bar Triceps Extension',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (18,'Dumbbell Kickback',3,'10,12,15')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (19,'Barbell Upright Row',4,'8,8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (19,'Single-Arm Neutral-Grip Dumbbell Row',4,'8,8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (19,'Incline Dumbbell Biceps Curl',4,'8,8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (19,'Preacher Curl with Cable',4,'8,8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (19,'Reverse Crunch',3,'15-20')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (19,'Crunch',3,'15-20')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (20,'Back Squat',4,'8,8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (20,'Leg Press',4,'8,8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (20,'Seated Leg Curl',4,'8,8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (20,'Romanian Deadlift',4,'8,8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (20,'Standing Calf Raise',3,'25')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (20,'Seated Calf Raise',3,'25')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (22,'Incline Barbell Bench Press',5,'10')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (22,'Dumbbell Bench Press',5,'*Perform reps as: 8,8,10,10,12.')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (22,'Dumbbell Flye',5,'*Perform reps as: 8,8,10,10,12.')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (22,'Rope Pressdown',4,'*Perform reps as: 10,10,12,12.')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (22,'Dumbbell Kickback',3,'10')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (22,'Lying EZ-Bar Triceps Extension',3,'10')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (22,'Standing Calf Raise',3,'25')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (22,'Seated Calf Raise',3,'225')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (23,'Back Squat',5,'10')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (23,'Leg Press',5,'*Perform reps as: 8,8,10,10,12.')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (23,'Leg Extension',5,'*Perform reps as: 8, 8,10,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (23,'Lying Leg Curl',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (23,'Romanian Deadlift',3,'8,10,10')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (23,'Seated Leg Curl',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (23,'Reverse Crunch',2,'20')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (23,'Crunch',2,'20')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (25,'Overhead Dumbbell Press',4,'12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (25,'Smith Machine Upright Row',3,'8,10,12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (25,'Dumbbell Lateral Raise',3,'10')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (25,'Seated Calf Raise',10,'10')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (26,'Barbell Bent-Over Row',5,'12')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (26,'Lat Pulldown',5,'*Perform reps as: 8,8,10,12,12.')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (26,'Single-Arm Neutral-Grip Dumbbell Row',5,'*Perform reps as: 8,8,8,10,10.')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (26,'Barbell Biceps Curl',4,'*Perform reps as: 10,10,12,12.')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (26,'Incline Dumbbell Biceps Curl',3,'10')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (26,'Preacher Curl with Cable',3,'10')\n" +
            "INSERT INTO Strength(Day, Title, Sets, RepString) VALUES (26,'Crunch',3,'20')\n").split("\n");
    //*/

    public NumberEnterListener(AppCompatActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        EditText self = (EditText) mainActivity.findViewById(R.id.myNumber);
        String [] empty = new String[]{};
        if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            /*
            SQLiteDatabase mydatabase = mainActivity.openOrCreateDatabase("Workouts",mainActivity.MODE_PRIVATE,null);
            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Strength (Day INT, Title VARCHAR(40), Sets INT, RepString VARCHAR(20))");
            Cursor c = mydatabase.rawQuery("SELECT Title, Sets, RepString FROM Strength", new String[]{});
            if (!c.moveToFirst()) {
                String[] dataLoad = dataInsert.split("\n");
                if (dataLoad.length > 5 && dataLoad.length < 1000) {
                    for (String s: dataLoad) {mydatabase.execSQL(s);}
                    ((MainActivity)mainActivity).setGridView(new String[]{"Success"});
                } else {
                    ((MainActivity)mainActivity).setGridView(new String[]{(new Integer(dataLoad.length)).toString()});
                }
            } else {
                ((MainActivity)mainActivity).setGridView(new String[]{"???"});
            }
            /*/
            ArrayList<String> tables = new ArrayList<String>();
            SQLiteDatabase mydatabase = mainActivity.openOrCreateDatabase("Workouts", mainActivity.MODE_PRIVATE, null);
            mydatabase.execSQL(tableCreate);
            Cursor c = mydatabase.rawQuery("SELECT Title, Sets, RepString FROM Strength", new String[]{});
            if (!c.moveToFirst()) {
                if (dataInsert.length > 5 && dataInsert.length < 1000) {
                    for (String s: dataInsert) {mydatabase.execSQL(s);}
                    ((MainActivity)mainActivity).setGridView(new String[]{"Success"});
                } else {
                    ((MainActivity)mainActivity).setGridView(new String[]{(new Integer(dataInsert.length)).toString()});
                }
            }
            String dayString = self.getText().toString();
            Integer rawNumber = Integer.parseInt(dayString);
            Integer dayNumber = reduce(rawNumber);
            if (rawNumber != dayNumber) {
                self.setText(dayNumber.toString());
            }
            c = mydatabase.rawQuery("SELECT Title, Sets, RepString FROM Strength WHERE Day=?", new String[]{dayNumber.toString()});
            if (c.moveToFirst()) {
                for (String s : MainActivity.initialList) {
                    tables.add(s);
                }
                while (!c.isAfterLast()) {
                    tables.add(c.getString(c.getColumnIndex("Title")));
                    tables.add(c.getString(c.getColumnIndex("Sets")));
                    tables.add(c.getString(c.getColumnIndex("RepString")));
                    c.moveToNext();
                }
            } else {
                tables.add("Rest day");
            }
            ((MainActivity) mainActivity).setGridView(tables);
            ((MainActivity) mainActivity).hideKeyboard(mainActivity);

            // */
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
