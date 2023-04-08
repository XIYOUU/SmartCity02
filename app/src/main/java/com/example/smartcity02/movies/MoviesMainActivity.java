package com.example.smartcity02.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartcity02.R;
import com.example.smartcity02.common.ApiServe;
import com.example.smartcity02.moviesBean.MBannerBean;
import com.example.smartcity02.moviesBean.MListBean;
import com.example.smartcity02.utils.ConnUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesMainActivity extends AppCompatActivity {

    private Banner banner;
    private ListView m_list;
    private Button more;
    private SearchView search_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_main);

        banner = (Banner) findViewById(R.id.banner);
        m_list = (ListView) findViewById(R.id.m_list);
        more = (Button) findViewById(R.id.more);
        search_bar = (SearchView) findViewById(R.id.search_bar);
        //搜索内容
        search_bar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                initMList(query, 5, 1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        //显示全部
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initMList("", 100, 1);
            }
        });
        initBanner();
        //默认显示全部（5条数据）
        initMList("", 5, 1);

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void initBanner() {
        ApiServe api = ConnUtils.getApi();
        Call<MBannerBean> mRotationList = api.getMRotationList("2");
        mRotationList.enqueue(new Callback<MBannerBean>() {
            @Override
            public void onResponse(Call<MBannerBean> call, Response<MBannerBean> response) {
                List<String> imgs = new ArrayList<>();
                for (int i = 0; i < response.body().getRows().size(); i++) {
                    imgs.add(ConnUtils.getPath() + response.body().getRows().get(i).getAdvImg());
                }
                banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object o, ImageView imageView) {
                        Glide.with(MoviesMainActivity.this)
                                .load((String) o)
                                .into(imageView);
                    }
                });
                banner.setImages(imgs);
                banner.start();
            }

            @Override
            public void onFailure(Call<MBannerBean> call, Throwable throwable) {

            }
        });
    }

    public void initMList(String name, int pageSize, int pageNum) {
        ApiServe api = ConnUtils.getApi();
        Call<MListBean> mFirmList = api.getMFirmList(name, pageSize, pageNum);
        mFirmList.enqueue(new Callback<MListBean>() {
            @Override
            public void onResponse(Call<MListBean> call, final Response<MListBean> response) {
                m_list.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return response.body().getRows().size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return null;
                    }

                    @Override
                    public long getItemId(int position) {
                        return 0;
                    }

                    @Override
                    public View getView(final int position, View convertView, ViewGroup parent) {
                        View v = LayoutInflater.from(MoviesMainActivity.this).inflate(R.layout.lo_m_list, null);
                        TextView name = (TextView) v.findViewById(R.id.name);
                        TextView playDate = (TextView) v.findViewById(R.id.playDate);
                        TextView duration = (TextView) v.findViewById(R.id.duration);
                        RelativeLayout m_rl = (RelativeLayout) findViewById(R.id.m_rl);
                        name.setText(response.body().getRows().get(position).getName());
                        playDate.setText("上映时间:" + response.body().getRows().get(position).getPlayDate());
                        duration.setText("时长:" + response.body().getRows().get(position).getDuration() + "分钟");

//                        m_rl.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Intent intent=new Intent(MoviesMainActivity.this,MoviesDetailActivity.class);
//                                intent.putExtra("id",response.body().getRows().get(position).getId());
//                                startActivity(intent);
//                            }
//                        });
                        return v;
                    }
                });
                m_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MoviesMainActivity.this, MoviesDetailActivity.class);
                        intent.putExtra("id", response.body().getRows().get(position).getId());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<MListBean> call, Throwable throwable) {

            }
        });

    }
}