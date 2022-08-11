package com.example.project2_yocar.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.project2_yocar.bean.CarCategoryInfo;

import java.util.List;

@Dao
public interface CarCategoryInfoDao {
    //Insert data
    @Insert (onConflict = OnConflictStrategy.REPLACE)//If old data exists, it will be replaced, if not, it will be inserted.
    void insertAll(List<CarCategoryInfo> categoryInfos);


    //Query all data
    @Query("select* from carcategoryinfo")
    List<CarCategoryInfo> loadAll();
}

