package com.rokkhi.demofieldwork.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.rokkhi.demofieldwork.BaseActivity;
import com.rokkhi.demofieldwork.Model.ViewPagerAdapter;
import com.rokkhi.demofieldwork.R;

public class MyHomeActivity extends BaseActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_my_home);

        viewPager=(ViewPager)findViewById(R.id.home_viewpager);
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new MyhomeFragment()," ");


    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_my_home;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.my_home;
    }
}
