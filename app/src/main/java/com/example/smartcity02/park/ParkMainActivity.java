package com.example.smartcity02.park;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.smartcity02.R;
import com.example.smartcity02.common.ApiServe;
import com.example.smartcity02.movies.MoviesDetailActivity;
import com.example.smartcity02.movies.MoviesMainActivity;
import com.example.smartcity02.moviesBean.MListBean;
import com.example.smartcity02.parkBean.ParkInfoBean;
import com.example.smartcity02.utils.ConnUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParkMainActivity extends AppCompatActivity {

    private ImageView list_history;
    private Button more;
    private ListView m_list;
    private Button history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_main);

        more = (Button) findViewById(R.id.more);
        m_list = (ListView) findViewById(R.id.m_list);
        list_history = (ImageView) findViewById(R.id.list_history);

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        list_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParkMainActivity.this,ParkHistoryActivity.class));
            }
        });


        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initMList("", 100, 1);
            }
        });
        initMList("", 5, 1);
    }

    public void initMList(String name, int pageSize, int pageNum) {
        ApiServe api = ConnUtils.getApi();
        Call<ParkInfoBean> mFirmList = api.getLotList(name, pageSize, pageNum);
        mFirmList.enqueue(new Callback<ParkInfoBean>() {
            @Override
            public void onResponse(Call<ParkInfoBean> call, final Response<ParkInfoBean> response) {

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
                        View v = LayoutInflater.from(ParkMainActivity.this).inflate(R.layout.lo_park_info, null);

                        TextView parkName = (TextView) v.findViewById(R.id.parkName);
                        TextView allPark = (TextView) v.findViewById(R.id.allPark);
                        TextView address = (TextView) v.findViewById(R.id.address);
                        TextView distance = (TextView) v.findViewById(R.id.distance);
                        RelativeLayout m_rl = (RelativeLayout) findViewById(R.id.m_rl);

                        parkName.setText(response.body().getRows().get(position).getParkName());
                        allPark.setText("空位:" + response.body().getRows().get(position).getAllPark()+"个");
                        address.setText("地址:" + response.body().getRows().get(position).getAddress());
                        distance.setText(response.body().getRows().get(position).getDistance()+"米");

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
                        Intent intent = new Intent(ParkMainActivity.this, ParkDetailActivity.class);
                        intent.putExtra("id", response.body().getRows().get(position).getId());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<ParkInfoBean> call, Throwable throwable) {

            }
        });

    }
}