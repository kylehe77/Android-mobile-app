package com.example.project2_yocar.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project2_yocar.bean.CarInfo;

import java.util.List;

/**
 * user related dao class
 */
@Dao
public interface CarInfoDao {
    /**
     * insert data
     * @param carInfos
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)//If old data exists, it will be replaced, if not, it will be inserted
    void insertAll(List<CarInfo> carInfos );
    /**
     * insert data
     * @param carInfos
     */
    @Update
    void update( CarInfo... carInfos );

    /**
     *Search CarInfo list according to the carCategoryID
     * @param carCategoryId
     * @return
     */
    @Query("select * from carinfo where carCategoryId = :carCategoryId")
    List<CarInfo> findByCarCategoryId(String carCategoryId );

    /**
     *  search carinfo according to the carname
     * @param carName
     * @return
     */
    @Query("select * from carinfo where carName like '%' || :carName || '%' " )
    List<CarInfo> searchByName(String carName );


    /**
     * Query all infos
     * @return
     */
    @Query("select * from carinfo")
    List<CarInfo> loadAll();
}
