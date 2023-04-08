package com.example.smartcity02.TO;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcity02.R;
import com.example.smartcity02.TOBean.TOSellerListBean2;
import com.example.smartcity02.common.ApiServe;
import com.example.smartcity02.utils.ConnUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToSellerActivity extends AppCompatActivity {

    private ListView seller_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_seller);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);


        initSellerList(id);
    }
    public void initSellerList(Integer id){
        ApiServe api = ConnUtils.getApi();
        Call<TOSellerListBean2> sellerList = api.getSellerList(id);
        sellerList.enqueue(new Callback<TOSellerListBean2>() {
            @Override
            public void onResponse(Call<TOSellerListBean2> call, final Response<TOSellerListBean2> response) {
                seller_list.setAdapter(new BaseAdapter() {
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
                        View v = LayoutInflater.from(ToSellerActivity.this).inflate(R.layout.lo_seller_list, null);
                        TextView name = (TextView) v.findViewById(R.id.name);
                        name.setText(response.body().getRows().get(position).getName());
                        return v;
                    }
                });
            }

            @Override
            public void onFailure(Call<TOSellerListBean2> call, Throwable throwable) {

            }
        });
    }
}