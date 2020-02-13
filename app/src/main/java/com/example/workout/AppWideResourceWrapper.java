package com.example.workout;

import android.database.sqlite.SQLiteDatabase;

public class AppWideResourceWrapper {
    private static SQLiteDatabase sqlitedb = null;

    public static SQLiteDatabase getSqlitedb() {
        return sqlitedb;
    }

    public static void setSqlitedb(SQLiteDatabase sqlitedb) {
        AppWideResourceWrapper.sqlitedb = sqlitedb;
    }

    public static boolean sqlitedbIsSet() {
        return (sqlitedb != null);
    }
}
