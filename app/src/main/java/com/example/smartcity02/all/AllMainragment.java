package com.example.smartcity02.all;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartcity02.R;
import com.example.smartcity02.TO.TOMainActivity;
import com.example.smartcity02.act.ActMainActivity;
import com.example.smartcity02.bus.BusMainActivity;
import com.example.smartcity02.city.CityMainActivity;
import com.example.smartcity02.common.ApiServe;
import com.example.smartcity02.commonBean.ServeBean;
import com.example.smartcity02.dataAna.AnaMainActivity;
import com.example.smartcity02.indexBean.IndexServiceListBean;
import com.example.smartcity02.movies.MoviesMainActivity;
import com.example.smartcity02.park.ParkMainActivity;
import com.example.smartcity02.utils.ConnUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllMainragment extends Fragment {


    private View v;
    private RecyclerView rv_serve_type;
    private RecyclerView rv_serve_type_all;
    private RecyclerView rv_serve_type_all1;
    private ArrayList<String> listType;
    private List<IndexServiceListBean.RowsBean> list;
    private IndexServiceListBean indexServiceListBean;
    private IndexServiceListBean indexServiceListBean1;
    private SearchView toolbar2;
    private EditText et_serve;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_all_mainragment, container, false);

        Button search_serve = (Button) v.findViewById(R.id.search_serve);
        et_serve = (EditText) v.findViewById(R.id.et_serve);
        rv_serve_type = (RecyclerView) v.findViewById(R.id.rv_serve_type);
        rv_serve_type_all1 = (RecyclerView) v.findViewById(R.id.rv_serve_type_all);

        //搜索图标
        search_serve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String s = et_serve.getText().toString();
                initSearch(s);
            }
        });
        initServiceList();
        return v;
    }

    public void initSearch(String name) {

        ApiServe api = ConnUtils.getApi();
        Call<ServeBean> serveList = api.getServeList(name);
        serveList.enqueue(new Callback<ServeBean>() {

            private ImageView pic;
            private View view;

            @Override
            public void onResponse(Call<ServeBean> call, final Response<ServeBean> response) {
                view = LayoutInflater.from(getActivity()).inflate(R.layout.lo_serve_search, null);
                pic = (ImageView) view.findViewById(R.id.pic);
                if (response.body().getRows().size() == 1) {            //如果搜索的到一个结果
                    Glide.with(getActivity())
                            .load(ConnUtils.getPath() + response.body().getRows().get(0).getImgUrl())
                            .into(pic);
                    pic.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (response.body().getRows().get(0).getServiceName()) {
                                case "城市地铁": {
                                    startActivity(new Intent(getActivity(), CityMainActivity.class));
                                }

                            }

                        }
                    });
                }
                Dialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("搜索结果")
                        .setView(view)
                        .create();
                dialog.show();
            }

            @Override
            public void onFailure(Call<ServeBean> call, Throwable throwable) {

            }
        });
    }

    public void initServiceList() {
        ApiServe apiServe = ConnUtils.getApi();
        Call<ResponseBody> serviceList = apiServe.getServiceList();
        serviceList.enqueue(new Callback<ResponseBody>() {


            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();
                    Gson gson = new Gson();
                    indexServiceListBean = gson.fromJson(string, IndexServiceListBean.class);

                    list = indexServiceListBean.getRows();
                    listType = new ArrayList<String>();

//                    listType.add(indexServiceListBean.getRows().get(0).getServiceType());
                    //去掉重复元素
                    for (int i = 0; i < indexServiceListBean.getRows().size(); i++) {
                        //如果重复数组在不重复数组里面找到就break;
                        if (!listType.contains(list.get(i).getServiceType())) {
//                            Log.e("dddd",String.valueOf(list.get(i).getServiceType()));
                            listType.add(list.get(i).getServiceType());
                        }
                    }
                    rv_serve_type = (RecyclerView) v.findViewById(R.id.rv_serve_type);
                    rv_serve_type.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv_serve_type.setAdapter(new serveAdapter());
                } catch (IOException e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    public class serveAdapter extends RecyclerView.Adapter<serveAdapter.serveHolder> {

        public class serveHolder extends RecyclerView.ViewHolder {
            //            private final ImageView iv_serve_pic;
            private final TextView tv_serve_name;
            private final RelativeLayout rl_server_item;

            public serveHolder(@NonNull View itemView) {
                super(itemView);
//                iv_serve_pic = (ImageView) itemView.findViewById(R.id.iv_serve_pic);
                tv_serve_name = (TextView) itemView.findViewById(R.id.tv_serve_name);
                rl_server_item = (RelativeLayout) itemView.findViewById(R.id.rl_server_item);
            }
        }


        @NonNull
        @Override
        public serveHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.layout_serve_line_item, null);
            return new serveHolder(v);
        }


        @Override
        public void onBindViewHolder(@NonNull serveHolder holder, final int position) {
//            Glide.with(holder.itemView)
//                    .load(ConnUtil.getPath() + indexServiceListBean.getRows().get(position).getImgUrl())
//                    .into(holder.iv_serve_pic);
            holder.tv_serve_name.setText(listType.get(position));

            initServiceGrid(listType.get(0));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getActivity(), "position:"+position, Toast.LENGTH_SHORT).show();
                    Log.e("ddd", "position:" + position);

                    initServiceGrid(listType.get(position));
                }
            });

        }

        @Override
        public int getItemCount() {
            return listType.size();
        }


    }

    public void initServiceGrid(String type) {
        ApiServe apiServe = ConnUtils.getApi();
        Call<ResponseBody> serviceType = apiServe.getServiceList(type);
        serviceType.enqueue(new Callback<ResponseBody>() {


            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();
                    Gson gson = new Gson();
                    indexServiceListBean1 = gson.fromJson(string, IndexServiceListBean.class);

                    RecyclerView rv_serve_type_all = (RecyclerView) v.findViewById(R.id.rv_serve_type_all);
                    rv_serve_type_all.setLayoutManager(new GridLayoutManager(getActivity(), 4));
                    rv_serve_type_all.setAdapter(new serveTypeAdapter());
                } catch (IOException e) {


                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    public class serveTypeAdapter extends RecyclerView.Adapter<serveTypeAdapter.serveTypeHolder> {


        public class serveTypeHolder extends RecyclerView.ViewHolder {


            private final RelativeLayout rl_server_item;
            private final ImageView iv_serve_pic;
            private final TextView tv_serve_name;

            public serveTypeHolder(@NonNull View itemView) {
                super(itemView);

                rl_server_item = (RelativeLayout) itemView.findViewById(R.id.rl_server_item);
                iv_serve_pic = (ImageView) itemView.findViewById(R.id.iv_serve_pic);
                tv_serve_name = (TextView) itemView.findViewById(R.id.tv_serve_name);
            }
        }


        @NonNull
        @Override
        public serveTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(getActivity()).inflate(R.layout.layout_serve_item, null);
            return new serveTypeHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull serveTypeHolder holder, final int position) {
            Glide.with(getActivity())
                    .load(ConnUtils.getPath() + indexServiceListBean1.getRows().get(position).getImgUrl())
                    .into(holder.iv_serve_pic);
            holder.tv_serve_name.setText(indexServiceListBean1.getRows().get(position).getServiceName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (indexServiceListBean1.getRows().get(position).getServiceName()) {
                        case "城市地铁": {
                            startActivity(new Intent(getActivity(), CityMainActivity.class));
                            break;
                        }
                        case "活动管理": {
                            startActivity(new Intent(getActivity(), ActMainActivity.class));
                            break;
                        }
                        case "数据分析": {
                            startActivity(new Intent(getActivity(), AnaMainActivity.class));
                            break;
                        }
                        case "智慧巴士": {
                            startActivity(new Intent(getActivity(), BusMainActivity.class));
                            break;
                        }
                        case "看电影": {
                            startActivity(new Intent(getActivity(), MoviesMainActivity.class));
                            break;
                        }
                        case "停哪儿": {
                            startActivity(new Intent(getActivity(), ParkMainActivity.class));
                            break;
                        }
                        case "外卖订餐": {
                            startActivity(new Intent(getActivity(), TOMainActivity.class));
                            break;
                        } default:{
                            Intent intent = new Intent(getActivity(), IntentActivity.class);
                            intent.putExtra("title",indexServiceListBean1.getRows().get(position).getServiceName());
                            startActivity(intent);
                            break;
                        }
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return indexServiceListBean1.getRows().size();
        }
    }

}