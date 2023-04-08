package com.example.smartcity02.TO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartcity02.R;
import com.example.smartcity02.TOBean.TOSellerListBean;
import com.example.smartcity02.TOBean.TOThemeBean;
import com.example.smartcity02.common.ApiServe;
import com.example.smartcity02.utils.ConnUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TOBannerDetailActivity extends AppCompatActivity {

    private ImageView seller_pic;
    private int getTargetId;
    private RecyclerView rec;
    private TOThemeBean toThemeBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_o_banner_detail);
        Intent intent = getIntent();
        getTargetId = intent.getIntExtra("getTargetId", 0);

        seller_pic = (ImageView) findViewById(R.id.seller_pic);
//        rec = (RecyclerView) findViewById(R.id.rec);



        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initSeller(getTargetId);
//        initSellerList();
    }
    public void initSeller(Integer getTargetId){
        ApiServe api = ConnUtils.getApi();
        Call<TOSellerListBean> seller = api.getSeller(getTargetId);
        Log.e("id",String.valueOf(getTargetId));
        seller.enqueue(new Callback<TOSellerListBean>() {
            @Override
            public void onResponse(Call<TOSellerListBean> call, Response<TOSellerListBean> response) {

                Glide.with(TOBannerDetailActivity.this)
                        .load(ConnUtils.getPath()+response.body().getData().getImgUrl())
                        .into(seller_pic);
            }

            @Override
            public void onFailure(Call<TOSellerListBean> call, Throwable throwable) {

            }
        });
    }


}