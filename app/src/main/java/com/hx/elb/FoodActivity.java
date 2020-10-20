package com.hx.elb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class FoodActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        textView = (TextView)findViewById(R.id.FOODNAME);
        imageView = (ImageView)findViewById(R.id.FOODIMAGE);
        Intent intent = getIntent();
        final String name_food = intent.getStringExtra("foodName");
        textView.setText(name_food);
        int FoodImageid = intent.getIntExtra("foodImage",0);
        Glide.with(this).load(FoodImageid).into(imageView);
        button = (Button)findViewById(R.id.commitFood);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               bought_food.add(name_food);
                Toast.makeText(FoodActivity.this,"加入购物车成功",Toast.LENGTH_SHORT).show();
            }
        });


    }

       public static List<String> bought_food = new ArrayList<>();


}
