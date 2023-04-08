package com.example.smartcity02.park;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcity02.R;
import com.example.smartcity02.common.ApiServe;
import com.example.smartcity02.parkBean.ParkDetailBean;
import com.example.smartcity02.utils.ConnUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParkDetailActivity extends AppCompatActivity {

    private TextView parkName;
    private TextView distance;
    private TextView address;
    private TextView open;
    private TextView rates;
    private TextView vacancy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_detail);

        Intent intent = getIntent();
        Integer id = intent.getIntExtra("id", 0);

        parkName = (TextView) findViewById(R.id.parkName);
        distance = (TextView) findViewById(R.id.distance);
        address = (TextView) findViewById(R.id.address);
        open = (TextView) findViewById(R.id.open);
        rates = (TextView) findViewById(R.id.rates);
        vacancy = (TextView) findViewById(R.id.vacancy);


        initParkDetail(id);

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initParkDetail(Integer id){
        ApiServe api = ConnUtils.getApi();
        Call<ParkDetailBean> parkDetail = api.getParkDetail(id);
        parkDetail.enqueue(new Callback<ParkDetailBean>() {
            @Override
            public void onResponse(Call<ParkDetailBean> call, Response<ParkDetailBean> response) {
                parkName.setText(response.body().getData().getParkName());
                distance.setText(response.body().getData().getDistance()+"米");
                address.setText("地址："+response.body().getData().getAddress());
                open.setText("是否开放："+response.body().getData().getOpen());
                rates.setText("每小时："+ response.body().getData().getRates()+"元"+"，最高"+response.body().getData().getPriceCaps()+"元/天");
                vacancy.setText("剩余车位："+response.body().getData().getVacancy());
            }

            @Override
            public void onFailure(Call<ParkDetailBean> call, Throwable throwable) {

            }
        });
    }
}