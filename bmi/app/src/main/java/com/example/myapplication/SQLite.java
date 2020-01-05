package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLite extends AppCompatActivity {
    public BmiData DH = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_data);
        DH = new BmiData(this);
        final Button back = (Button) findViewById(R.id.back);
        final Button updata = (Button) findViewById(R.id.updata);

        add();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent backIntent = new Intent(SQLite.this,UserAreaActivity.class);
                SQLite.this.startActivity(backIntent);
            }

        });
        updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                int bmi= intent.getIntExtra("KEY_As", 1);
                //int bmi= bundle.getInt("KEY_As");
                String s =Integer.toString(bmi);
                SQLiteDatabase db = DH.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("_title", "你的bmi是:"+s.toString());//載入資料123
                db.insert("TB", null, values);//寫入123
                ListView LV1 = (ListView) findViewById(R.id.LV);//讀取元件
                add();
                updata.setEnabled(false);
            }

        });

    }

    private void add() {
        SQLiteDatabase db = DH.getWritableDatabase();
        /*ContentValues values = new ContentValues();
        values.put("_title", s.toString());//載入資料123
        db.insert("TB", null, values);//寫入123*/
        ListView LV1 = (ListView) findViewById(R.id.LV);//讀取元件

//查詢資料庫並載入
        Cursor cursor = db.query("TB", new String[]{"_id", "_title"}, null, null, null, null, null);
        List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
        cursor.moveToFirst();

//叫出資料庫的資料

        for (int i = 0; i < cursor.getCount(); i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("_id", cursor.getString(0));
            item.put("_title", cursor.getString(1));
            items.add(item);//新增
            cursor.moveToNext();//移下一筆資料        }
            SimpleAdapter SA = new SimpleAdapter(this, items, android.R.layout.simple_expandable_list_item_2, new String[]{"_id", "_title"}, new int[]{android.R.id.text1, android.R.id.text2});
            LV1.setAdapter(SA);
        }
    }
}
