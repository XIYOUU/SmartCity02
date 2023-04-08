package com.example.smartcity02.index;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.smartcity02.R;
import com.example.smartcity02.TO.TOMainActivity;
import com.example.smartcity02.act.ActMainActivity;
import com.example.smartcity02.all.IntentActivity;
import com.example.smartcity02.bus.BusMainActivity;
import com.example.smartcity02.city.CityMainActivity;
import com.example.smartcity02.common.ApiServe;
import com.example.smartcity02.commonBean.BannerBean;
import com.example.smartcity02.commonBean.NewBean;
import com.example.smartcity02.commonBean.NewsBean;
import com.example.smartcity02.commonBean.ServeBean;
import com.example.smartcity02.dataAna.AnaMainActivity;
import com.example.smartcity02.movies.MoviesMainActivity;
import com.example.smartcity02.news.NewActivity;
import com.example.smartcity02.park.ParkMainActivity;
import com.example.smartcity02.utils.ConnUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IndexMianFragment extends Fragment implements TabHost.TabContentFactory {


    private SearchView search_bar;
    private Banner banner;
    private RecyclerView recycle;
    private View v;
    private BannerBean bannerBean;
    private List<String> rowsBeans;
    private ServeBean serveBean;
    private ListView list_new;
    private TabHost tabhost;
    private List<ServeBean.RowsBean> rowsBeans1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_index_mian, null, false);

        search_bar = (SearchView) v.findViewById(R.id.search_bar);
        banner = (Banner) v.findViewById(R.id.banner);
        recycle = (RecyclerView) v.findViewById(R.id.recycle);
        list_new = (ListView) v.findViewById(R.id.list_new);
        tabhost = (TabHost) v.findViewById(android.R.id.tabhost);

        search_bar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Toast.makeText(getActivity(), "改变了", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),NewDetailActivity.class));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        initBanner();
        initServe();
        inittabs();
        return v;
    }

    public void initBanner() {
        ApiServe api = ConnUtils.getApi();
        Call<BannerBean> rotationList = api.getRotationList("2");
        rotationList.enqueue(new Callback<BannerBean>() {

            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                bannerBean = response.body();
                rowsBeans = new ArrayList<>();
//                rowsBeans=response.body().getRows();
                for (int i = 0; i < response.body().getRows().size(); i++) {
                    rowsBeans.add(ConnUtils.getPath() + response.body().getRows().get(i).getAdvImg());
                }
                banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object o, ImageView imageView) {
                        Glide.with(getActivity())
                                .load((String) o)
                                .into(imageView);
                    }
                });
                banner.setImages(rowsBeans);
                banner.start();

                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int i) {
                        startActivity(new Intent(getActivity(), NewActivity.class));
                    }
                });
            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable throwable) {

            }
        });
    }

    public void initServe() {
        ApiServe api = ConnUtils.getApi();
        Call<ServeBean> serveList = api.getServeList();
        serveList.enqueue(new Callback<ServeBean>() {

            @Override
            public void onResponse(Call<ServeBean> call, Response<ServeBean> response) {
                rowsBeans1 = response.body().getRows();
                Collections.sort(rowsBeans1, new Comparator<ServeBean.RowsBean>() {
                    @Override
                    public int compare(ServeBean.RowsBean o1, ServeBean.RowsBean o2) {

                        return -(o1.getId() - o2.getId());
                    }
                });

                serveBean = response.body();
                recycle.setLayoutManager(new GridLayoutManager(getActivity(), 5));
                recycle.setAdapter(new myAdapter());
            }

            @Override
            public void onFailure(Call<ServeBean> call, Throwable throwable) {

            }
        });
    }


    public class myAdapter extends RecyclerView.Adapter<myAdapter.myHolder> {
        @NonNull
        @Override
        public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.layout_item, null);

            return new myHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull myHolder holder, final int position) {
            if (position == 9) {
                holder.pic.setImageResource(R.drawable.more);
                holder.name.setText("更多服务");
                holder.ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                        navController.navigate(R.id.action_indexMianFragment_to_allMainragment);
                    }
                });
            } else {
                Glide.with(getActivity())
                        .load(ConnUtils.getPath() + serveBean.getRows().get(position).getImgUrl())
                        .into(holder.pic);
                holder.name.setText(serveBean.getRows().get(position).getServiceName());
                holder.ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (serveBean.getRows().get(position).getServiceName()) {
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
                            }
                            default:{
                                Intent intent = new Intent(getActivity(), IntentActivity.class);
                                intent.putExtra("title",rowsBeans1.get(position).getServiceName());
                                startActivity(intent);
                                break;
                            }
                        }
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        public class myHolder extends RecyclerView.ViewHolder {

            private final LinearLayout ll;
            private final ImageView pic;
            private final TextView name;

            public myHolder(@NonNull View itemView) {
                super(itemView);
                ll = (LinearLayout) itemView.findViewById(R.id.ll);
                pic = (ImageView) itemView.findViewById(R.id.pic);
                name = (TextView) itemView.findViewById(R.id.name);


            }
        }
    }

    public void inittabs() {
        tabhost.setup();
        ApiServe api = ConnUtils.getApi();
        Call<NewBean> categoryList = api.getCategoryList();
        categoryList.enqueue(new Callback<NewBean>() {
            @Override
            public void onResponse(Call<NewBean> call, final Response<NewBean> response) {
                for (int i = 0; i < response.body().getData().size(); i++) {
                    tabhost.addTab(tabhost.newTabSpec("" + i).setIndicator(response.body().getData().get(i).getName()).setContent(IndexMianFragment.this));
                }
                initNews(String.valueOf(response.body().getData().get(Integer.parseInt("0")).getId()));
                tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                    @Override
                    public void onTabChanged(String tabId) {
                        initNews(String.valueOf(response.body().getData().get(Integer.parseInt(tabId)).getId()));
                    }
                });
            }

            @Override
            public void onFailure(Call<NewBean> call, Throwable throwable) {

            }
        });
    }

    public void initNews(String type) {
        ApiServe api = ConnUtils.getApi();
        Call<NewsBean> pressList = api.getPressList(type);
        pressList.enqueue(new Callback<NewsBean>() {
            @Override
            public void onResponse(Call<NewsBean> call, final Response<NewsBean> response) {
                list_new.setAdapter(new BaseAdapter() {
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
                        View v = LayoutInflater.from(getActivity()).inflate(R.layout.list_new, null);
                        ImageView pic = (ImageView) v.findViewById(R.id.pic);
                        TextView title = (TextView) v.findViewById(R.id.title);
                        TextView content = (TextView) v.findViewById(R.id.content);
                        TextView commentNum = (TextView) v.findViewById(R.id.commentNum);
                        TextView publishDate = (TextView) v.findViewById(R.id.publishDate);
                        RelativeLayout rl_new = (RelativeLayout) v.findViewById(R.id.rl_new);
                        Glide.with(getActivity())
                                .load(ConnUtils.getPath() + response.body().getRows().get(position).getCover())
                                .into(pic);
                        title.setText(response.body().getRows().get(position).getTitle());
                        content.setText("内容：" + Html.fromHtml(response.body().getRows().get(position).getContent()));
                        commentNum.setText("评论数：" + response.body().getRows().get(position).getCommentNum());
                        publishDate.setText("发布时间：" + response.body().getRows().get(position).getPublishDate());
                        rl_new.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), NewDetailActivity.class);
                                intent.putExtra("content",response.body().getRows().get(position).getTitle());
                                startActivity(intent);
                            }
                        });
                        return v;
                    }
                });
            }

            @Override
            public void onFailure(Call<NewsBean> call, Throwable throwable) {

            }
        });
    }


    @Override
    public View createTabContent(String tag) {
        View v = new View(getActivity());
        return v;
    }
}