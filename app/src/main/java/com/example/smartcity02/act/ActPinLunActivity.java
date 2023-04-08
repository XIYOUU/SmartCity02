package com.example.smartcity02.act;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcity02.R;
import com.example.smartcity02.actBean.ActCommentNumBean;
import com.example.smartcity02.actBean.PinlunListBean;
import com.example.smartcity02.common.ApiServe;
import com.example.smartcity02.utils.ConnUtils;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActPinLunActivity extends AppCompatActivity {

    private TextView all_pinlun;
    private ListView all_pinlun_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_pin_lun);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);


        all_pinlun = (TextView) findViewById(R.id.all_pinlun);
        all_pinlun_list = (ListView) findViewById(R.id.all_pinlun_list);

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initPinlunList(id);
        initPinlunNum(id);
    }

    public void initPinlunNum(Integer id) {
        ApiServe api = ConnUtils.getApi();
        Call<ActCommentNumBean> commentNum = api.getCommentNum(id);
        commentNum.enqueue(new Callback<ActCommentNumBean>() {
            @Override
            public void onResponse(Call<ActCommentNumBean> call, Response<ActCommentNumBean> response) {
                all_pinlun.setText("评论数：" + response.body().getCommentNum());
            }

            @Override
            public void onFailure(Call<ActCommentNumBean> call, Throwable throwable) {

            }
        });
    }

    public void initPinlunList(Integer id) {
        ApiServe api = ConnUtils.getApi();
        Call<PinlunListBean> commentList = api.getCommentList(id);
        commentList.enqueue(new Callback<PinlunListBean>() {
            @Override
            public void onResponse(Call<PinlunListBean> call, final Response<PinlunListBean> response) {
                all_pinlun_list.setAdapter(new BaseAdapter() {
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
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View v = LayoutInflater.from(ActPinLunActivity.this).inflate(R.layout.lo_act_detail_list, null);
                        TextView name = (TextView) v.findViewById(R.id.name);
                        name.setText("" + response.body().getRows().get(position).getContent());
                        return v;
                    }
                });
            }

            @Override
            public void onFailure(Call<PinlunListBean> call, Throwable throwable) {

            }
        });

    }
}