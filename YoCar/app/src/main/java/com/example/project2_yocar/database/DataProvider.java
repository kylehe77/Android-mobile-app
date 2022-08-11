package com.example.project2_yocar.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.project2_yocar.bean.CarCategoryInfo;
import com.example.project2_yocar.bean.CarInfo;

@Database(entities = {CarInfo.class, CarCategoryInfo.class},version = 1)
public abstract class DataProvider extends RoomDatabase {
     public abstract CarCategoryInfoDao carCategoryInfoDao();
     public abstract  CarInfoDao carInfoDao() ;
}
