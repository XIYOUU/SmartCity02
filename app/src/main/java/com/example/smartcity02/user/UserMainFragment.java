package com.example.smartcity02.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.smartcity02.MainActivity;
import com.example.smartcity02.R;
import com.example.smartcity02.common.ApiServe;
import com.example.smartcity02.common.IUserServer;
import com.example.smartcity02.commonBean.UserInfo;
import com.example.smartcity02.ui.login.LoginActivity;
import com.example.smartcity02.utils.ConnUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class UserMainFragment extends Fragment {


    private Button user_info;
    private Button user_order;
    private Button user_pwd;
    private Button user_feedback;
    private Button user_unLogin;
    private ImageView pic;
    private TextView name;
    private String token;
    private ImageView iv_enter1;
    private ImageView iv_enter3;
    private ImageView iv_enter2;
    private TextView tv_back;
    private RelativeLayout rl_personinfo;
    private RelativeLayout rl_orderList;
    private RelativeLayout rl_changePwd;
    private RelativeLayout rl_feedback;
    private TextView tv_account;
    private ImageView iv_head_pic;
    private ImageView iv_enter4;

    @SuppressLint("CommitPrefEdits")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_main, null, false);

        iv_enter1 = (ImageView) v.findViewById(R.id.iv_enter1);
        iv_enter3 = (ImageView) v.findViewById(R.id.iv_enter3);
        iv_enter2 = (ImageView) v.findViewById(R.id.iv_enter2);
        iv_enter4 = (ImageView) v.findViewById(R.id.iv_enter4);
        rl_personinfo = (RelativeLayout) v.findViewById(R.id.rl_personinfo);
        rl_orderList = (RelativeLayout) v.findViewById(R.id.rl_orderList);
        rl_changePwd = (RelativeLayout) v.findViewById(R.id.rl_changePwd);
        rl_feedback = (RelativeLayout) v.findViewById(R.id.rl_feedback);
        name = (TextView) v.findViewById(R.id.name);




        //跳转到个人信息页面
        rl_personinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                startActivity(intent);
            }
        });
        //跳转到订单列表
        rl_orderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderListActivity.class);
                startActivity(intent);
            }
        });

        //跳转到修改密码
        rl_changePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserPwdActivity.class);
                startActivity(intent);
            }
        });

        //跳转到意见反馈
        rl_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserFeedbackActivity.class);
                startActivity(intent);
            }
        });

        //点击退出按钮退回到登录界面
//        tv_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                logOut();
//                //finish();
//                Intent intent = new Intent(getActivity(), LoginActivity.class);
//                startActivity(intent);
//
//            }
//        });

        SharedPreferences sharedPreferences1=getActivity().getSharedPreferences("Token",Context.MODE_PRIVATE);
        token = sharedPreferences1.getString("Token", null);

        if(token!=null&&  !token.isEmpty()){
            initUserInfo();
        }else{
//            Log.e("Token",MainActivity.Token);
            startActivity(new Intent(getActivity(),LoginActivity.class));
        }


        return v;
    }

    public void logOut(){
        Retrofit retrofit = ConnUtils.create();
        IUserServer iUserServer = retrofit.create(IUserServer.class);
        Call<ResponseBody> responseBodyCall = iUserServer.reqLogOut();
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                Toast.makeText(getActivity(), "注销失败"+throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public void getUserInfo(){
//        Retrofit retrofit = ConnUtils.create();
//        IUserServer iUserServer = retrofit.create(IUserServer.class);
//        Call<ResponseBody> userInfo = iUserServer.getUserInfo(HomePagerActivity.TOKEN);
//        userInfo.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    String string = response.body().string();
//                    Gson gson=new Gson();
//                    UserInfo userInfo1 = gson.fromJson(string, UserInfo.class);
//
//                    Glide.with(getActivity())
//                            .load(ConnUtils.getPath()+userInfo1.getUser().getAvatar())
//                            .into(iv_head_pic);
//                    tv_account.setText(userInfo1.getUser().getUserName());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
//
//            }
//        });
//    }

//        pic = (ImageView) v.findViewById(R.id.pic);
//        name = (TextView) v.findViewById(R.id.name);
//
//        user_info = (Button) v.findViewById(R.id.user_info);
//        user_order = (Button) v.findViewById(R.id.user_order);
//        user_pwd = (Button) v.findViewById(R.id.user_pwd);
//        user_feedback = (Button) v.findViewById(R.id.user_feedback);
//        user_unLogin = (Button) v.findViewById(R.id.user_unLogin);
//
//        user_unLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sharedPreferences2=getActivity().getSharedPreferences("Token",Context.MODE_PRIVATE);
//                SharedPreferences.Editor edit = sharedPreferences2.edit();
//                edit.putString("Token",null);
//                edit.commit();
//                startActivity(new Intent(getActivity(),LoginActivity.class));
//            }
//        });
//
//        SharedPreferences isLogin = getActivity().getSharedPreferences("isLogin", Context.MODE_PRIVATE);
//        SharedPreferences.Editor edit = isLogin.edit();
//        if(isLogin.getBoolean("isLogin",false)==false){
//            startActivity(new Intent(getActivity(), LoginActivity.class));
//        }
//



    public void initUserInfo(){
        ApiServe api = ConnUtils.getApi();
        Call<UserInfo> userInfo = api.getUserInfo(token);
//        Log.e("Token",MainActivity.Token);
        System.out.println(token);
        userInfo.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
//                Glide.with(getActivity())
//                        .load(ConnUtils.getPath()+response.body().getUser().getAvatar())
//                        .into(pic);
//                System.out.println(response.body().getUser().getUserName());
//                name.setText(response.body().getUser().getUserName());
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable throwable) {

            }
        });
    }

}