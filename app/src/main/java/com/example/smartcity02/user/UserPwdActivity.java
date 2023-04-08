package com.example.smartcity02.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.smartcity02.MainActivity;
import com.example.smartcity02.R;
import com.example.smartcity02.common.ApiServe;
import com.example.smartcity02.commonBean.ResponseBean;
import com.example.smartcity02.commonBean.UserPwd;
import com.example.smartcity02.utils.ConnUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPwdActivity extends AppCompatActivity {

    private EditText priPwd_m;
    private EditText newPwd_m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pwd);

        priPwd_m = (EditText) findViewById(R.id.priPwd_m);
        newPwd_m = (EditText) findViewById(R.id.newPwd_m);
        Button modify = (Button) findViewById(R.id.modify);






        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPwd(new UserPwd(newPwd_m.getText().toString(),priPwd_m.getText().toString()));
            }
        });


    }
    public void initPwd(UserPwd userPwd){
        ApiServe api = ConnUtils.getApi();
        Call<ResponseBean> resetPwd = api.getResetPwd(MainActivity.Token,userPwd);
        resetPwd.enqueue(new Callback<ResponseBean>() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response) {
                if(response.body().getCode()/100==2){
                    Toast.makeText(UserPwdActivity.this, "修改密码成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UserPwdActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBean> call, Throwable throwable) {

            }
        });
    }
}