package com.rokkhi.demofieldwork.Ui;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.rokkhi.demofieldwork.MainActivity;
import com.rokkhi.demofieldwork.Model.BuildingsListAdapter;
import com.rokkhi.demofieldwork.Model.FBuildings;
import com.rokkhi.demofieldwork.Model.FWorkers;
import com.rokkhi.demofieldwork.Model.LogSession;
import com.rokkhi.demofieldwork.R;
import com.rokkhi.demofieldwork.Utils.Normalfunc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyhomeFragment extends Fragment {

    Context context;
    FloatingActionButton flotbtn;
    RecyclerView recyclerView;
    List<FBuildings> fBuildingsList;
    FirebaseFirestore db;
    BuildingsListAdapter buildingsListAdapter;
    ProgressBar progressBar;

    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    String userID;
    String utoken="";
    CircleImageView profileImage;
    ImageView logout;
    Normalfunc normalfunc;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPref;

    boolean signoutstate=false;


    TextView f_name;
    ProgressBar profile_progressBar,spinKitProgress;
    FirebaseAuth.AuthStateListener mAuthListener;

    private static final String TAG = "xxx";
    RelativeLayout mrootview;
    private static final int RC_SIGN_IN = 12773;
    AuthUI.IdpConfig phoneConfigWithDefaultNumber;



    public MyhomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_myhome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        firebaseUser=mAuth.getCurrentUser();
        context= getActivity();
        normalfunc=new Normalfunc(context);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = sharedPref.edit();

        f_name=view.findViewById(R.id.myHome_frag_fwname);
        profile_progressBar=view.findViewById(R.id.profile_progress);
        spinKitProgress=view.findViewById(R.id.spin_kit);
        profileImage=view.findViewById(R.id.fw_myhomefrag_image);
        logout=view.findViewById(R.id.logout_image);

        Wave wave=new Wave();
        spinKitProgress.setIndeterminateDrawable(wave);

        profile_progressBar.setVisibility(View.VISIBLE);

        checkUserExistence();
        progressBar=view.findViewById(R.id.progressbar);
        recyclerView=view.findViewById(R.id.myhome_frag_recyclerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        fBuildingsList=new ArrayList<>();
        buildingsListAdapter=new BuildingsListAdapter(fBuildingsList);
        recyclerView.setAdapter(buildingsListAdapter);

        spinKitProgress.setVisibility(View.VISIBLE);
        gettingAllHouseData();
//      shoWorkerDetails();

        flotbtn = view.findViewById(R.id.floating_btn);

        flotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddBuildingActivity.class);
                startActivity(intent);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mAuth.signOut();
                new AlertDialog.Builder(getContext())
                        .setTitle("Logout")
                        .setIcon(R.drawable.logout_black)
                        .setMessage("Are you sure you want to logout?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                normalfunc.removeTokenId();
                                mAuth.signOut();

                            }

                        }).create().show();
            }
        });
    }

    private void checkUserExistence() {

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                firebaseUser=firebaseAuth.getCurrentUser();

                if(firebaseUser==null){
                    gotoLogIN();
                }
                else{
                    userID=firebaseUser.getUid();
                    final String user_id=firebaseAuth.getCurrentUser().getUid();

                    db.collection(getString(R.string.col_fWorkers)).document(user_id).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                            profile_progressBar.setVisibility(View.GONE);

                            if (documentSnapshot!=null && documentSnapshot.exists()){
//                                String name=documentSnapshot.getString("fw_name");
//                                String imageurl=documentSnapshot.getString("fw_imageUrl");
//                                f_name.setText(name);
//                                Glide.with(getContext()).load(imageurl).into(profileImage);

                                FWorkers fworkers=documentSnapshot.toObject(FWorkers.class);


                                final List< String > usertoken = fworkers.getAtoken();

                                FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(getActivity(), new OnSuccessListener<InstanceIdResult>() {
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



    private void gotoFworkerActivty() {

        Intent intent=new Intent(getContext(),FworkerProfileActivity.class);
        startActivity(intent);

    }

    public void gettingAllHouseData(){
        db.collection("fBuildings").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        spinKitProgress.setVisibility(View.GONE);

                        if (!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d:list){
                                FBuildings fb=d.toObject(FBuildings.class);
                                //fb.setDocId(d.getId());
                                fBuildingsList.add(fb);
                            }
                            buildingsListAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    public void gotoLogIN(){
        List<String> whitelistedCountries = new ArrayList<String>();
        whitelistedCountries.add("bd");
        phoneConfigWithDefaultNumber = new AuthUI.IdpConfig.PhoneBuilder()
                .setDefaultCountryIso("bd")
                .setWhitelistedCountries(whitelistedCountries)
                .build();

        signInPhone(mrootview);

    }

    public void signInPhone(View view) {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(phoneConfigWithDefaultNumber))
                        .setTheme(R.style.AppTheme)
                        .build(),
                RC_SIGN_IN);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            handleSignInResponse(resultCode, data);
        }
    }

    private void handleSignInResponse(int resultCode, Intent data) {
        IdpResponse response = IdpResponse.fromResultIntent(data);

        if (resultCode == RESULT_OK) {
            Log.d(TAG, "handleSignInResponse: checkhere ");
            Log.d(TAG, "handleSignInResponse: jjj " + FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());

        } else {
            if (response == null) {
                showSnackbar(R.string.sign_in_cancelled);
                return;
            }
            if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                showSnackbar(R.string.no_internet_connection);
                return;
            }
            if (response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                showSnackbar(R.string.unknown_error);
                return;
            }
        }

    }

    private void showSnackbar(int errorMessageRes) {
        Snackbar.make(mrootview, errorMessageRes, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void onResume() {
        super.onResume();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        mAuth.removeAuthStateListener(mAuthListener);
    }


    public void shoWorkerDetails(){
        firebaseUser=mAuth.getCurrentUser();
        userID=firebaseUser.getUid();
        db.collection("f_workers").document(userID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                profile_progressBar.setVisibility(View.GONE);

                if (task.isSuccessful()){
                    DocumentSnapshot documentSnapshot=task.getResult();

                    if (documentSnapshot!=null && documentSnapshot.exists()){
                        String name=documentSnapshot.getString("fw_name");
                        String imageurl=documentSnapshot.getString("fw_imageUrl");
                        f_name.setText(name);
                        Glide.with(getContext()).load(imageurl).into(profileImage);
                    }
                }
            }
        });
    }


}

