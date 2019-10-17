package com.example.dbr;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class RoomApp extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
