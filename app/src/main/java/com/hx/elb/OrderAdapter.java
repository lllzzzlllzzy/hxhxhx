package com.hx.elb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Hello on 2020/10/13.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{

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
    public OrderAdapter(List<Order> orderList){
        mList = orderList;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Order  order = mList.get(position);
        holder.foodName.setText(order.getContext());
        holder.phoneNumber.setText(LoginActivity.MYPHONENUMBER);
        final View view = holder.cardView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(mContext,FoodActivity.class);
//                intent.putExtra("foodName",food.getName());
//                intent.putExtra("foodImage",food.getImageId());
//                mContext.startActivity(intent);



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


}

