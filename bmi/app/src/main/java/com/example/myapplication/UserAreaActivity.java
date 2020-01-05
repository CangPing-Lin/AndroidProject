package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etAge;
    private TextView welcomemessage;
    private Button btcount;
    private EditText etHeight;
    private EditText etWeight;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        etUsername = (android.widget.EditText) findViewById(R.id.etUsername);
        etAge = (EditText) findViewById(R.id.etAge);
        welcomemessage = (TextView) findViewById(R.id.tvWellcomeMsg);
        btcount = (Button) findViewById(R.id.btcount);
        etHeight = (android.widget.EditText) findViewById(R.id.etHeight);
        etWeight = (EditText) findViewById(R.id.etWeight);
        btcount.setOnClickListener(even1);
        final Button his3 = (Button) findViewById(R.id.his3);
        final Button updata = (Button) findViewById(R.id.updata);

        his3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent HisIntent = new Intent(UserAreaActivity.this,SQLite.class);
                UserAreaActivity.this.startActivity(HisIntent);

                //updata.setEnabled(false);
            }

        });
    }
    private OnClickListener even1 = new OnClickListener() {


            public void onClick(View v) {
                int etheight =
                        Integer.parseInt(etHeight.getText().toString());
                int etweight =
                        Integer.parseInt(etWeight.getText().toString());
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                intent.setClass(UserAreaActivity.this, CountActivity.class);
                bundle.putInt("KEY_Height",etheight);
                //intent.putExtra("KEY_Height", etheight);
                intent.putExtra("KEY_Weight", etweight);
                intent.putExtras(bundle);
                startActivity(intent);
            }


    };



}