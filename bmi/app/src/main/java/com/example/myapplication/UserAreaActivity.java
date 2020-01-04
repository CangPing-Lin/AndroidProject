package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText etUsername = (android.widget.EditText) findViewById(R.id.etUsername);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final TextView welcomemessgage = (TextView) findViewById(R.id.tvWellcomeMsg);
        final Button btcount = (Button) findViewById(R.id.btcount);

        btcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent countIntent = new Intent(UserAreaActivity.this,CountActivity.class);
                UserAreaActivity.this.startActivity(countIntent);
            }

        });
    }
}
