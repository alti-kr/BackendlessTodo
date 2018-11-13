package com.gmail.sergii_tymofieiev.backendlesstodo.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.gmail.sergii_tymofieiev.backendlesstodo.App;
import com.gmail.sergii_tymofieiev.backendlesstodo.R;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * @author Sergii Tymofieiev on 13.11.2018
 */
public class Utils {

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return isValidByPattern(phoneNumber, Constants.PATTERN_PHONE_NUMBER);
    }
    private static boolean isValidByPattern(String s, String pattern) {
        Pattern put = Pattern.compile(pattern);
        Matcher m = put.matcher(s);
        if (m.matches()) {
            return true;
        }
        return false;
    }
    public static int getScreenHeight(Context context) {
        return getScreenWidthHeight(context, false);
    }

    private static int getScreenWidthHeight(Context context, boolean isWidth) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        final Point point = new Point();
        try {
            display.getSize(point);
        } catch (java.lang.NoSuchMethodError ignore) { // Older device
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        if (isWidth) {
            return point.x;
        } else {
            return point.y;
        }

    }
    public static View getViewByLayoutId(Context c, int id, ViewGroup root, boolean attachToRoot) {
        Resources r = c.getResources();
        String name = findResourceIdName(R.layout.class, id);
        if (name == null) {
            return ((LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(r.getLayout(id), root, attachToRoot);
        } else {
            int newId = r.getIdentifier(name, "layout", getAppInfo().packageName);
            return ((LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(r.getLayout(newId == 0 ? id : newId), root, attachToRoot);
        }
    }
    public static String findResourceIdName(Class<?> c, int id) {
        Field[] strings = c.getFields();
        for (int i = 0; i < strings.length; i++) {
            try {
                Class<?> t = strings[i].getType();
                if (t == int.class) {
                    if (Modifier.isStatic(strings[i].getModifiers())) {
                        if (strings[i].getInt(null) == id) {
                            return strings[i].getName();
                        }
                    }
                } else {
                    return null;
                }
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    public static PackageInfo getAppInfo() {
        try {
            return App.getContext().getPackageManager().getPackageInfo(App.getContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }
    public static String getStringById(int id) {
        return Utils.getStringById(App.getContext(), id);
    }
    public static String getStringById(Context context, int id) {
        return context != null ? context.getResources().getString(id) : App.getContext().getResources().getString(id);
    }
    public static Toast makeToast(Context context, int stringID, int duration) {
        return makeToast(context, getStringById(context, stringID), duration);
    }

    public static Toast makeToast(Context context, CharSequence text, int duration) {
        final Toast t = new Toast(context);
        View v = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.utils_toast, null);
        t.setView(v);
        t.setText(text);
        int displace = (int)App.getContext().getResources().getDimension(R.dimen.dimen_64);
        t.setGravity(Gravity.BOTTOM, 0, displace);
        t.setDuration(duration);
        return t;
    }
}
