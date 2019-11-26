package com.rokkhi.demofieldwork.Ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.rokkhi.demofieldwork.Model.FGuardTrack;
import com.rokkhi.demofieldwork.Model.GuardTrainListAdapter;
import com.rokkhi.demofieldwork.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mumayank.com.airlocationlibrary.AirLocation;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class GuardTrackListActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton fab;
    IntentIntegrator qrScan;
    TextView txt;
    FusedLocationProviderClient client;
    String late, lang;
    private AirLocation airLocation;
    Double lat,lng;

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    FirebaseUser firebaseUser;

    Date date;
    DocumentReference doc_ref;
    FGuardTrack fGuardTrack;
    String in,out;

    RecyclerView recyclerView;
    GuardTrainListAdapter guardTrainListAdapter;
    List<FGuardTrack> fGuardTrackList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard_track_list);
        client = LocationServices.getFusedLocationProviderClient(this);


        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        firebaseUser=mAuth.getCurrentUser();
        doc_ref=db.collection(getString(R.string.col_GuardTrainerTrack)).document();

        date = Calendar.getInstance().getTime();
        fGuardTrackList=new ArrayList<>();

        fab=findViewById(R.id.guard_floatingbtn);
        txt=findViewById(R.id.text);
        recyclerView=findViewById(R.id.guard_track_recycler);

        fGuardTrack=new FGuardTrack();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        fab.setOnClickListener(this);
        qrScan=new IntentIntegrator(this);
        qrScan.setBeepEnabled(false);

        airLocation = new AirLocation(this, true, true, new AirLocation.Callbacks() {
            @Override
            public void onSuccess(Location location) {
                lat = location.getLatitude();
                lng = location.getLongitude();
            }

            @Override
            public void onFailed(AirLocation.LocationFailedEnum locationFailedEnum) {
                // do something
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.guard_floatingbtn){
            getTheLatLang();
            qrScan.initiateScan();

        }
    }

    private void getTheLatLang() {

        if (ActivityCompat.checkSelfPermission(GuardTrackListActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {

                    late = String.valueOf(location.getLatitude());
                    lang = String.valueOf(location.getLongitude());
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        airLocation.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data); {

            if (result != null) {

                if (result.getContents()==null){
                    Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
                }else {
                    txt.setText(result.getContents().toString());
                    //saveScandataToDB();
                }
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        airLocation.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void saveScandataToDB(){

        String userId=firebaseUser.getUid();
        String doc_id=doc_ref.getId();


         fGuardTrack=new FGuardTrack(userId,date,date,"",doc_id,"",late,lang,"");


        db.collection(getString(R.string.col_GuardTrainerTrack)).document(doc_id).set(fGuardTrack).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){

                    Toast.makeText(GuardTrackListActivity.this, "Data Saved Done...", Toast.LENGTH_SHORT).show();
                    showAllScanData();
                }
            }
        });

    }

   public void showAllScanData() {
       db.collection(getString(R.string.col_GuardTrainerTrack)).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
           @Override
           public void onComplete(@NonNull Task<QuerySnapshot> task) {
               if (task.isSuccessful()){
                   fGuardTrackList.clear();

                   for (DocumentSnapshot doc:task.getResult()){
                       FGuardTrack fGuardTrack=doc.toObject(FGuardTrack.class);
                       fGuardTrackList.add(fGuardTrack);
                   }
               }
           }
       });


   }
}
