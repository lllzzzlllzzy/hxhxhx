package com.hx.elb;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Utils.DBConnection;

public class LoginActivity extends AppCompatActivity {
    public static String MYPHONENUMBER ;
    EditText phone;
    EditText password;
    Button button;
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone = (EditText)findViewById(R.id.phonenumber_input);
        password = (EditText)findViewById(R.id.password_input);
        button = (Button)findViewById(R.id.enter);
        button1 = (Button)findViewById(R.id.regi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 final String phonenum = phone.getText().toString();
                 final String pass = password.getText().toString();
                Log.e("vvv",phonenum);

                if (phonenum.equals("hx")&&pass.equals("hx")){
                    Intent intent = new Intent(LoginActivity.this,GuanliyuanActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {

                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                       if ( DBConnection.SelectUser(phonenum,pass))
                       {
                           MYPHONENUMBER = phonenum;
                           Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                           startActivity(intent);
                           finish();
                           Looper.prepare();
                           Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                           Looper.loop();

                           Log.e("vv","vvdew");

                       }
                        else {

                       }
                    }
                }).start();



            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}
