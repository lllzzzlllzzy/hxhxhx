package com.hx.elb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Utils.DBConnection;

public class RegisterActivity extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    EditText phone;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editText1 = (EditText)findViewById(R.id.password);
        editText2 = (EditText)findViewById(R.id.password_again);
        phone = (EditText)findViewById(R.id.phonenumber);
        button = (Button)findViewById(R.id.tijiaozhuce);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  String phoneNumber = phone.getText().toString();
                final String Password = editText2.getText().toString();
                final String Password1 = editText1.getText().toString();
                Log.e("xxx",phoneNumber);
                Log.e("xxx",Password);
                Log.e("xxx",Password1);
                if(Password.equals(Password1))
                {
                    new Thread(new Runnable() {
                    @Override
                    public void run() {

                        DBConnection.InserIntoUser(phoneNumber,Password1);
                        Looper.prepare();
                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        Looper.loop();


                    }
                }).start();
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }
}
