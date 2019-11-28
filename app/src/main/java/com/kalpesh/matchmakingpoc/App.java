package com.kalpesh.matchmakingpoc;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import io.realm.Realm;

public class App extends Application {

    /// application context variable
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        // Set the application context
        context = getApplicationContext();
        // Initialise Realm
        Realm.init(this);
    }

    /// Get application context
    public static Context sharedContext() {
        return context;
    }

}
