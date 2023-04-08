package com.example.smartcity02.common;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface IUserServer {



    //登录
    @POST("prod-api/api/login")
    Call<ResponseBody> getLogin(@Body RequestBody string);

    //注销
    @POST("logout")
    Call<ResponseBody> reqLogOut();

    //1.1.2用户注册
    @POST("/prod-api/api/register")
    Call<ResponseBody> reqRegister(@Body RequestBody body);

    //1.2.1查询用户信息
    @GET("prod-api/api/common/user/getInfo")
    Call<ResponseBody> getUserInfo(@Header("Authorization") String Authorization);


    //1.2.2.修改个人基本信息
    @PUT("prod-api/api/common/user")
    Call<ResponseBody> putInfoModify(@Header("Authorization") String Authorization, @Body RequestBody req);

    //1.2.3.修改用户密码
    @PUT("prod-api/api/common/user/resetPwd")
    Call<ResponseBody> putInfoPWd(@Header("Authorization") String Authorization, @Body RequestBody req);
}
