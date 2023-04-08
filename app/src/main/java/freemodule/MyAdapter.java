package freemodule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcity02.R;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    List covers;
    String content1;
    String content2;

    public MyAdapter(LayoutInflater layoutInflater, List covers, String content1, String content2){
        this.layoutInflater = layoutInflater;
        this.covers = covers;
        this.content1 = content1;
        this.content2 = content2;
    };
    @Override
    public int getCount() {
        return covers.size();
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
        View v = layoutInflater.inflate(R.layout.f_listview_item,null,false);
        ImageView cover = v.findViewById(R.id.cover);
        TextView content1 = v.findViewById(R.id.content1);
        TextView content2 = v.findViewById(R.id.content2);

        cover.setImageResource((int)covers.get(position));
        return v;
    }
}
