package com.example.workout;

import android.app.Application;

public class WorkoutApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (!AppWideResourceWrapper.globalContextIsSet()) {
            AppWideResourceWrapper.setGlobalContext(this);
        }
    }
}
