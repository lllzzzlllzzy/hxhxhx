package com.hx.elb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import Utils.DBConnection;

/**
 * Created by Hello on 2020/10/13.
 */

public class DingdanFragment extends android.support.v4.app.Fragment{

    @SuppressLint("HandlerLeak")
    private Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            orderList.clear();
            Order orders[] = (Order[]) msg.obj;

            for (int i = 0; i < orders.length; i++) {
                orderList.add(orders[i]);

            }
            mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
            mRecyclerView.setLayoutManager(layoutManager);
            adapter = new OrderAdapter(orderList);


            mRecyclerView.setAdapter(adapter);
        }

    };

    @SuppressLint("HandlerLeak")
    private Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            orderList.clear();
            Order orders[] = (Order[]) msg.obj;

            for (int i = 0; i < orders.length; i++) {
                orderList.add(orders[i]);

            }
            mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
            mRecyclerView.setLayoutManager(layoutManager);
            adapter = new OrderAdapter(orderList);


            mRecyclerView.setAdapter(adapter);
            swipeRefresh.setRefreshing(false);
        }

    };
    @Nullable

    private List<Order> orderList = new ArrayList<>();
    private OrderAdapter adapter;
    public RecyclerView mRecyclerView;
    private View view;
    private SwipeRefreshLayout swipeRefresh;




    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.diancan,container,false);
        swipeRefresh = (SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh);
//        swipeRefresh.setColorSchemeColors("#0000000");
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });








//        initRecyclerView();
        //1.将订单信息加入数据库
        //2.从数据库重新读入数据
        //3.刷新订单界面


        new Thread(new Runnable() {
            @Override
            public void run() {
                // 调用数据库工具类DBUtils的getInfoByName方法获取数据库表中数据
                Message message = handler1.obtainMessage();
                int i = DBConnection.GetNumberOfMyOrder(LoginActivity.MYPHONENUMBER);
                Order orders[] = new Order[i];
                if (DBConnection.ReadAllMyOrdersInformation(LoginActivity.MYPHONENUMBER)!=null)
                    System.arraycopy(DBConnection.ReadAllMyOrdersInformation(LoginActivity.MYPHONENUMBER),0,orders,0,orders.length);
                message.obj = orders;
                // 发消息通知主线程更新UI
                handler1.sendMessage(message);


//            aBoolean = bundle.getBoolean("isadd");

            }
        }).start();
        return view;

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void refresh(){
        new Thread(new Runnable() {
            @Override
            public void run() {
//                try{
//                    Thread.sleep(2000);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
                Message message = handler2.obtainMessage();
                int i = DBConnection.GetNumberOfMyOrder(LoginActivity.MYPHONENUMBER);
                Order orders[] = new Order[i];
                if (DBConnection.ReadAllMyOrdersInformation(LoginActivity.MYPHONENUMBER)!=null)
                    System.arraycopy(DBConnection.ReadAllMyOrdersInformation(LoginActivity.MYPHONENUMBER),0,orders,0,orders.length);
                message.obj = orders;
                // 发消息通知主线程更新UI
                handler2.sendMessage(message);

//                adapter.notifyDataSetChanged();
//                swipeRefresh.setRefreshing(false);


            }
        }).start();
    }


}

