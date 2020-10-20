package com.hx.elb;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Utils.DBConnection;

public class GouwucheActivity extends AppCompatActivity {


    private TextView textView;
    private Button button;
    private String thetext = " ";
    private String id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gouwuche);
        id = LoginActivity.MYPHONENUMBER+ System.currentTimeMillis();//phone + time =id

        textView = (TextView)findViewById(R.id.allfood);
        button = (Button)findViewById(R.id.tijiaodingdan);
        for (int i = 0;i<FoodActivity.bought_food.size();i++)
        {
            thetext =thetext+","+FoodActivity.bought_food.get(i);
        }

        textView.setText(thetext);
        textView.clearComposingText();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        DBConnection.InserIntoOrder(id,LoginActivity.MYPHONENUMBER,thetext);
                        Looper.prepare();
                        Toast.makeText(GouwucheActivity.this,"提交订单成功",Toast.LENGTH_SHORT).show();
                        Looper.loop();

                    }
                }).start();







                finish();


            }
        });


    }
    @Override
    protected void onDestroy() {
        textView.setText("");
        FoodActivity.bought_food.clear();
        super.onDestroy();
    }
}
