package com.hx.elb;

import android.content.Intent;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import Utils.DBConnection;

public class DeleteActivity extends AppCompatActivity {

    TextView dingdanhao;
    TextView shouji;
    TextView neirong;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        dingdanhao = (TextView)findViewById(R.id.dingdanhao);
        shouji = (TextView)findViewById(R.id.shouji);
        neirong = (TextView)findViewById(R.id.dingdancontext);
        button = (Button)findViewById(R.id.shanchu);

        Intent intent = getIntent();
        final String theName = intent.getStringExtra("orderName");
        final String thePhone =  intent.getStringExtra("orderPhone");
        final String theId =  intent.getStringExtra("orderId");

        dingdanhao.setText(theId);
        shouji.setText(thePhone);
        neirong.setText(theName);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }
}
