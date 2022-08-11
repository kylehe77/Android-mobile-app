package com.example.project2_yocar;

import android.app.Application;

import com.example.project2_yocar.database.DataProviderHelper;

public class MyApplication extends Application {
    public void onCreate(){
        super.onCreate();
        DataProviderHelper.initDb(this);
    }
}
