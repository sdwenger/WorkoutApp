package com.example.workout;

import android.app.Application;
import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class WorkoutApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (!AppWideResourceWrapper.globalContextIsSet()) {
            AppWideResourceWrapper.setGlobalContext(this);
        }
        SQLiteDatabase mydatabase = openOrCreateDatabase(getString(R.string.table_workout), MODE_PRIVATE, null);
        AppWideResourceWrapper.setSqlitedb(mydatabase);

        DatabaseUpdater updater = DatabaseUpdater.getInstance(mydatabase);
        updater.start();
    }
}

class DatabaseUpdater extends Thread {

    SQLiteDatabase mydatabase = null;
    public static boolean isRun = false;

    private DatabaseUpdater() {

    }

    public static DatabaseUpdater getInstance(SQLiteDatabase mydatabase) {
        DatabaseUpdater result = new DatabaseUpdater();
        result.mydatabase = mydatabase;
        return result;
    }

    public void run() {
        boolean upToDate = false;
        try {
            Cursor c = mydatabase.rawQuery(AppWideResourceWrapper.staticGetString(R.string.versionQuery), new String[]{});
            if (c.moveToFirst()) {
                String version = c.getString(c.getColumnIndex(AppWideResourceWrapper.staticGetString(R.string.header_val)));
                upToDate = AppWideResourceWrapper.staticGetString(R.string.version_number).equals(version);
            }
        } catch (SQLiteException e) {
        }
        if (!upToDate) {
            InputStream sqlStream = null;
            BufferedReader sqlReader = null;
            StringWriter writer = null;
            try {
                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Strength (Day INT, Sequence INT, Title VARCHAR(40), Sets INT, RepString VARCHAR(20), Weight INT);");
                Cursor c = mydatabase.rawQuery("SELECT Day, Sequence, Weight FROM Strength", new String[]{});
                Map<Integer, Integer> oldData = new HashMap<Integer, Integer>();
                if (c.moveToFirst()) {
                    do {
                        Integer key = c.getInt(c.getColumnIndex("Day")) * 1000 + c.getInt(c.getColumnIndex("Sequence"));
                        Integer value = c.getInt(c.getColumnIndex("Weight"));
                        oldData.put(key, value);
                    } while (c.moveToNext());
                }
                sqlStream = Resources.getSystem().openRawResource(R.raw.workouts);
                sqlReader = new BufferedReader(new InputStreamReader(sqlStream, AppWideResourceWrapper.staticGetString(R.string.charSetUTF8)));
                writer = new StringWriter();
                char[] buffer = new char[2048];
                int n;
                while ((n = sqlReader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
                String rawSql = writer.toString();
                String[] dbSetup = rawSql.split(AppWideResourceWrapper.staticGetString(R.string.newline));
                for (String s : dbSetup) {
                    mydatabase.execSQL(s);
                }
                sqlStream.close();
                sqlReader.close();
                writer.close();
                for (Integer key : oldData.keySet()) {
                    Log.w("key", key + ":" + oldData.get(key));
                    Integer day = key / 1000;
                    Integer sequence = key % 1000;
                    ContentValues content = new ContentValues();
                    content.put("Weight", oldData.get(key));
                    mydatabase.update("Strength", content, "Day=? AND Sequence=?", new String[]{day.toString(), sequence.toString()});
                }
                c.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                isRun = true;
            }
        }
    }
}