package com.example.smartcity02.index;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.smartcity02.R;

public class NewDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);

        Intent intent = getIntent();
        String content = intent.getStringExtra("content");

        TextView new_detail = (TextView) findViewById(R.id.new_detail);
        new_detail.setText(content);
    }
}