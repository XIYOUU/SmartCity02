package com.example.smartcity02.TO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartcity02.R;
import com.example.smartcity02.TOBean.TOBannerBean;
import com.example.smartcity02.TOBean.TOLacBean;
import com.example.smartcity02.TOBean.TOThemeBean;
import com.example.smartcity02.common.ApiServe;
import com.example.smartcity02.utils.ConnUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TOMainActivity extends AppCompatActivity implements TabHost.TabContentFactory {

    private TextView loc;
    private SearchView search_bar;
    private Banner to_banner;
    private RecyclerView rec;
    private TOThemeBean toThemeBean;
    private TabHost tabhost;
    private String names[] = {"首页", "关注", "订单", "我的"};
    private int pics[]={R.drawable.ic_home_black_24dp,R.drawable.ic_dashboard_black_24dp,R.drawable.ic_notifications_black_24dp,R.drawable.user};


    private List<TextView> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_o_main);

        initTab();
    }

    public void initTab() {
        tabhost = (TabHost) findViewById(android.R.id.tabhost);
        tabhost.setup();
        views = new ArrayList<>();
        for (int i = 0; i < 4; i++) {


            View v = LayoutInflater.from(TOMainActivity.this).inflate(R.layout.layout_item, null);

            LinearLayout ll = (LinearLayout) v.findViewById(R.id.ll);

            ImageView pic = (ImageView) v.findViewById(R.id.pic);
            TextView name = (TextView) v.findViewById(R.id.name);
            pic.setImageResource(pics[i]);
            name.setText(names[i]);
            name.setTextColor(Color.parseColor("#000000"));

            views.add(name);
            tabhost.addTab(tabhost.newTabSpec("" + i).setIndicator(v).setContent(TOMainActivity.this));
        }
        views.get(0).setTextColor(Color.parseColor("#FF0000"));
        getSupportFragmentManager().beginTransaction().replace(R.id.tabContent, new TOIndexFragment()).commit();
        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for (int i = 0; i < views.size(); i++) {
                    views.get(i).setTextColor(Color.parseColor("#000000"));
                }
                switch (tabId) {
                    case "0": {
                        getSupportFragmentManager().beginTransaction().replace(R.id.tabContent, new TOIndexFragment()).commit();
//                        View childAt = tabhost.getChildAt(Integer.parseInt(tabId));
                        views.get(0).setTextColor(Color.parseColor("#FF0000"));
                        break;
                    }
                    case "1": {
                        getSupportFragmentManager().beginTransaction().replace(R.id.tabContent, new TOAttFragment()).commit();
                        views.get(1).setTextColor(Color.parseColor("#FF0000"));
                        break;
                    }
                    case "2": {
                        getSupportFragmentManager().beginTransaction().replace(R.id.tabContent, new TOOrderFragment()).commit();
                        views.get(2).setTextColor(Color.parseColor("#FF0000"));
                        break;
                    }
                    case "3": {
                        getSupportFragmentManager().beginTransaction().replace(R.id.tabContent, new TOUserFragment()).commit();
                        views.get(3).setTextColor(Color.parseColor("#FF0000"));
                        break;
                    }
                }
            }

        });

    }



    @Override
    public View createTabContent(String tag) {
        View v = new View(TOMainActivity.this);

        return v;
    }
}