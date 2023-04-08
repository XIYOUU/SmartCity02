package com.example.smartcity02.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.DirectedAcyclicGraph;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.smartcity02.MainActivity;
import com.example.smartcity02.R;
import com.example.smartcity02.actBean.ActCID;
import com.example.smartcity02.actBean.ActCommendBean;
import com.example.smartcity02.actBean.ActDetailBean;
import com.example.smartcity02.actBean.ActIfSuccessBean;
import com.example.smartcity02.actBean.ActSortListBean;
import com.example.smartcity02.actBean.ActSuccess;
import com.example.smartcity02.common.ApiServe;
import com.example.smartcity02.utils.ConnUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActDetailActivity extends AppCompatActivity {

    private ImageView pic;
    private TextView content;
    private Button see_pinlun;
    private Button pinlun;
    private Button en_act;
    private Intent intent;
    private Integer id;
    private ListView recommend_list;
    private int CategoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_detail);
        intent = getIntent();
        id = intent.getIntExtra("id", 0);
        CategoryId = intent.getIntExtra("CategoryId", 0);


        recommend_list = (ListView) findViewById(R.id.recommend_list);
        pic = (ImageView) findViewById(R.id.pic);
        content = (TextView) findViewById(R.id.content);
        see_pinlun = (Button) findViewById(R.id.see_pinlun);
        pinlun = (Button) findViewById(R.id.pinlun);
        en_act = (Button) findViewById(R.id.en_Act);

        en_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ActDetailActivity.this, "加入成功！", Toast.LENGTH_SHORT).show();
                initEnterAct(id);
                initIfSuccess(id);
            }
        });


        pinlun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(ActDetailActivity.this).inflate(R.layout.lo_commend, null);
                final EditText et = (EditText) view.findViewById(R.id.et);

                Dialog dialog = new AlertDialog.Builder(ActDetailActivity.this)
                        .setTitle("请输入评论")
                        .setView(view)
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.e("CategoryId", String.valueOf(CategoryId));
                                initCommend(new ActCommendBean(id, et.getText().toString()));
                            }
                        })
                        .create();
                dialog.show();
            }
        });


        see_pinlun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActDetailActivity.this, ActPinLunActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });


        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initDetail(id);
        initRecommend();
    }

    public void initDetail(Integer id) {
        ApiServe api = ConnUtils.getApi();
        Call<ActDetailBean> actDetail = api.getActDetail(id);
        actDetail.enqueue(new Callback<ActDetailBean>() {
            @Override
            public void onResponse(Call<ActDetailBean> call, Response<ActDetailBean> response) {
                Glide.with(ActDetailActivity.this)
                        .load(ConnUtils.getPath() + response.body().getData().getImgUrl())
                        .into(pic);
                content.setText(Html.fromHtml(response.body().getData().getContent()));
            }

            @Override
            public void onFailure(Call<ActDetailBean> call, Throwable throwable) {

            }
        });
    }

    public void initRecommend() {
        ApiServe api = ConnUtils.getApi();
        Call<ActSortListBean> actList = api.getActList("Y", 3, 1);
        actList.enqueue(new Callback<ActSortListBean>() {
            @Override
            public void onResponse(Call<ActSortListBean> call, final Response<ActSortListBean> response) {
                recommend_list.setAdapter(new BaseAdapter() {
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
                        View v = LayoutInflater.from(ActDetailActivity.this).inflate(R.layout.lo_act_list, null);
                        ImageView imgUrl = (ImageView) v.findViewById(R.id.imgUrl);
                        TextView name = (TextView) v.findViewById(R.id.name);
                        TextView signupNum = (TextView) v.findViewById(R.id.signupNum);
                        TextView likeNum = (TextView) v.findViewById(R.id.likeNum);
                        RelativeLayout rl = (RelativeLayout) v.findViewById(R.id.rl);


                        Glide.with(ActDetailActivity.this)
                                .load(ConnUtils.getPath() + response.body().getRows().get(position).getImgUrl())
                                .into(imgUrl);
                        name.setText(response.body().getRows().get(position).getName());
                        signupNum.setText("报名人数：" + response.body().getRows().get(position).getSignupNum());
                        likeNum.setText("点赞数：" + response.body().getRows().get(position).getLikeNum());
                        rl.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(ActDetailActivity.this, ActDetailActivity.class);
                                intent.putExtra("id", response.body().getRows().get(position).getId());
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

    public void initEnterAct(Integer id) {
        ApiServe api = ConnUtils.getApi();
        Call<ActSuccess> actSignUp = api.getActSignUp(id);
        actSignUp.enqueue(new Callback<ActSuccess>() {
            @Override
            public void onResponse(Call<ActSuccess> call, Response<ActSuccess> response) {

            }

            @Override
            public void onFailure(Call<ActSuccess> call, Throwable throwable) {

            }
        });
    }

    public void initIfSuccess(Integer id) {
        ApiServe api = ConnUtils.getApi();
        Call<ActIfSuccessBean> signUp = api.getSignUp(MainActivity.Token, id);
        signUp.enqueue(new Callback<ActIfSuccessBean>() {
            @Override
            public void onResponse(Call<ActIfSuccessBean> call, Response<ActIfSuccessBean> response) {
                Log.e("code", String.valueOf(response.body().getCode()));
                if (response.body().getCode() / 100 == 2) {
                    Toast.makeText(ActDetailActivity.this, "报名成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActDetailActivity.this, "报名失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ActIfSuccessBean> call, Throwable throwable) {

            }
        });
    }

    public void initCommend(ActCommendBean actCommendBean) {
        ApiServe api = ConnUtils.getApi();
        Call<ActSuccess> actComment = api.getActComment(MainActivity.Token, actCommendBean);
        actComment.enqueue(new Callback<ActSuccess>() {
            @Override
            public void onResponse(Call<ActSuccess> call, Response<ActSuccess> response) {
                if (response.body().getCode() / 100 == 2) {
                    Toast.makeText(ActDetailActivity.this, "评论成功！", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ActDetailActivity.this, "评论失败！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ActSuccess> call, Throwable throwable) {

            }
        });
    }
}