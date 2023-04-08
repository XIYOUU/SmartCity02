package freemodule;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.smartcity02.R;

import java.util.ArrayList;
import java.util.List;

public class Fun3Activity extends AppCompatActivity {
    ListView listview;
    List covers = new ArrayList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fun3_activity);

        listview = findViewById(R.id.f3_listview);
        covers = new ArrayList();
        covers.add(R.mipmap.logo_a);
        covers.add(R.mipmap.logo_b);
        covers.add(R.mipmap.logo_c);
        covers.add(R.mipmap.logo_d);
        covers.add(R.mipmap.logo_a);
        covers.add(R.mipmap.logo_b);
        covers.add(R.mipmap.logo_c);
        covers.add(R.mipmap.logo_d);

        class MyAdapter extends BaseAdapter {
            String content1,content2;
            List data;
            public MyAdapter(List data, String content1, String content2){
                this.data = data;
                this.content1 = content1;
                this.content2 = content2;
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

        listview.setAdapter(new MyAdapter(covers,"扶贫目标","查看详情 >"));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), FInfoActivity.class);
                intent.putExtra("title","扶贫目标"+(position+1));
                intent.putExtra("content1","扶贫目标环境情况"+(position+1));
                intent.putExtra("content2","");
                startActivity(intent);
            }
        });
    }
}
