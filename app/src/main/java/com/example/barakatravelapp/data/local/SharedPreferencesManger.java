package com.example.barakatravelapp.data.local;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.barakatravelapp.data.model.userLoginResponce.UserData;
import com.google.gson.Gson;

public class SharedPreferencesManger {

    public static SharedPreferences sharedPreferences = null;
    public static String USER_DATA = "USER_DATA";
    public static String REMEMBER_ME = "REMEMBER_ME";
    public static String USER_PASSWORD = "USER_PASSWORD";
    public static String CLIENT = "CLIENT";
    public static String TABPOSION = "TABPOSION";
    public static String GOOGLECHECK = "GOOGLECHECK";


    public static void setSharedPreferences(Activity activity) {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences(
                    "Sofra", activity.MODE_PRIVATE);
        }
    }

    public static void SaveData(Activity activity, String data_Key, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }

    public static void SaveData(Activity activity, String data_Key, boolean data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }

    public static void SaveData(Activity activity, String data_Key, Object data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String StringData = gson.toJson(data_Value);
            editor.putString(data_Key, StringData);
            editor.commit();
        }
    }

    public static String LoadData(Activity activity, String data_Key) {
        setSharedPreferences(activity);

        return sharedPreferences.getString(data_Key, null);
    }

//
    public static UserData LoadUserData(Activity activity) {
        setSharedPreferences(activity);

        UserData loginData = null;
        Gson gson = new Gson();
        loginData = gson.fromJson(LoadData(activity, USER_DATA), UserData.class);

        return loginData;
    }
    public static UserData LoadUserData2(Context activity) {
        setSharedPreferences((Activity) activity);

        UserData loginData = null;
        Gson gson = new Gson();
        loginData = gson.fromJson(LoadData((Activity) activity, USER_DATA), UserData.class);

        return loginData;
    }
    public static boolean LoadBoolean(Activity activity, String data_Key) {
        setSharedPreferences(activity);

        return sharedPreferences.getBoolean(data_Key, false);
    }

    public static void clean(Activity activity) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        }
    }

}
