package com.example.smartcity02.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartcity02.R;
import com.example.smartcity02.common.ApiServe;
import com.example.smartcity02.moviesBean.MDetailBean;
import com.example.smartcity02.utils.ConnUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesDetailActivity extends AppCompatActivity {

    private int id;
    private ImageView pic;
    private TextView name;
    private TextView score;
    private TextView playDate;
    private TextView likeNum;
    private Button back_up;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_detail);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        pic = (ImageView) findViewById(R.id.pic);
        name = (TextView) findViewById(R.id.name);
        score = (TextView) findViewById(R.id.score);
        playDate = (TextView) findViewById(R.id.playDate);
        likeNum = (TextView) findViewById(R.id.likeNum);
        back_up = (Button) findViewById(R.id.back_up);

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        back_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initMDetail(id);
    }

    public void initMDetail(Integer id){
        ApiServe api = ConnUtils.getApi();
        Call<MDetailBean> filmDetail = api.getFilmDetail(id);
        filmDetail.enqueue(new Callback<MDetailBean>() {
            @Override
            public void onResponse(Call<MDetailBean> call, Response<MDetailBean> response) {
                Glide.with(MoviesDetailActivity.this)
                        .load(ConnUtils.getPath()+response.body().getData().getCover())
                        .into(pic);
                name.setText(response.body().getData().getName());
                score.setText("评分："+response.body().getData().getScore());
                playDate.setText("上映时间："+response.body().getData().getPlayDate());
                likeNum.setText("想看人数："+response.body().getData().getLikeNum());
            }

            @Override
            public void onFailure(Call<MDetailBean> call, Throwable throwable) {

            }
        });
    }
}