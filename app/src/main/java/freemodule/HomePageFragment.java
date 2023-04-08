package freemodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.smartcity02.R;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends Fragment {
    private Banner banner;
    List images = new ArrayList();
    List covers = new ArrayList();
    private LinearLayout fun1;
    private LinearLayout fun2;
    private LinearLayout fun3;
    private LinearLayout fun4;
    private ListView listview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.homepage_fragment, null, false);
        initView(view);
        images.add(R.mipmap.banner1);
        images.add(R.mipmap.banner2);
        images.add(R.mipmap.banner3);
        images.add(R.mipmap.banner4);
        images.add(R.mipmap.banner5);
        banner.setImages(images);

        covers.add(R.mipmap.banner1);
        covers.add(R.mipmap.banner2);
        covers.add(R.mipmap.banner3);
        covers.add(R.mipmap.banner4);
        covers.add(R.mipmap.banner5);
        covers.add(R.mipmap.banner1);
        covers.add(R.mipmap.banner2);
        covers.add(R.mipmap.banner3);
        covers.add(R.mipmap.banner4);
        covers.add(R.mipmap.banner5);

        class MyAdapter extends BaseAdapter {
            String content1,content2;
            List data;
            public MyAdapter(List data, String content1, String content2){
                this.data = data;
                this.content1 = content1;
                this.content2 = content2 + " >";
            }

            @Override
            public int getCount() {
                return data.size();
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
                View v = getLayoutInflater().inflate(R.layout.listview_item,null,false);
                ImageView cover = v.findViewById(R.id.f_cover);
                TextView name = v.findViewById(R.id.chunzhuang);
                TextView tv2 = v.findViewById(R.id.content2);
                cover.setImageResource((int)data.get(position));
                name.setText(content1+(position+1));
                tv2.setText(content2);
                return v;
            }
        }

        class MyImageLoader extends ImageLoader {
            @Override
            public void displayImage(Context context, Object o, ImageView imageView) {
                imageView.setImageResource((int) o);
            }
        }
        banner.setImageLoader(new MyImageLoader());
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int i) {
                Intent intent = new Intent(getActivity(),FInfoActivity.class);
                intent.putExtra("title","新闻标题"+(i+1));
                intent.putExtra("content1","新闻内容"+(i+1));
                intent.putExtra("content2","2022-01-02");
                startActivity(intent);
            }
        });

        listview.setAdapter(new MyAdapter(covers,"村子名称","查看详情"));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),FInfoActivity.class);
                intent.putExtra("title","村子名称"+(position+1));
                intent.putExtra("content1","村子详情"+(position+1));
                intent.putExtra("content2","2022-01-01");
                startActivity(intent);
            }
        });

        fun1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Fun1Activity.class);
                startActivity(intent);
            }
        });

        fun2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Fun2Activity.class);
                startActivity(intent);
            }
        });

        fun3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Fun3Activity.class);
                startActivity(intent);
            }
        });

        fun4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Fun4Activity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initView(View view) {
        banner = (Banner) view.findViewById(R.id.f_banner);
        fun1 = (LinearLayout) view.findViewById(R.id.fun1);
        fun2 = (LinearLayout) view.findViewById(R.id.fun2);
        fun3 = (LinearLayout) view.findViewById(R.id.fun3);
        fun4 = (LinearLayout) view.findViewById(R.id.fun4);
        listview = (ListView) view.findViewById(R.id.f_listview);
    }
}
