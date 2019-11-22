package com.rokkhi.demofieldwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rokkhi.demofieldwork.Ui.MyhomeFragment;
import com.rokkhi.demofieldwork.Ui.ProfileFragment;
/*

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.nav_bar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();

                if (id==R.id.my_home){

                    MyhomeFragment myhomeFragment=new MyhomeFragment();
                    FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout,myhomeFragment);
                    fragmentTransaction.commit();
                }else if (id==R.id.profile){

                    ProfileFragment profileFragment=new ProfileFragment();
                    FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout,profileFragment);
                    fragmentTransaction.commit();
                }

                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.my_home);
    }

    //TODO:show the exit button

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit from the app?")
                .setIcon(R.drawable.exitblack)
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();

                    }

                }).create().show();
    }
}
*/
