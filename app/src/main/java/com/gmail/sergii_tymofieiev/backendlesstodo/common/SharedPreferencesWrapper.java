package com.gmail.sergii_tymofieiev.backendlesstodo.common;

import android.content.Context;
import android.content.SharedPreferences;

/*
 * @author Sergii Tymofieiev on 13.11.2018
 */

public class SharedPreferencesWrapper {
    private final static int MODE = Context.MODE_PRIVATE;
    private static SharedPreferences getSharedPreferences(Context context){

        return context.getSharedPreferences(context.getApplicationContext().getPackageName(),MODE);
    }
    public static void putString(Context context, String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static String getString(Context context, String key, String defaultValue) {
        return getSharedPreferences(context).getString(key,defaultValue);
    }

    public static long getLong(Context context, String key, long defaultValue){
        return getSharedPreferences(context).getLong(key, defaultValue);
    }

    public static void putLong(Context context, String key, long value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putLong(key, value);
        editor.commit();
    }
    public static int getInt(Context context, String key, int defaultValue){
        return getSharedPreferences(context).getInt(key, defaultValue);
    }

    public static void putInt(Context context, String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue){
        return getSharedPreferences(context).getBoolean(key, defaultValue);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
}
