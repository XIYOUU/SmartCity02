package com.example.smartcity02.city;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartcity02.R;
import com.example.smartcity02.cityBean.AllLine;
import com.example.smartcity02.cityBean.AllPic;
import com.example.smartcity02.common.ApiServe;
import com.example.smartcity02.utils.ConnUtils;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllActivity extends AppCompatActivity {

    private ListView all_line_list;
    private ImageView all_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        all_line_list = (ListView) findViewById(R.id.all_line_list);
        all_pic = (ImageView) findViewById(R.id.all_pic);




        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initLine();
        initPic();
    }
    public void initLine(){
        ApiServe api = ConnUtils.getApi();
        Call<AllLine> metroLineList = api.getMetroLineList();
        metroLineList.enqueue(new Callback<AllLine>() {
            @Override
            public void onResponse(Call<AllLine> call, final Response<AllLine> response) {
                all_line_list.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return response.body().getData().size();
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
                        View v = LayoutInflater.from(AllActivity.this).inflate(R.layout.lo_line, null);
                        TextView lineName = (TextView) v.findViewById(R.id.lineName);
                        lineName.setText(response.body().getData().get(position).getLineName());
                        return v;
                    }
                });
            }

            @Override
            public void onFailure(Call<AllLine> call, Throwable throwable) {

            }
        });
    }

    public void initPic(){
        ApiServe api = ConnUtils.getApi();
        Call<AllPic> metroPic = api.getMetroPic();
        metroPic.enqueue(new Callback<AllPic>() {
            @Override
            public void onResponse(Call<AllPic> call, Response<AllPic> response) {
                Glide.with(AllActivity.this)
                        .load(ConnUtils.getPath()+response.body().getData().getImgUrl())
                        .into(all_pic);
            }

            @Override
            public void onFailure(Call<AllPic> call, Throwable throwable) {

            }
        });
    }
}