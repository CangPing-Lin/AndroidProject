package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CountActivity extends AppCompatActivity {
    private double w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        TextView result1 =(TextView)findViewById(R.id.stext1);
        TextView result2 =(TextView)findViewById(R.id.stext2);
        Button button =(Button)findViewById(R.id.back);
        button.setOnClickListener(event1);
        Intent intent = getIntent();
        Bundle bunde=getIntent().getExtras();
        int height= intent.getIntExtra("KEY_Height", 1);
        int weight= intent.getIntExtra("KEY_Weight", 1);
        // int a= intent.getIntExtra("KEY_sex", 1);


            w=weight/(height*height);

        result1.setText("你的身高:"+height+"公分"+",標準重量為:"+w);
        result2.setText("你的性別是:");

    }
    private View.OnClickListener event1 = new View.OnClickListener() {


        public void onClick(View v) {
            // TODO Auto-generated method stub
            finish();
        }
    };
    }
