package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;


import java.util.ArrayList;

public class CountActivity extends AppCompatActivity {
    LineChart lineChart;
    Button b1;
    public double w;
    public double h;
    public double as;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        lineChart=(LineChart)findViewById(R.id.lc);
        Button his = (Button) findViewById(R.id.his);

        b1=(Button)findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Entry>value1 =new ArrayList<>();
                value1.add(new Entry(1,(float)as));



                text_all(value1);

            }
        });




        TextView result1 =(TextView)findViewById(R.id.stext1);
        TextView result2 =(TextView)findViewById(R.id.stext2);
        Button button =(Button)findViewById(R.id.back);
        button.setOnClickListener(event1);
        Intent intent = getIntent();
        Bundle bunde=getIntent().getExtras();
        int height= bunde.getInt("KEY_Height");
        int weight= intent.getIntExtra("KEY_Weight", 1);
        // int a= intent.getIntExtra("KEY_sex", 1);


            w=weight;
            h=height*0.01;
            as= w/(h*h) ;
        result1.setText("你的身高:"+h+"公尺"+",你的體重為:"+w);
        result2.setText("你的Bmi是:"+as);

        his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent LoginIntent = new Intent(CountActivity.this,SQLite.class);
                CountActivity.this.startActivity(LoginIntent);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                intent.setClass(CountActivity.this, SQLite.class);
                //intent.putExtra("KEY_As", as);
                bundle.putInt("KEY_As",(int)as);
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });


    }

    private void text_all(ArrayList<Entry> value1) {
        LineDataSet set1;
        set1 =new LineDataSet(value1,"數據資料");
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set1.setColor(Color.BLACK);
        set1.setLineWidth(2);
        set1.setCircleRadius(4);
        set1.enableDashedHighlightLine(5,5,0);
        set1.setHighlightLineWidth(2);
        set1.setHighlightEnabled(true);
        set1.setHighLightColor(Color.RED);
        set1.setValueTextSize(20);
        set1.setDrawFilled(false);


        LineData data = new LineData(set1);
        lineChart.setData(data);
        lineChart.invalidate();


    }

    private View.OnClickListener event1 = new View.OnClickListener() {


        public void onClick(View v) {
            // TODO Auto-generated method stub
            finish();
        }
    };
    }
