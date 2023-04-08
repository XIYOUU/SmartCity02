package freemodule;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity02.R;


public class FInfoActivity extends AppCompatActivity {
    private TextView title;
    private TextView content1;
    private TextView content2;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_info);
        initView();


        intent = getIntent();
        String Stitle = intent.getStringExtra("title");
        String Scontent1 = intent.getStringExtra("content1");
        String Scontent2 = intent.getStringExtra("content2");

        for (int j=0; j<6; j++){
            Scontent1= Scontent1 +" ";
            Scontent1+=Scontent1;
        }

        title.setText(Stitle);
        content1.setText(Scontent1);
        content2.setText(Scontent2);

    }

    private void initView() {
        title = (TextView) findViewById(R.id.info_title);
        content1 = (TextView) findViewById(R.id.info_content1);
        content2 = (TextView) findViewById(R.id.info_content2);
    }
}
