package com.example.project2_yocar.utils;

import android.content.Context;

import com.example.project2_yocar.bean.CarCategoryInfo;
import com.example.project2_yocar.bean.CarInfo;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AssetsUtil {
    public static List<CarCategoryInfo> getCarCategoryList(Context context) {
        InputStream is = null;
        ////Get the in-memory output stream object
        ByteArrayOutputStream bos = null;
        try{
            is = context.getAssets().open("carCategoryInfo.json");
            bos = new ByteArrayOutputStream();
            //declare a 4048 bytes number
            byte[] bytes= new byte[4 * 1024];
            int len = 0;
            //when len = -1, it means reaching the end of file.
            while((len = is.read(bytes))!=-1){
                bos.write(bytes,0,len);
            }
            final String json = new String(bos.toByteArray());
            //Store all information from json file into the list
            List<CarCategoryInfo> carCategoryInfos = (List<CarCategoryInfo>) JsonUtil.parseJsonToList(json,
                    new TypeToken<List<CarCategoryInfo>>() {
                    }.getType());
            return carCategoryInfos;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
                if (bos != null)
                    bos.close();
            } catch (IOException e) {

            }
        }
        return null;
    }


    public static List<CarInfo> getCarInfoList(Context context) {
        InputStream is = null;
        ////Get the in-memory output stream object
        ByteArrayOutputStream bos = null;
        try{
            is = context.getAssets().open("carinfo.json");
            bos = new ByteArrayOutputStream();
            //declare a 4048 bytes number
            byte[] bytes= new byte[4 * 1024];
            int len = 0;
            //when len = -1, it means reaching the end of file.
            while((len = is.read(bytes))!=-1){
                bos.write(bytes,0,len);
            }
            final String json = new String(bos.toByteArray());
            //Store all information from json file into the list
            List<CarInfo> myCarInfoList = (List<CarInfo>) JsonUtil.parseJsonToList(json,
                    new TypeToken<List<CarInfo>>() {
                    }.getType());
            return myCarInfoList;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
                if (bos != null)
                    bos.close();
            } catch (IOException e) {

            }
        }
        return null;
    }
}
