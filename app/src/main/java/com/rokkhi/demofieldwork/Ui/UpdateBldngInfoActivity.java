package com.rokkhi.demofieldwork.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import com.google.firebase.firestore.WriteBatch;
import com.rokkhi.demofieldwork.Model.AllStringValues;
import com.rokkhi.demofieldwork.Model.FBuildings;
import com.rokkhi.demofieldwork.R;

import java.util.HashMap;
import java.util.Map;

public class UpdateBldngInfoActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView houseImage;
    EditText house_name,total_guards,building_status,followup_date,total_floor,flat_floor,house_address,flat_format;
    FBuildings fBuildings;
    AllStringValues allStringValues;
    Button updateInfo_Button;

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    ProgressBar spinKitProgress;
    DocumentReference docRef;
    DocumentSnapshot documentSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bldng_info);

        fBuildings=(FBuildings) getIntent().getSerializableExtra("fbuildings");
        mAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

        house_name=findViewById(R.id.update_bldng_houseName);
        houseImage=findViewById(R.id.update_bldng_image);
        total_guards=findViewById(R.id.update_bldng_totalGuards);
        building_status=findViewById(R.id.update_bldng_bldngStatus);
        followup_date=findViewById(R.id.update_bldng_followupDate);
        total_floor=findViewById(R.id.update_bldng_totalfloor);
        flat_floor=findViewById(R.id.update_bldng_flatpfloor);
        house_address=findViewById(R.id.update_bldng_houseAddress);
        flat_format=findViewById(R.id.update_bldng_flatformat);
        updateInfo_Button=findViewById(R.id.update_bldng_updatebtn);

        spinKitProgress=findViewById(R.id.spin_kit);
        Wave wave=new Wave();
        spinKitProgress.setIndeterminateDrawable(wave);

        house_name.setText(fBuildings.getB_housename());
        total_guards.setText(fBuildings.getB_guards());
        building_status.setText(fBuildings.getB_status());
        followup_date.setText(fBuildings.getB_followupdate());
        total_floor.setText(fBuildings.getB_totalfloor());
        flat_floor.setText(fBuildings.getB_flatperfloor());
        house_address.setText(fBuildings.getB_address());
        flat_format.setText(fBuildings.getB_flatfrmt());
        Glide.with(this).load(fBuildings.getB_imageUrl()).into(houseImage);

        allStringValues=new AllStringValues();
        followup_date.setOnClickListener(this);
        total_guards.setOnClickListener(this);
        updateInfo_Button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.update_bldng_followupDate){
            AllStringValues.showCalendar(this,followup_date);

        }else if (v.getId()==R.id.update_bldng_totalGuards){
            total_guards.setFocusableInTouchMode(true);
        }else if (v.getId()==R.id.update_bldng_bldngStatus){
            building_status.setFocusableInTouchMode(true);
        }else if (v.getId()==R.id.update_bldng_updatebtn){
            spinKitProgress.setVisibility(View.VISIBLE);
            updateBuildingInfo();
        }
    }

    private void updateBuildingInfo() {

        WriteBatch batch=db.batch();

        String update_totalG=total_guards.getText().toString();
        String update_followdate=followup_date.getText().toString();

        DocumentReference docRef=db.collection("f_buildings").document(fBuildings.getDocId());
        batch.update(docRef,"b_guards",update_totalG);
        batch.update(docRef,"b_followupdate",update_followdate);

        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
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
}
