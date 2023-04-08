package freemodule;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity02.R;


public class Fun4Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fun4_activity);

        final EditText content = findViewById(R.id.fun4_content);
        Button submit = findViewById(R.id.fun4_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String words = content.getText().toString();
                if(words.length() > 10){
                    Toast.makeText(getApplicationContext(),"发布成功", Toast.LENGTH_SHORT).show();
                    content.setText("");
                }else{
                    Toast.makeText(getApplicationContext(),"内容太短啦", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
