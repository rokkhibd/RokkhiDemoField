package com.rokkhi.demofieldwork.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rokkhi.demofieldwork.Model.AllStringValues;
import com.rokkhi.demofieldwork.Model.FBuildings;
import com.rokkhi.demofieldwork.R;

public class UpdateBldngInfoActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView houseImage;
    EditText house_name,total_guards,building_status,followup_date;
    FBuildings fBuildings;
    AllStringValues allStringValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bldng_info);

        fBuildings=(FBuildings) getIntent().getSerializableExtra("fbuildings");

        house_name=findViewById(R.id.update_bldng_houseName);
        houseImage=findViewById(R.id.update_bldng_image);
        total_guards=findViewById(R.id.update_bldng_totalGuards);
        building_status=findViewById(R.id.update_bldng_bldngStatus);
        followup_date=findViewById(R.id.update_bldng_followupDate);

        house_name.setText(fBuildings.getB_housename());
        total_guards.setText(fBuildings.getB_guards());
        building_status.setText(fBuildings.getB_status());
        followup_date.setText(fBuildings.getB_followupdate());
        Glide.with(this).load(fBuildings.getB_imageUrl()).into(houseImage);

        allStringValues=new AllStringValues();
        followup_date.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.update_bldng_followupDate){
            AllStringValues.showCalendar(this,followup_date);
        }
    }
}
