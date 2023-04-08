package com.example.smartcity02.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smartcity02.R;

public class UserFeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feedback);

        Button bun_obj = (Button) findViewById(R.id.bun_obj);
        bun_obj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserFeedbackActivity.this, "提交成功！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}