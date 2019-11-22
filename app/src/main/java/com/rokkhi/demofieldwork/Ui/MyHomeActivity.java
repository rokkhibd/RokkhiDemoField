package com.rokkhi.demofieldwork.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.rokkhi.demofieldwork.BaseActivity;
import com.rokkhi.demofieldwork.Model.FWorkers;
import com.rokkhi.demofieldwork.Model.LogSession;
import com.rokkhi.demofieldwork.Model.ViewPagerAdapter;
import com.rokkhi.demofieldwork.R;

import java.util.Calendar;
import java.util.List;

public class MyHomeActivity extends BaseActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_my_home);
        viewPager=(ViewPager)findViewById(R.id.home_viewpager);
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new MyhomeFragment()," ");

        viewPager.setAdapter(viewPagerAdapter);
/*
        checkUserExistence(getActivity());*/

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_my_home;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.my_home;
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
                        MyHomeActivity.super.onBackPressed();

                    }

                }).create().show();
    }


/*

    private void checkUserExistence(FragmentActivity view) {
        FirebaseAuth.AuthStateListener mAuthListener = null;


       FirebaseAuth mAuth=FirebaseAuth.getInstance();
        FirebaseUser  firebaseUser=mAuth.getCurrentUser();
        mAuthListener=mAuth.addAuthStateListener(mAuthListener);

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                firebaseUser=firebaseAuth.getCurrentUser();

                if(firebaseUser==null){
                    gotoLogIN();
                }
                else{
                    userID=firebaseUser.getUid();
                    getfirstdata();
                    final String user_id=firebaseAuth.getCurrentUser().getUid();

                    db.collection(getString(R.string.col_fWorkers)).document(user_id).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {


                            if (documentSnapshot!=null && documentSnapshot.exists()){
//                                String name=documentSnapshot.getString("fw_name");
//                                String imageurl=documentSnapshot.getString("fw_imageUrl");
//                                f_name.setText(name);
//                                Glide.with(getContext()).load(imageurl).into(profileImage);

                                FWorkers fworkers=documentSnapshot.toObject(FWorkers.class);


                                final List< String > usertoken = fworkers.getAtoken();

                                FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(view, new OnSuccessListener<InstanceIdResult>() {
                                    @Override
                                    public void onSuccess(InstanceIdResult instanceIdResult) {
                                        utoken = instanceIdResult.getToken();
                                        editor.putString("token", utoken);
                                        editor.apply();
                                        // Log.d(TAG, "onSuccess: tokenxx "+ useertoken +"xx"+ utoken);
                                        Log.d(TAG, "onSuccess: tttt7 "+signoutstate);
                                        //signoutstate=true;

                                        if (usertoken != null && !usertoken.contains(utoken)  ) {
                                            String logID= db.collection(getString(R.string.col_loginsession)).document().getId();
                                            LogSession logSession= new LogSession(logID,userID,utoken,"FieldWork", Calendar.getInstance().getTime());
                                            db.collection(getString(R.string.col_loginsession)).document(logID).set(logSession)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            Toast.makeText(context,"Welcome!",Toast.LENGTH_SHORT).show();

                                                        }
                                                    });
                                        }
                                    }
                                });


                            }else {
                                gotoFworkerActivty();
                            }
                        }
                    });

                }
            }
        };

    }
*/



}
