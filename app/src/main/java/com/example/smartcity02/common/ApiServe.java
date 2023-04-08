package com.example.smartcity02.common;

import com.example.smartcity02.TOBean.TOSellerListBean2;
import com.example.smartcity02.TOBean.TOThemeBean;
import com.example.smartcity02.TOBean.TOSellerListBean;
import com.example.smartcity02.TOBean.TOBannerBean;
import com.example.smartcity02.TOBean.TOLacBean;
import com.example.smartcity02.actBean.ActBannerBean;
import com.example.smartcity02.actBean.ActCommendBean;
import com.example.smartcity02.actBean.ActCommentNumBean;
import com.example.smartcity02.actBean.ActDetailBean;
import com.example.smartcity02.actBean.ActIfSuccessBean;
import com.example.smartcity02.actBean.ActSortBean;
import com.example.smartcity02.actBean.ActSortListBean;
import com.example.smartcity02.actBean.ActSuccess;
import com.example.smartcity02.actBean.PinlunListBean;
import com.example.smartcity02.busBean.BusListBean;
import com.example.smartcity02.cityBean.AllLine;
import com.example.smartcity02.cityBean.AllPic;
import com.example.smartcity02.cityBean.CityLineBean;
import com.example.smartcity02.cityBean.DetailLineBean;
import com.example.smartcity02.commonBean.BannerBean;
import com.example.smartcity02.commonBean.LoginBean;
import com.example.smartcity02.commonBean.NewBean;
import com.example.smartcity02.commonBean.NewsBean;
import com.example.smartcity02.commonBean.ResponseBean;
import com.example.smartcity02.commonBean.ServeBean;
import com.example.smartcity02.commonBean.User;
import com.example.smartcity02.commonBean.UserInfo;
import com.example.smartcity02.commonBean.UserPwd;
import com.example.smartcity02.dataAnaBean.DataAnaBean;
import com.example.smartcity02.moviesBean.MBannerBean;
import com.example.smartcity02.moviesBean.MDetailBean;
import com.example.smartcity02.moviesBean.MListBean;
import com.example.smartcity02.parkBean.ParkDetailBean;
import com.example.smartcity02.parkBean.ParkHistoryBean;
import com.example.smartcity02.parkBean.ParkInfoBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServe {
    @GET("prod-api/api/rotation/list")
    Call<BannerBean> getRotationList(@Query("type") String type);

    @GET("prod-api/api/service/list")
    Call<ServeBean> getServeList();

    @GET("prod-api/press/category/list")
    Call<NewBean> getCategoryList();

    @GET("prod-api/press/press/list")
    Call<NewsBean> getPressList(@Query("type") String type);

    @GET("prod-api/api/common/user/getInfo")
    Call<UserInfo> getUserInfo(@Header("Authorization") String authorization);

    @POST("prod-api/api/login")
    Call<LoginBean> getLogin(@Body User user);


    @PUT("prod-api/api/common/user/resetPwd")
    Call<ResponseBean> getResetPwd(@Header("Authorization") String authorization, @Body UserPwd userPwd);


    //1.9.1.获取系统全部服务
    @GET("prod-api/api/service/list")
    Call<ResponseBody> getServiceList();

    //1.9.1.获取系统全部服务
    @GET("prod-api/api/service/list")
    Call<ResponseBody> getServiceList(@Query("serviceType") String serviceType);


    //3.7.4.首页地铁站点查询
    @GET("prod-api/api/metro/list")
    Call<CityLineBean> getMetroList(@Query("currentName") String currentName);

    @GET("prod-api/api/metro/line/{id}")
    Call<DetailLineBean> getMetroLine(@Path("id") Integer id);

    @GET("prod-api/api/metro/line/list")
    Call<AllLine> getMetroLineList();

    @GET("prod-api/api/metro/city")
    Call<AllPic> getMetroPic();

    @GET("prod-api/api/service/list")
    Call<ServeBean> getServeList(@Query("serviceName") String name);


    @GET("prod-api/api/activity/rotation/list")
    Call<ActBannerBean> getActRotationList();

    @GET("prod-api/api/activity/category/list")
    Call<ActSortBean> getActCategoryList();

    @GET("prod-api/api/activity/activity/list")
    Call<ActSortListBean> getActList(@Query("categoryId") Integer categoryId);

    @GET("prod-api/api/activity/activity/list")
    Call<ActSortListBean> getActList(@Query("recommend") String recommend, @Query("pageSize") int pageSize, @Query("pageNum") int pageNum);

    @GET("prod-api/api/activity/activity/{id}")
    Call<ActDetailBean> getActDetail(@Path("id") Integer id);

    @GET("prod-api/api/activity/comment/list")
    Call<PinlunListBean> getCommentList(@Query("activityId") Integer activityId);

    @GET("prod-api/api/activity/comment/number")
    Call<ActCommentNumBean> getCommentNum(@Query("activityId") Integer activityId);

    @POST("prod-api/api/activity/signup")
    Call<ActSuccess> getActSignUp(@Body Integer activityId);

    @GET("prod-api/api/activity/signup/check")
    Call<ActIfSuccessBean> getSignUp(@Header("Authorization") String Token, @Query("activityId") Integer activityId);

    @POST("prod-api/api/activity/comment")
    Call<ActSuccess> getActComment(@Header("Authorization") String Token, @Body ActCommendBean actCommendBean);

    @GET("prod-api/press/press/list")
    Call<DataAnaBean> getPressList(@Query("pageSize") int pageSize, @Query("pageNum") int pageNum);

    @GET("prod-api/api/bus/line/list")
    Call<BusListBean> getBusLineList();

    @GET("prod-api/api/movie/rotation/list")
    Call<MBannerBean> getMRotationList(@Query("type") String type);

    @GET("prod-api/api/movie/film/list")
    Call<MListBean> getMFirmList(@Query("name") String name, @Query("pageSize") int pageSize, @Query("pageNum") int pageNum);

    @GET("prod-api/api/movie/film/detail/{id}")
    Call<MDetailBean> getFilmDetail(@Path("id") Integer id);

    @GET("prod-api/api/common/gps/location")
    Call<TOLacBean> getGPSLoc();

    @GET("prod-api/api/takeout/rotation/list")
    Call<TOBannerBean> getTOBanner();

    @GET("prod-api/api/takeout/seller/{id}")
    Call<TOSellerListBean> getSeller(@Path("id") Integer id);

    @GET("prod-api/api/takeout/theme/list")
    Call<TOThemeBean> getThemeList();

    @GET("prod-api/api/takeout/seller/list")
    Call<TOSellerListBean2> getSellerList(@Query("themeId") Integer themeId);

    @GET("prod-api/api/takeout/seller/list")
    Call<TOSellerListBean2> getSellerList(@Query("recommend") String recommend);


    /*停哪儿*/
    @GET("prod-api/api/park/lot/record/list")
    Call<ParkHistoryBean> getRecordList();

    @GET("prod-api/api/park/lot/list")
    Call<ParkInfoBean> getLotList(@Query("name") String name, @Query("pageSize") int pageSize, @Query("pageNum") int pageNum);

    @GET("prod-api/api/park/lot/{id}")
    Call<ParkDetailBean> getParkDetail(@Path("id") Integer id);

}
