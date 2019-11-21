package com.rokkhi.demofieldwork.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.WriteBatch;
import com.rokkhi.demofieldwork.Model.AllStringValues;
import com.rokkhi.demofieldwork.Model.FBPeople;
import com.rokkhi.demofieldwork.Model.FBuildings;
import com.rokkhi.demofieldwork.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateBldngInfoActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView houseImage;
    EditText house_name,total_guards,building_status,followup_date,total_floor,flat_floor,house_address,flat_format,
            owner_name,owner_number,careataker_name,caretaker_number;
    FBuildings fBuildings;
    AllStringValues allStringValues;
    FBPeople fbPeople;
    Button updateInfo_Button;


    FirebaseAuth mAuth;
    FirebaseFirestore db;
    ProgressBar spinKitProgress;
    DocumentReference docRef;
    DocumentSnapshot documentSnapshot;
    List<FBPeople> fbPeopleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bldng_info);

        fBuildings=new FBuildings();
        fBuildings=(FBuildings) getIntent().getSerializableExtra("fbuildings");
        mAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        fbPeople=new FBPeople();
        fbPeopleList=new ArrayList<>();

        house_name=findViewById(R.id.update_bldng_houseName);
        houseImage=findViewById(R.id.update_bldng_image);
       // total_guards=findViewById(R.id.update_bldng_totalGuards);
        building_status=findViewById(R.id.update_bldng_bldngStatus);
        followup_date=findViewById(R.id.update_bldng_followupDate);
        total_floor=findViewById(R.id.update_bldng_totalfloor);
        flat_floor=findViewById(R.id.update_bldng_flatpfloor);
        house_address=findViewById(R.id.update_bldng_houseAddress);
        flat_format=findViewById(R.id.update_bldng_flatformat);
        owner_name=findViewById(R.id.update_bldng_ownername);
        owner_number=findViewById(R.id.update_bldng_ownernmbr);
        careataker_name=findViewById(R.id.update_bldng_caretakername);
        caretaker_number=findViewById(R.id.bldng_edit_caretakernmbr);

        getThePeoplesInfo();


        updateInfo_Button=findViewById(R.id.update_bldng_updatebtn);

        spinKitProgress=findViewById(R.id.spin_kit);
        Wave wave=new Wave();
        spinKitProgress.setIndeterminateDrawable(wave);

        house_name.setText(fBuildings.getHousename());
        //total_guards.setText(fBuildings.());
        building_status.setText(fBuildings.getStatus());
//        followup_date.setText((CharSequence) fBuildings.getFollowupdate());
        total_floor.setText(String.valueOf(fBuildings.getTotalfloor()));
        flat_floor.setText(String.valueOf(fBuildings.getFlatperfloor()));
        house_address.setText(fBuildings.getB_address());
        flat_format.setText(fBuildings.getFlatformat());
        Glide.with(this).load(fBuildings.getB_imageUrl().get(0)).fitCenter().placeholder(R.drawable.building).into(houseImage);

        allStringValues=new AllStringValues();
        followup_date.setOnClickListener(this);
//        total_guards.setOnClickListener(this);
        updateInfo_Button.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.update_bldng_followupDate){
            AllStringValues.showCalendar(this,followup_date);
        }/*else if (v.getId()==R.id.update_bldng_totalGuards){
            total_guards.setFocusableInTouchMode(true);
        }*/else if (v.getId()==R.id.update_bldng_bldngStatus){
            building_status.setFocusableInTouchMode(true);
        }else if (v.getId()==R.id.update_bldng_updatebtn){
            spinKitProgress.setVisibility(View.VISIBLE);
            //updateBuildingInfo();
        }
    }

    private void updateBuildingInfo() {

        WriteBatch batch=db.batch();

        String update_totalG=total_guards.getText().toString();
        String update_followdate=followup_date.getText().toString();

        DocumentReference docRef=db.collection(getString(R.string.col_fBuildings)).document(fBuildings.getBuild_id());
        //batch.update(docRef,"b_guards",update_totalG);
        //batch.update(docRef,"b_followupdate",update_followdate);

        Map<String,Object> map=new HashMap<>();
        map.put("b_guards",update_totalG);
       // docRef.set(map, SetOptions.merge());


        //batch.set(docRef,"b_followupdate".)

        docRef.set(map, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(UpdateBldngInfoActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                    spinKitProgress.setVisibility(View.INVISIBLE);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateBldngInfoActivity.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                spinKitProgress.setVisibility(View.INVISIBLE);
            }
        });

    }


    private void updatebuildingInfo(){

        WriteBatch batch=db.batch();



    }

    private void getThePeoplesInfo() {

        db.collection(getString(R.string.col_fBbuildingContacts)).whereEqualTo("b_code",fBuildings.getB_code()).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot documentSnapshot:task.getResult()){
                            FBPeople fbPeople=documentSnapshot.toObject(FBPeople.class);
                            Log.e("xxxx",fbPeople.getDesignation());
                            Log.e("xxxx",fbPeople.getName());
                            Log.e("xxxx",fbPeople.getNumber());




                        }
                    }
                });

    }
}
