package com.rokkhi.demofieldwork.Ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.os.Bundle;

import com.rokkhi.demofieldwork.BaseActivity;
import com.rokkhi.demofieldwork.Model.ViewPagerAdapter;
import com.rokkhi.demofieldwork.R;

public class NoticeActivity extends BaseActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_notice);


        viewPager=(ViewPager)findViewById(R.id.notice_viewpager);

        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new NoticeFragment()," ");

        viewPager.setAdapter(viewPagerAdapter);

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_notice;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.notice;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit from the app?")
                .setIcon(R.drawable.exitblack)
                .setMessage("Are you sure, you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NoticeActivity.super.onBackPressed();

                    }

                }).create().show();
    }
}
