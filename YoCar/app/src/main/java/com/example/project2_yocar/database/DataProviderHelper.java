package com.example.project2_yocar.database;

import android.app.Application;

import androidx.room.Room;

public class DataProviderHelper {
    private final static String databaseName  = "mydatabase";
    private static  DataProvider dataBase  = null;

    /**
     *  get DataProvider
     * @return
     */
    public static DataProvider getDB()  {
        if (null == dataBase) {
            throw new RuntimeException("please first init database");
        }
        return dataBase ;
    }
    /**
     * initialise DataProvider
     * @param application
     */
    public static void initDb(Application application) {
        dataBase = Room.databaseBuilder(
                        application,
                        DataProvider.class,
                        databaseName)
                        .allowMainThreadQueries()
                        .build();
    }

}
