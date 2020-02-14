package com.example.workout;

import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

    public static void assertTrue(boolean flag) {
        assertTrue(flag, "Assertion failed");
    }

    public static void assertTrue(boolean flag, Object message) {
        assertTrue(flag, message.toString());
    }

    public static void assertTrue(boolean flag, String message) {
        if (!flag) {
            throw new AssertionError(message);
        }
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