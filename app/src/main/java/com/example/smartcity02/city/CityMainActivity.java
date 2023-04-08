package com.example.smartcity02.city;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcity02.R;
import com.example.smartcity02.cityBean.CityLineBean;
import com.example.smartcity02.common.ApiServe;
import com.example.smartcity02.utils.ConnUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityMainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private ImageView all;
    private CityLineBean cityLineBeans;
    private ListView list_line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_main);


//        recycler = (RecyclerView) findViewById(R.id.recycler);
        all = (ImageView) findViewById(R.id.all);
        list_line = (ListView) findViewById(R.id.list_line);


        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CityMainActivity.this,AllActivity.class));
            }
        });

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        initCityLine();
    }

    public void initCityLine() {
        ApiServe apiServe = ConnUtils.getApi();
        Call<CityLineBean> jianguo = apiServe.getMetroList("建国门");
        jianguo.enqueue(new Callback<CityLineBean>() {


            @Override
            public void onResponse(Call<CityLineBean> call, final Response<CityLineBean> response) {
                list_line.setAdapter(new BaseAdapter() {
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
                        View v = LayoutInflater.from(CityMainActivity.this).inflate(R.layout.layout_line, null);
                        LinearLayout ll_line = (LinearLayout) v.findViewById(R.id.ll_line);
                        TextView lineName = (TextView) v.findViewById(R.id.lineName);
                        TextView nextStep = (TextView) v.findViewById(R.id.nextStep);
                        TextView reachTime = (TextView) v.findViewById(R.id.reachTime);

                        lineName.setText(""+response.body().getData().get(position).getLineName());
                        nextStep.setText(""+response.body().getData().get(position).getNextStep().getName());
                        reachTime.setText(""+response.body().getData().get(position).getReachTime());
                        return v;
                    }
                });
                list_line.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(CityMainActivity.this,MetroDetailActivity.class);
                        intent.putExtra("lineId",response.body().getData().get(position).getLineId());
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<CityLineBean> call, Throwable throwable) {

            }
        });
    }

}