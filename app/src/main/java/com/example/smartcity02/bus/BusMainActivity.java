package com.example.smartcity02.bus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcity02.R;
import com.example.smartcity02.busBean.BusListBean;
import com.example.smartcity02.common.ApiServe;
import com.example.smartcity02.utils.ConnUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusMainActivity extends AppCompatActivity {

    private ListView bus_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_main);

        bus_list = (ListView) findViewById(R.id.bus_list);
        initBusList();
    }

    public void initBusList(){
        ApiServe api = ConnUtils.getApi();
        Call<BusListBean> busLineList = api.getBusLineList();
        busLineList.enqueue(new Callback<BusListBean>() {
            @Override
            public void onResponse(Call<BusListBean> call, final Response<BusListBean> response) {
                bus_list.setAdapter(new BaseAdapter() {
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
                        View v = LayoutInflater.from(BusMainActivity.this).inflate(R.layout.lo_bus_list, null);
                        TextView title = (TextView) v.findViewById(R.id.title);
                        TextView start = (TextView) v.findViewById(R.id.start);
                        TextView time = (TextView) v.findViewById(R.id.time);
                        TextView price = (TextView) v.findViewById(R.id.price);
                        TextView mileage = (TextView) v.findViewById(R.id.mileage);

                        title.setText(response.body().getRows().get(position).getName());
                        start.setText(response.body().getRows().get(position).getFirst()+"->"+response.body().getRows().get(position).getEnd());
                        time.setText(response.body().getRows().get(position).getStartTime()+"->"+response.body().getRows().get(position).getEndTime());
                        price.setText("票价："+response.body().getRows().get(position).getPrice());
                        mileage.setText("里程："+response.body().getRows().get(position).getMileage());

                        return v;
                    }
                });
//                bus_list.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
            }

            @Override
            public void onFailure(Call<BusListBean> call, Throwable throwable) {

            }
        });
    }
}