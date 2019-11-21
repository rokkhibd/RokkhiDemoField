package com.rokkhi.demofieldwork.Ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.os.Bundle;

import com.rokkhi.demofieldwork.BaseActivity;
import com.rokkhi.demofieldwork.Model.ViewPagerAdapter;
import com.rokkhi.demofieldwork.R;

public class PaymentHistoryActivity extends BaseActivity {

    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_payment_history);

        viewPager=(ViewPager)findViewById(R.id.payment_viewpager);
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new PaymentHistoryFragment()," ");

        viewPager.setAdapter(viewPagerAdapter);

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_payment_history;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.payment;
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
                        PaymentHistoryActivity.super.onBackPressed();

                    }

                }).create().show();
    }
}
