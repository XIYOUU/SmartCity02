package com.example.smartcity02.utils;

import com.example.smartcity02.common.ApiServe;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnUtils {

    /*新*/
    public static String getPath(){
        return "http://124.93.196.45:10001/";
    }

    public static ApiServe getApi(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://124.93.196.45:10001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServe apiServe = retrofit.create(ApiServe.class);
        return apiServe;
    }



    /*旧*/
    //创建Retrofit
    public static Retrofit create(){
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://124.93.196.45:10001/").
                build();
        return retrofit;
    }
    //自定义路径
    public static Retrofit create(String str){
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(str).
                build();
//        TimerTask
        return retrofit;
    }

//    //直接获取服务
//    public static AllServer createAll(){
//        Retrofit retrofit=new Retrofit.Builder()
//                .baseUrl("http://124.93.196.45:10001/")
//                .build();
//
//        AllServer allServer = retrofit.create(AllServer.class);
//
//        return allServer;
//    }
}
