package com.rokkhi.demofieldwork.Ui;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.rokkhi.demofieldwork.Model.FPayments;
import com.rokkhi.demofieldwork.Model.Users;
import com.rokkhi.demofieldwork.R;
import com.rokkhi.demofieldwork.Utils.Normalfunc;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.firebase.ui.auth.AuthUI.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    ImageView logout,edit;
    CircleImageView propic;
    TextView name,mailid,joiningDate;
    Button bkashno;
    TextView tearning,dearning,tref,dref,tmeeting,dmeeting,tbuilding,dbuilding,abuilding;
    FirebaseFirestore firebaseFirestore;

    Context context;
    ProgressBar pro;
    Normalfunc normalfunc;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseFirestore=FirebaseFirestore.getInstance();
        context=getActivity();
        pro=view.findViewById(R.id.pro);
        pro.setVisibility(View.VISIBLE);

        logout=view.findViewById(R.id.logout);
        edit=view.findViewById(R.id.edit);
        propic=view.findViewById(R.id.propic);
        name=view.findViewById(R.id.username);
        mailid=view.findViewById(R.id.mail);
        joiningDate=view.findViewById(R.id.joinDate);
        bkashno=view.findViewById(R.id.bkash_no);
        tearning=view.findViewById(R.id.total_earning);
        dearning=view.findViewById(R.id.due_earning);
        tref=view.findViewById(R.id.total_referal);
        dref=view.findViewById(R.id.due_referal);
        tmeeting=view.findViewById(R.id.total_meeting);
        dmeeting=view.findViewById(R.id.due_meeting);
        tbuilding=view.findViewById(R.id.total_building);
        dbuilding=view.findViewById(R.id.due_building);
        abuilding=view.findViewById(R.id.active_building);

        normalfunc= new Normalfunc(context);
        showCurrentUserInfo();

    }

    private static final String TAG = "ProfileFragment";


    private void showCurrentUserInfo() {

        String userId= FirebaseAuth.getInstance().getUid();

        firebaseFirestore.collection(getString(R.string.col_users)).document(userId)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e);
                            return;
                        }

                        if(documentSnapshot.exists()){
                            Users users= documentSnapshot.toObject(Users.class);
                            name.setText(users.getName());
                            if(!users.getThumb().isEmpty() && !users.getThumb().equals("none"))
                                Glide.with(context).load(users.getThumb()).into(propic);
                            mailid.setText(users.getMail());
                            joiningDate.setText(normalfunc.getDateMMMdyyyy(users.getJoindate()));
                        }


                    }
                });

        firebaseFirestore.collection(getString(R.string.col_fPayment)).document(userId)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e);
                            return;
                        }

                        if(documentSnapshot.exists()){
                            FPayments fPayments= documentSnapshot.toObject(FPayments.class);
                            bkashno.setText(fPayments.getBkash_no());
//                            mailid.setText(users.getMail());
//                            joiningDate.setText(normalfunc.getDateMMMdyyyy(users.getJoindate()));
                        }


                    }
                });


    }
}
