package com.example.project2_yocar.utils;

import com.google.gson.Gson;

import java.util.List;
import java.lang.reflect.Type;

public class JsonUtil {

    //Parse json string and store information into a list.
    public static List<?> parseJsonToList(String json, Type type){
        Gson gson = new Gson();
        List<?> list = gson.fromJson(json, type);
        return list;
    }
}
