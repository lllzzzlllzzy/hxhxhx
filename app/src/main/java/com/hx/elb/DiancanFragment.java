package com.hx.elb;

import android.app.Fragment;
import android.os.Bundle;
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

/**
 * Created by Hello on 2020/10/13.
 */

public class DiancanFragment extends android.support.v4.app.Fragment{
    @Nullable

    private Food[] foods = {new Food("rice",R.drawable.baimifan),
            new Food("Saute vegetable",R.drawable.chaoqingcai),
            new Food("fried eggs with tomatoes",R.drawable.fanqiechaodan),
            new Food("Twice-cooked pork",R.drawable.huiguorou),
            new Food("Shredded pork with pickled peppers",R.drawable.paojiaorousi),
            new Food("Sauteed Shredded Pork with Green Pepper",R.drawable.qingjiaorousi),
            new Food("Eggplant with minced meat",R.drawable.roumoqiezi),
            new Food("Boiled meat",R.drawable.shuizhuroupian),
            new Food("Sliced Boiled Pork with Garlic Sauce",R.drawable.suannibairou),
            new Food("Fried Pork Slices with Salted Pepper",R.drawable.yanjianrou),
            new Food("Yu-Shiang Shredded Pork",R.drawable.yuxiangrousi),
            new Food("Shredded pork with ginger",R.drawable.zijiangrousi),
            new Food("Roast meat",R.drawable.shaobai)
    };

    private List<Food> foodList = new ArrayList<>();
    private FoodAdapter adapter;
    public RecyclerView mRecyclerView;
    private View view;
    private SwipeRefreshLayout swipeRefresh;

    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.diancan,container,false);
        swipeRefresh = (SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e("vv","hello");
                swipeRefresh.setRefreshing(false);
            }
        });

        initFoods();
        initRecyclerView();



        return view;

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void initRecyclerView(){
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new FoodAdapter(foodList);
        mRecyclerView.setAdapter(adapter);


    }

    public void initFoods(){
        foodList.clear();
        for (int i = 0;i<foods.length;i++)
        {
            foodList.add(foods[i]);
        }
    }


}

