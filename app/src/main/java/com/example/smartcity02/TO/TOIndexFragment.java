package com.example.smartcity02.TO;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartcity02.R;
import com.example.smartcity02.TOBean.TOBannerBean;
import com.example.smartcity02.TOBean.TOLacBean;
import com.example.smartcity02.TOBean.TOSellerListBean2;
import com.example.smartcity02.TOBean.TOThemeBean;
import com.example.smartcity02.common.ApiServe;
import com.example.smartcity02.utils.ConnUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TOIndexFragment extends Fragment {


    private View v;
    private TextView loc;
    private SearchView search_bar;
    private Banner to_banner;
    private RecyclerView rec;
    private TOThemeBean toThemeBean;
    private RecyclerView rec_common;
    private TOSellerListBean2 toSellerListBean2;
    private ListView seller_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_t_o_index, container, false);
        loc = (TextView) v.findViewById(R.id.loc);
        search_bar = (SearchView) v.findViewById(R.id.search_bar);
        to_banner = (Banner) v.findViewById(R.id.to_banner);
        rec = (RecyclerView) v.findViewById(R.id.rec);
        seller_list = (ListView) v.findViewById(R.id.seller_list);
        rec_common = (RecyclerView) v.findViewById(R.id.rec_common);


        ImageView back = (ImageView) v.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        initLoc();
        initBanner();
        initSellerList();
        initCommon();
        return v;
    }

    public void initLoc() {
        ApiServe api = ConnUtils.getApi();
        Call<TOLacBean> gpsLoc = api.getGPSLoc();
        gpsLoc.enqueue(new Callback<TOLacBean>() {
            @Override
            public void onResponse(Call<TOLacBean> call, Response<TOLacBean> response) {
                loc.setText("当前：" + response.body().getData().getCity());
            }

            @Override
            public void onFailure(Call<TOLacBean> call, Throwable throwable) {

            }
        });
    }

    public void initBanner() {
        ApiServe api = ConnUtils.getApi();
        final Call<TOBannerBean> toBanner = api.getTOBanner();
        toBanner.enqueue(new Callback<TOBannerBean>() {
            @Override
            public void onResponse(Call<TOBannerBean> call, final Response<TOBannerBean> response) {
                List<String> img = new ArrayList<>();
                for (int i = 0; i < response.body().getRows().size(); i++) {
                    img.add(ConnUtils.getPath() + response.body().getRows().get(i).getAdvImg());

                }
                to_banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object o, ImageView imageView) {
                        Glide.with(getActivity())
                                .load((String) o)
                                .into(imageView);

                    }
                });
                to_banner.setImages(img);
                to_banner.start();
                to_banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int i) {
                        Intent intent = new Intent(getActivity(), TOBannerDetailActivity.class);
                        intent.putExtra("getTargetId", response.body().getRows().get(i).getTargetId());

                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<TOBannerBean> call, Throwable throwable) {

            }
        });
    }

    public void initSellerList(){
        ApiServe api = ConnUtils.getApi();
        Call<TOThemeBean> themeList = api.getThemeList();
        themeList.enqueue(new Callback<TOThemeBean>() {



            @Override
            public void onResponse(Call<TOThemeBean> call, Response<TOThemeBean> response) {
                toThemeBean = response.body();
                rec.setLayoutManager(new GridLayoutManager(getActivity(),5));
                rec.setAdapter(new SellerAdapter());
            }

            @Override
            public void onFailure(Call<TOThemeBean> call, Throwable throwable) {

            }
        });
    }
    public class SellerAdapter extends RecyclerView.Adapter<SellerAdapter.SellerHolder>{
        @NonNull
        @Override
        public SellerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.layout_item, null);

            return new SellerHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull SellerHolder holder, final int position) {
            Glide.with(getActivity())
                    .load(ConnUtils.getPath()+toThemeBean.getData().get(position).getImgUrl())
                    .into(holder.pic);
            holder.name.setText(toThemeBean.getData().get(position).getThemeName());
            holder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(),ToSellerActivity.class);
                    intent.putExtra("id",toThemeBean.getData().get(position).getId());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return toThemeBean.getData().size();
        }

        public class SellerHolder extends RecyclerView.ViewHolder {

            private final ImageView pic;
            private final TextView name;
            private final LinearLayout ll;

            public SellerHolder(@NonNull View itemView) {
                super(itemView);
                pic = (ImageView) itemView.findViewById(R.id.pic);
                name = (TextView) itemView.findViewById(R.id.name);
                ll = (LinearLayout) itemView.findViewById(R.id.ll);
            }
        }
    }

    public void initCommon(){
        ApiServe api = ConnUtils.getApi();
        Call<TOSellerListBean2> y = api.getSellerList("y");
        y.enqueue(new Callback<TOSellerListBean2>() {



            @Override
            public void onResponse(Call<TOSellerListBean2> call, Response<TOSellerListBean2> response) {
                toSellerListBean2 = response.body();
                rec_common.setLayoutManager(new GridLayoutManager(getActivity(),2));
                rec_common.setAdapter(new CAdapter());
            }

            @Override
            public void onFailure(Call<TOSellerListBean2> call, Throwable throwable) {

            }
        });
    }
    public class CAdapter extends RecyclerView.Adapter<CAdapter.CHorder>{
        @NonNull
        @Override
        public CHorder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.rec_common, null);


            return new CHorder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull CHorder holder, int position) {
            Glide.with(getActivity()).load(ConnUtils.getPath()+toSellerListBean2.getRows().get(position).getImgUrl())
                    .into(holder.pic);
            holder.name.setText("店家名称："+toSellerListBean2.getRows().get(position).getName());
            holder.score.setText("评分："+toSellerListBean2.getRows().get(position).getScore());
            holder.saleNum3hour.setText("近3小时下单数："+toSellerListBean2.getRows().get(position).getSaleNum3hour());
        }

        @Override
        public int getItemCount() {
            return toSellerListBean2.getRows().size();
        }

        public class CHorder extends RecyclerView.ViewHolder {

            private final ImageView pic;
            private final TextView name;
            private final TextView score;
            private final TextView saleNum3hour;

            public CHorder(@NonNull View itemView) {
                super(itemView);
                pic = (ImageView) itemView.findViewById(R.id.pic);
                name = (TextView) itemView.findViewById(R.id.name);
                score = (TextView) itemView.findViewById(R.id.score);
                saleNum3hour = (TextView) itemView.findViewById(R.id.saleNum3hour);
            }
        }
    }
}