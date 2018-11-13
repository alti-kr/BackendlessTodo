package com.gmail.sergii_tymofieiev.backendlesstodo;

import android.app.Application;
import android.content.Context;

/*
 * @author Sergii Tymofieiev on 13.11.2018
 */
public class App extends Application {
    private static App instance;

    public App() {
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }
}
