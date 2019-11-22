package com.rokkhi.demofieldwork.Model;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.rokkhi.demofieldwork.R;
import com.rokkhi.demofieldwork.Ui.UpdateBldngInfoActivity;
import com.rokkhi.demofieldwork.Utils.Normalfunc;

import java.io.Serializable;
import java.util.List;

public class BuildingsListAdapter extends RecyclerView.Adapter<BuildingsListAdapter.BuildingViewholder>{

    public Context context;
    public List<FWorkerBuilding> fBuildingsList;
    FirebaseFirestore firebaseFirestore;


    public BuildingsListAdapter(List<FWorkerBuilding> fBuildingsList,Context context) {

        this.fBuildingsList = fBuildingsList;
        this.context=context;
    }

    @NonNull
    @Override
    public BuildingViewholder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        View v;

        firebaseFirestore=FirebaseFirestore.getInstance();

        v=LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_layout,parent,false);
        final BuildingViewholder bv=new BuildingViewholder(v);

        //TODO:Dialog initialize

        final Dialog dialog=new Dialog(parent.getContext());
        dialog.setContentView(R.layout.show_buildinginfo_layout);

        return bv;
    }

    @Override
    public void onBindViewHolder(@NonNull BuildingViewholder holder, int position) {

        FWorkerBuilding fBuildings=fBuildingsList.get(position);
        firebaseFirestore.collection(context.getString(R.string.col_fBuildings))
                .document(fBuildings.getBuild_id()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot= task.getResult();
                    if(documentSnapshot.exists()){
                        FBuildings fb= documentSnapshot.toObject(FBuildings.class);
                        holder.build_address.setText("Building Address: "+fb.getB_address());
                        holder.build_name.setText("Building Name: "+fb.getHousename());
                        holder.build_status.setText("Current Status: "+fb.getStatus());
                        holder.build_lastVisit.setText("Visit Date: "+ Normalfunc.convertDate(fb.getFollowupdate()));
                    }
                }
            }
        });




    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return fBuildingsList.size();
    }

    public class BuildingViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView build_name,build_address,build_status,build_lastVisit;
        public BuildingViewholder(@NonNull final View itemView) {
            super(itemView);

            build_name=itemView.findViewById(R.id.myhome_frag_bldngName);
            build_address=itemView.findViewById(R.id.myhome_frag_bldngAddress);
            build_status=itemView.findViewById(R.id.myhome_frag_bldngstatus);
            build_lastVisit=itemView.findViewById(R.id.myhome_frag_bldngvisitdate);

            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            FWorkerBuilding fBuildings=fBuildingsList.get(getAdapterPosition());
            Intent intent=new Intent(v.getContext(), UpdateBldngInfoActivity.class);
            intent.putExtra("buildingID", fBuildings.getBuild_id());
            v.getContext().startActivity(intent);
        }
    }
}
