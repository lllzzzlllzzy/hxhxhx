package com.hx.elb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Hello on 2020/10/13.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>{

    private Context mContext;
    private List<Food> mFoodList;
    private int count = 0;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView foodImage;
        TextView foodName;

        public ViewHolder(View view)
        {
            super(view);
            cardView=(CardView)view;
            foodImage = (ImageView)view.findViewById(R.id.food_image);
            foodName = (TextView)view.findViewById(R.id.food_name);

        }
    }
    public FoodAdapter(List<Food> foodList){
        mFoodList = foodList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext==null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.food_item,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Food  food = mFoodList.get(position);
        holder.foodName.setText(food.getName());
        Glide.with(mContext).load(food.getImageId()).into(holder.foodImage);
        final View view = holder.cardView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,FoodActivity.class);
                intent.putExtra("foodName",food.getName());
                intent.putExtra("foodImage",food.getImageId());
                mContext.startActivity(intent);



            }
        });
    }

    @Override
    public int getItemCount() {
        return mFoodList.size();
    }

}
