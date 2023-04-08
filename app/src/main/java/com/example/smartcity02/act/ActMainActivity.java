package com.example.smartcity02.act;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartcity02.R;
import com.example.smartcity02.actBean.ActBannerBean;
import com.example.smartcity02.actBean.ActSortBean;
import com.example.smartcity02.actBean.ActSortListBean;
import com.example.smartcity02.common.ApiServe;
import com.example.smartcity02.commonBean.BannerBean;
import com.example.smartcity02.news.NewActivity;
import com.example.smartcity02.utils.ConnUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActMainActivity extends AppCompatActivity implements TabHost.TabContentFactory {

    private Banner banner;
    private TabHost tabhost;
    private ListView list_act_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_main);


        banner = (Banner) findViewById(R.id.banner);
        tabhost = (TabHost) findViewById(android.R.id.tabhost);
        tabhost.setup();
        list_act_list = (ListView) findViewById(R.id.list_act_list);

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initBanner();
        initActSort();
    }

    public void initBanner() {
        ApiServe api = ConnUtils.getApi();
        Call<ActBannerBean> actRotationList = api.getActRotationList();
        actRotationList.enqueue(new Callback<ActBannerBean>() {
            @Override
            public void onResponse(Call<ActBannerBean> call, Response<ActBannerBean> response) {
                List<String> imgs = new ArrayList<>();
                for (int i = 0; i < response.body().getRows().size(); i++) {
                    imgs.add(ConnUtils.getPath() + response.body().getRows().get(i).getAdvImg());
                }
                banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object o, ImageView imageView) {
                        Glide.with(context)
                                .load((String) o)
                                .into(imageView);

                    }
                });
                banner.setImages(imgs);
                banner.start();
            }

            @Override
            public void onFailure(Call<ActBannerBean> call, Throwable throwable) {

            }
        });
    }

    public void initActSort() {
        ApiServe api = ConnUtils.getApi();
        Call<ActSortBean> actCategoryList = api.getActCategoryList();
        actCategoryList.enqueue(new Callback<ActSortBean>() {
            @Override
            public void onResponse(Call<ActSortBean> call, final Response<ActSortBean> response) {
                initSortList(response.body().getData().get(0).getId());
                for (int i = 0; i < response.body().getData().size(); i++) {
                    tabhost.addTab(tabhost.newTabSpec(i + "")
                            .setIndicator(response.body().getData().get(i).getName())
                            .setContent(ActMainActivity.this));

                }
                tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                    @Override
                    public void onTabChanged(String tabId) {
                        initSortList(response.body().getData().get(Integer.parseInt(tabId)).getId());
                    }
                });
            }

            @Override
            public void onFailure(Call<ActSortBean> call, Throwable throwable) {

            }
        });
    }

    @Override
    public View createTabContent(String tag) {
        View v = new View(ActMainActivity.this);
        return v;
    }

    public void initSortList(Integer categoryId) {
        ApiServe api = ConnUtils.getApi();
        Call<ActSortListBean> actList = api.getActList(categoryId);
        actList.enqueue(new Callback<ActSortListBean>() {
            @Override
            public void onResponse(Call<ActSortListBean> call, final Response<ActSortListBean> response) {
                list_act_list.setAdapter(new BaseAdapter() {
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
                        View v = LayoutInflater.from(ActMainActivity.this).inflate(R.layout.lo_act_list, null);
                        ImageView imgUrl = (ImageView) v.findViewById(R.id.imgUrl);
                        TextView name = (TextView) v.findViewById(R.id.name);
                        TextView signupNum = (TextView) v.findViewById(R.id.signupNum);
                        TextView likeNum = (TextView) v.findViewById(R.id.likeNum);
                        RelativeLayout rl = (RelativeLayout) v.findViewById(R.id.rl);


                        Glide.with(ActMainActivity.this)
                                .load(ConnUtils.getPath() + response.body().getRows().get(position).getImgUrl())
                                .into(imgUrl);
                        name.setText(response.body().getRows().get(position).getName());
                        signupNum.setText("报名人数：" + response.body().getRows().get(position).getSignupNum());
                        likeNum.setText("点赞数：" + response.body().getRows().get(position).getLikeNum());
                        rl.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(ActMainActivity.this,ActDetailActivity.class);
                                intent.putExtra("id",response.body().getRows().get(position).getId());
                                intent.putExtra("CategoryId",response.body().getRows().get(position).getCategoryId());
                                startActivity(intent);
                            }
                        });
                        return v;
                    }
                });
            }

            @Override
            public void onFailure(Call<ActSortListBean> call, Throwable throwable) {

            }
        });
    }
}