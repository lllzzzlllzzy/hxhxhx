package com.hx.elb;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import Utils.DBConnection;

/**
 * Created by Hello on 2020/10/13.
 */

public class OrderAdapter_all extends RecyclerView.Adapter<OrderAdapter_all.ViewHolder>{

    private Context mContext;
    private List<Order> mList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView phoneNumber;
        TextView foodName;

        public ViewHolder(View view)
        {
            super(view);
            cardView=(CardView)view;
            phoneNumber = (TextView)view.findViewById(R.id.phoneNumber_order);
            foodName = (TextView)view.findViewById(R.id.food_name_order);

        }
    }
    public OrderAdapter_all(List<Order> orderList){
        mList = orderList;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Order  order = mList.get(position);
        holder.foodName.setText(order.getContext());
        holder.phoneNumber.setText(order.getUserPhone());
        final View view = holder.cardView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,DeleteActivity.class);
                intent.putExtra("orderName",order.getContext());
                intent.putExtra("orderPhone",order.getUserPhone());
                intent.putExtra("orderId",order.getId());
//                intent.putExtra("position",position);
                mContext.startActivity(intent);



            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DBConnection.DeleteOrderById(order.getId());
                        Looper.prepare();
                        Toast.makeText(mContext,"删除成功",Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
                removeItem(position);
                return true;
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext==null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.order_item,parent,false);


        return new ViewHolder(view);
    }




    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void removeItem(int position){
        mList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,getItemCount());
    }


}

