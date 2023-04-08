package com.example.smartcity02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.smartcity02.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String Token;
    private ViewPager view_pager;

    private boolean isFirstUse;
    private int[] mLayout = {R.layout.layout_one, R.layout.layout_two, R.layout.layout_three, R.layout.layout_four, R.layout.layout_five,};
    private LinearLayout ll_dot;
    private List<View> mViews;
    private List<ImageView> mDot;
    private EditText et2;
    private EditText et1;
    private View views;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login();

        initViewPager();
    }

    private void Login() {
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        isFirstUse = sharedPreferences.getBoolean("isFirstUse", true);
        Log.e("isFirstUse", String.valueOf(isFirstUse));
        if (isFirstUse == true) {
            edit.putBoolean("isFirstUse", false);
            edit.commit();
        } else {
            startActivity(new Intent(MainActivity.this, NavMainActivity.class));
            finish();
        }
    }

    public void initViewPager() {
        ll_dot = (LinearLayout) findViewById(R.id.ll_dot);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        mViews = new ArrayList<>();
        mDot = new ArrayList<>();
        for (int i = 0; i < mLayout.length; i++) {
            //小圆点
            mViews.add(getLayoutInflater().inflate(mLayout[i], null));
            ImageView dot = new ImageView(MainActivity.this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(40, 40);
            params.leftMargin = 20;
            dot.setImageResource(R.drawable.spot2);
            dot.setLayoutParams(params);
            ll_dot.addView(dot);
            mDot.add(dot);

            //网络设置
            views = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_input, null);
            ll = (LinearLayout) views.findViewById(R.id.ll);
            et1 = (EditText) views.findViewById(R.id.et1);
            et2 = (EditText) views.findViewById(R.id.et2);
        }
        view_pager.setAdapter(new PagerAdapter() {

            private SharedPreferences sharedPreferences;
            private SharedPreferences.Editor edit;

            @Override
            public int getCount() {
                return mViews.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                sharedPreferences = getSharedPreferences("IP", MODE_PRIVATE);
                edit = sharedPreferences.edit();
                et1.setText(sharedPreferences.getString("address", "192.168.1.10"));
                et2.setText(sharedPreferences.getString("ip", "8080"));
                View v = mViews.get(position);
                container.addView(v);
                if (position == 4) {
                    Button enter = (Button) v.findViewById(R.id.enter);
                    Button modify = (Button) v.findViewById(R.id.modify);
                    enter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(MainActivity.this, NavMainActivity.class));
                        }
                    });
                    modify.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Dialog dialog = new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("网络配置")
                                    .setView(views)
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            ((ViewGroup) views.getParent()).removeView(views);
                                            edit.putString("address", et1.getText().toString());
                                            edit.putString("ip", et2.getText().toString());
                                            edit.commit();
                                        }
                                    })
                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            ((ViewGroup) views.getParent()).removeView(views);

                                        }
                                    })
                                    .create();
                            dialog.show();
                        }
                    });

                }
                return v;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(mViews.get(position));
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }
        });
        mDot.get(0).setImageResource(R.drawable.spot1);
//        view_pager.setCurrentItem(0);
        view_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < mDot.size(); i++) {
                    mDot.get(i).setImageResource(position == i ? R.drawable.spot1 : R.drawable.spot2);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}


