package com.example.workout;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.TimeZone;

public class AppWideResourceWrapper {
    private static SQLiteDatabase sqlitedb = null;
    private static Context globalContext = null;
    private static Date startDate = null;
    private static SharedPreferences appPreferences = null;
    private static String versionString = null;

    public static SQLiteDatabase getSqlitedb() {
        return sqlitedb;
    }

    public static void setSqlitedb(SQLiteDatabase sqlitedb) {
        AppWideResourceWrapper.sqlitedb = sqlitedb;
    }

    public static boolean sqlitedbIsSet() {
        return (sqlitedb != null);
    }

    public static Context getGlobalContext() {
        return globalContext;
    }

    public static void setGlobalContext(Context globalContext) {
        AppWideResourceWrapper.globalContext = globalContext;
    }

    public static boolean globalContextIsSet() {
        return globalContext!=null;
    }

    private static void setSharedPreferences() {
        if (globalContext == null) {
            throw new IllegalArgumentException(globalContext.getString(R.string.requireGlobalContextMessage));
        }
        appPreferences = globalContext.getSharedPreferences(globalContext.getString(R.string.preferencesFile), globalContext.MODE_PRIVATE);
    }

    public static String getVersionString() {
        if (versionString == null) {
            if (appPreferences == null) {
                setSharedPreferences();
            }
            versionString = appPreferences.getString(globalContext.getString(R.string.version_key), null);
        }
        return versionString;
    }

    public static void writebackVersionString() {
        String versionString = AppWideResourceWrapper.staticGetString(R.string.version_number);
        SharedPreferences.Editor editor = appPreferences.edit();
        editor.putString(AppWideResourceWrapper.staticGetString(R.string.version_key), versionString);
        editor.apply();
    }

    public static void addSharedPreferencesListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        appPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public static Date retrieveStartDate() {
        if (startDate == null) {
            if (appPreferences == null) {
                setSharedPreferences();
            }
            long startDateLong = appPreferences.getLong(globalContext.getString(R.string.startDateKey), (new Date().getTime()))-timeZoneDelta();
            startDateLong -= startDateLong%(86400L*1000L);
            startDate = new Date(startDateLong);
        }
        return startDate;
    }

    public static void setStartDate(Date date, boolean commit) {
        if (date != null) {
            startDate = date;
        }
        if (commit) {
            long milliseconds = startDate.getTime();
            SharedPreferences.Editor editor = appPreferences.edit();
            editor.putLong(globalContext.getString(R.string.startDateKey), milliseconds);
            editor.apply();
        }
    }

    public static long timeZoneDelta() {
        TimeZone tz = TimeZone.getDefault();
        return tz.getRawOffset();
    }

    public static String staticGetString(int locator) {
        return globalContext.getString(locator);
    }
}

class CustomList<T> implements List<T> {

    enum Setting {
        LIST, ARRAY, RULE
    }

    private List<T> list = null;
    private T[] array = null;
    private Rule<T> rule = null;
    private Setting wrapped = null;

    public CustomList(List<T> list) {
        this.list = list;
        wrapped = Setting.LIST;
    }

    public CustomList(T[] array) {
        this.array = array;
        wrapped = Setting.ARRAY;
    }

    public CustomList(Rule<T> rule) {
        this.rule = rule;
        wrapped = Setting.RULE;
    }

    @Override
    public int size() {
        switch (wrapped) {
            case ARRAY:
                return array.length;
            case LIST:
                return list.size();
            case RULE:
                return rule.getSize();
        }
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public boolean contains(@Nullable Object o) {
        return false;
    }

    @NonNull
    @Override
    public Iterator iterator() {
        return null;
    }

    @NonNull
    @Override
    public T[] toArray() {
        T[] result = (T[]) new Object[size()];
        for (int i = 0; i < size(); i ++) {
            result[i] = get(i);
        }
        return result;
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(@Nullable Object o) {
        return false;
    }

    @Override
    public boolean addAll(@NonNull Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, @NonNull Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        switch (wrapped) {
            case ARRAY:
                return array[index];
            case LIST:
                return list.get(index);
            case RULE:
                return rule.getIndex(index);
        }
        return null;
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(@Nullable Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(@Nullable Object o) {
        return 0;
    }

    @NonNull
    @Override
    public ListIterator listIterator() {
        return null;
    }

    @NonNull
    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @NonNull
    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(@NonNull Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(@NonNull Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(@NonNull Collection c) {
        return false;
    }

    @NonNull
    @Override
    public Object[] toArray(@NonNull Object[] a) {
        return new Object[0];
    }
}

abstract class Rule<T> {
    public abstract T getIndex(int position);
    public abstract int getSize();
}