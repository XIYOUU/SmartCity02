package com.example.smartcity02.city;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.example.smartcity02.R;
import com.example.smartcity02.cityBean.DetailLineBean;
import com.example.smartcity02.common.ApiServe;
import com.example.smartcity02.utils.ConnUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MetroDetailActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private TextView detail_name;
    private TextView detail_name1;
    private TextView first;
    private TextView end;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro_detail);

        detail_name1 = (TextView) findViewById(R.id.detail_name);
        first = (TextView) findViewById(R.id.first);
        end = (TextView) findViewById(R.id.end);

        Intent intent = getIntent();
        int lineId = intent.getIntExtra("lineId", 0);


        ImageView quit = (ImageView) findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        initDetail(lineId);
    }

    public void initDetail(Integer lineId) {
        ApiServe api = ConnUtils.getApi();
        Call<DetailLineBean> metroLine = api.getMetroLine(lineId);
        metroLine.enqueue(new Callback<DetailLineBean>() {
            @Override
            public void onResponse(Call<DetailLineBean> call, Response<DetailLineBean> response) {
                detail_name1.setText(response.body().getData().getName());
                first.setText(response.body().getData().getFirst());
                end.setText(response.body().getData().getEnd());
            }

            @Override
            public void onFailure(Call<DetailLineBean> call, Throwable throwable) {

            }
        });


    }
}