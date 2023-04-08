package com.example.smartcity02.park;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.smartcity02.R;
import com.example.smartcity02.common.ApiServe;
import com.example.smartcity02.movies.MoviesDetailActivity;
import com.example.smartcity02.movies.MoviesMainActivity;
import com.example.smartcity02.moviesBean.MListBean;
import com.example.smartcity02.parkBean.ParkHistoryBean;
import com.example.smartcity02.utils.ConnUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParkHistoryActivity extends AppCompatActivity {

    private ListView lv_record_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_history);

        lv_record_list = (ListView) findViewById(R.id.lv_record_list);

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        initParkHistory();

    }

    public void initParkHistory(){
        ApiServe api = ConnUtils.getApi();
        Call<ParkHistoryBean> recordList = api.getRecordList();
        recordList.enqueue(new Callback<ParkHistoryBean>() {
            @Override
            public void onResponse(Call<ParkHistoryBean> call, final Response<ParkHistoryBean> response) {

                lv_record_list.setAdapter(new BaseAdapter() {
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
                        View v = LayoutInflater.from(ParkHistoryActivity.this).inflate(R.layout.lo_park_record, null);
                        TextView parkName = (TextView) v.findViewById(R.id.parkName);
                        TextView entryTime_in = (TextView) v.findViewById(R.id.entryTime_in);
                        TextView outTime_in = (TextView) v.findViewById(R.id.outTime_in);
                        TextView monetary = (TextView) v.findViewById(R.id.monetary);
                        TextView plateNumber = (TextView) v.findViewById(R.id.plateNumber);

                        parkName.setText(response.body().getRows().get(position).getParkName());
                        entryTime_in.setText(response.body().getRows().get(position).getEntryTime());
                        outTime_in.setText(response.body().getRows().get(position).getOutTime());
                        monetary.setText("消费金额："+response.body().getRows().get(position).getMonetary()+"元");
                        plateNumber.setText(response.body().getRows().get(position).getPlateNumber());

                        return v;
                    }
                });
            }

            @Override
            public void onFailure(Call<ParkHistoryBean> call, Throwable throwable) {

            }
        });
    }


}