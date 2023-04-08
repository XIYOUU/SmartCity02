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

public class Fun1Activity extends AppCompatActivity {

    List data = new ArrayList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fun1_activity);

        data.add(R.mipmap.banner1);
        data.add(R.mipmap.banner2);
        data.add(R.mipmap.banner3);
        data.add(R.mipmap.banner4);
        data.add(R.mipmap.banner5);
        data.add(R.mipmap.banner1);
        data.add(R.mipmap.banner2);
        data.add(R.mipmap.banner3);
        data.add(R.mipmap.banner4);
        data.add(R.mipmap.banner5);

        ListView listview = findViewById(R.id.fun1_listview);
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
                final TextView tv2 = v.findViewById(R.id.content2);
                cover.setImageResource((int)data.get(position));
                name.setText(content1+(position+1));
                tv2.setText(content2);
                tv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String flag = tv2.getText().toString();
                        if(flag.equals(content2)){
                            tv2.setText("已"+content2);
                        }else{
                            tv2.setText(content2);
                        }

                    }
                });
                return v;
            }
        }
        listview.setAdapter(new MyAdapter(data,"案例","点赞"));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),FInfoActivity.class);
                intent.putExtra("title","案例标题"+(position+1));
                intent.putExtra("content1","案例内容"+(position+1));
                intent.putExtra("content2","2022-01-01");
                startActivity(intent);
            }
        });
    }
}
