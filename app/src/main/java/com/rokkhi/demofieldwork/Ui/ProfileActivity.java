package com.rokkhi.demofieldwork.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.rokkhi.demofieldwork.BaseActivity;
import com.rokkhi.demofieldwork.Model.ViewPagerAdapter;
import com.rokkhi.demofieldwork.R;

public class ProfileActivity extends BaseActivity {

    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile);

        viewPager=(ViewPager)findViewById(R.id.profile_viewpager);

        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new ProfileFragment(),"Rock Music Section");

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_profile;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.profile;
    }
}
