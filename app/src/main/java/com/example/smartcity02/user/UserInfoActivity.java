package com.example.smartcity02.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.smartcity02.MainActivity;
import com.example.smartcity02.R;
import com.example.smartcity02.common.IUserServer;
import com.example.smartcity02.commonBean.UserInfo;
import com.example.smartcity02.userBean.UserInfoBean;
import com.example.smartcity02.userBean.UserInfoBean2;
import com.example.smartcity02.utils.ConnUtils;
import com.example.smartcity02.utils.JsonReqBody;
import com.example.smartcity02.utils.PersonInfoTool;
import com.google.gson.Gson;


import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserInfoActivity extends AppCompatActivity {

    private EditText et_user_name;
    private EditText user_male;
    private EditText et_user_phone;
    private EditText et_user_pager;
    private ImageView back;
    private TextView tv_modify;
    private TextView tv_head;
    private ImageView iv_user_head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        et_user_name = (EditText) findViewById(R.id.et_user_name);
        user_male = (EditText) findViewById(R.id.et_user_male);
        et_user_phone = (EditText) findViewById(R.id.et_user_phone);
        et_user_pager = (EditText) findViewById(R.id.et_user_pager);
        back = (ImageView) findViewById(R.id.back);
        tv_modify = (TextView) findViewById(R.id.tv_modify);
        tv_head = (TextView) findViewById(R.id.tv_head);
        iv_user_head = (ImageView) findViewById(R.id.iv_user_head);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //initInfo();

        //单击修改，修改变成保存，同时文本可编辑，按保存，保存变成修改，同时文本不可编辑
        tv_modify.setOnClickListener(new View.OnClickListener() {
            int flag = 0;

            @Override
            public void onClick(View v) {
                if (flag == 0) {                                //可编辑
                    tv_modify.setText("保存");
                    changeState(true);
                    flag = 1;
                } else {                                      //不可编辑
                    String s = et_user_name.getText().toString();
                    String s1 = user_male.getText().toString();
                    String s2 = et_user_phone.getText().toString();
                    String s3 = et_user_pager.getText().toString();
                    if (s2.length() != 11 || s3.length() != 18) {                   //电话号码长度为11，身份证号码为
                        Toast.makeText(UserInfoActivity.this, "保存失败,电话号码长度为11位，身份证号码长度为18位", Toast.LENGTH_SHORT).show();


                        tv_modify.setText("保存");
                        changeState(true);
                        flag = 1;
                    } else {
                        hintPhone();
                        hintPager();
                        UserInfoBean.UserBean userBean = new UserInfoBean.UserBean(s, s2, s1, s3);
                        Gson gson = new Gson();
                        String s4 = gson.toJson(userBean);
                        JsonReqBody jsonReqBody = new JsonReqBody(s4);
                        saveModify(jsonReqBody);
                        tv_modify.setText("修改");
                        changeState(false);
                        Toast.makeText(UserInfoActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                        flag = 0;
                    }
                }
            }
        });

        getUserInfo();
    }
    private void changeState(boolean state){
        et_user_name.setFocusable(state);
        et_user_name.setFocusableInTouchMode(state);
        et_user_name.setCursorVisible(state);

        user_male.setFocusable(state);
        user_male.setFocusableInTouchMode(state);
        user_male.setCursorVisible(state);

        et_user_phone.setFocusable(state);
        et_user_phone.setFocusableInTouchMode(state);
        et_user_phone.setCursorVisible(state);

        et_user_pager.setFocusable(state);
        et_user_pager.setFocusableInTouchMode(state);
        et_user_pager.setCursorVisible(state);
    }

    //隐藏身份证信息
    public void hintPager(){
        String s = et_user_pager.getText().toString();
        et_user_pager.setText(PersonInfoTool.hideIdCard(s));
    }
    //隐藏手机号
    public void hintPhone(){
        String s=et_user_phone.getText().toString();
        et_user_phone.setText(PersonInfoTool.hintPhoneId(s));

    }

    public void getUserInfo(){
        Retrofit retrofit = ConnUtils.create();
        IUserServer iUserServer = retrofit.create(IUserServer.class);
        SharedPreferences sharedPreferences=getSharedPreferences("Token",MODE_PRIVATE);
        String string = sharedPreferences.getString("Token", "null");
        Call<ResponseBody> userInfo = iUserServer.getUserInfo(string);
        userInfo.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();
                    Gson gson=new Gson();
                    UserInfoBean2 userInfo1 = gson.fromJson(string, UserInfoBean2.class);
                    System.out.println("aaa"+string);
                    System.out.println("aaa"+MainActivity.Token);
//                    Glide.with(UserInfoActivity.this)
//                            .load(ConnUtils.getPath()+userInfo1.getUser().getAvatar())
//                            .into(iv_user_head);
                    et_user_name.setText(userInfo1.getUser().getUserName());
                    user_male.setText(userInfo1.getUser().getSex());
                    et_user_phone.setText(PersonInfoTool.hintPhoneId(userInfo1.getUser().getPhonenumber()));
                    et_user_pager.setText(PersonInfoTool.hideIdCard(userInfo1.getUser().getIdCard()));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    public void saveModify(RequestBody req){
        Retrofit retrofit = ConnUtils.create();
        IUserServer iUserServer = retrofit.create(IUserServer.class);
        Call<ResponseBody> responseBodyCall = iUserServer.putInfoModify(MainActivity.Token,req);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }
}


