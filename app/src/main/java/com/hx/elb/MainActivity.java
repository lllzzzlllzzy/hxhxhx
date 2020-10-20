package com.hx.elb;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String title[]={"点餐","订单"};


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.gouwuche_item:
            Intent intent = new Intent(MainActivity.this,GouwucheActivity.class);
                startActivity(intent);


            break;
            default:
        }return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.fragment_pager);
        initPager();
    }

    public void initPager() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = new Fragment();
                if (fragment != null) {
                    switch (position) {
                        case 0:
                            fragment = new DiancanFragment();
                            break;
                        case 1:
                            fragment = new DingdanFragment();
                            break;

                    }
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
        tabLayout.getTabAt(0).setCustomView(getTabView(0));
        tabLayout.getTabAt(1).setCustomView(getTabView(1));




    }

    private View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_task_item, null);
        TextView textView =(TextView) view.findViewById(R.id.txt_tab_task);
        textView.setText(title[position]);

        return view;
    }





}
