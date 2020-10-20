package com.hx.elb;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import Utils.DBConnection;

public class GuanliyuanActivity extends AppCompatActivity {
    private List<Order> orderList = new ArrayList<>();
    private OrderAdapter_all adapter;

    @SuppressLint("HandlerLeak")
    private Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            orderList.clear();
            Order orders[] = (Order[]) msg.obj;


            for (int i = 0; i < orders.length; i++) {
                orderList.add(orders[i]);

            }
           RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.recycler_guanliyuan_view);
            GridLayoutManager layoutManager = new GridLayoutManager(GuanliyuanActivity.this,1);
            mRecyclerView.setLayoutManager(layoutManager);
            adapter = new OrderAdapter_all(orderList);
            mRecyclerView.setAdapter(adapter);

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanliyuan);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = handler1.obtainMessage();
                int i = DBConnection.GetNumberOfOrder();
               Order orders[] = new Order[i];
                if (DBConnection.ReadAllOrdersInformation()!=null)
                    System.arraycopy(DBConnection.ReadAllOrdersInformation(),0,orders,0,orders.length);
//                Log.e("CCC",books[1].getBookName());
                message.obj = orders;
                // 发消息通知主线程更新UI
                handler1.sendMessage(message);


            }
        }).start();
    }
}
