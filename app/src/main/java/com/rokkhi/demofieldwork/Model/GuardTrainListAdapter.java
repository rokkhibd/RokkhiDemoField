package com.rokkhi.demofieldwork.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rokkhi.demofieldwork.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GuardTrainListAdapter extends RecyclerView.Adapter<GuardTrainListAdapter.GuardTrainViewHolder> {

    Context context;
    List<FGuardTrack> fGuardTrackList;
    View v;
    FirebaseFirestore firebaseFirestore;


    public GuardTrainListAdapter(Context context, List<FGuardTrack> fGuardTrackList) {
        this.context = context;
        this.fGuardTrackList = fGuardTrackList;
    }

    @NonNull
    @Override
    public GuardTrainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        firebaseFirestore=FirebaseFirestore.getInstance();
        v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_guardtrack_list,parent);
        GuardTrainViewHolder bv=new GuardTrainViewHolder(v);

        return bv;
    }

    @Override
    public void onBindViewHolder(@NonNull GuardTrainViewHolder holder, int position) {

        FGuardTrack fGuardTrack=fGuardTrackList.get(position);
        Date date1=fGuardTrack.getTimeEnd();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);

        String myFormat = "EEE, MMM d , hh:mm a "; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());


        holder.endButton.setText(sdf.format(cal.getTime()));


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class GuardTrainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView guardImage;
        TextView gName;
        Button startButton,endButton;

        public GuardTrainViewHolder(@NonNull View itemView) {
            super(itemView);

            guardImage=itemView.findViewById(R.id.propic);
            gName=itemView.findViewById(R.id.gname);
            startButton=itemView.findViewById(R.id.gtrain_start);
            endButton=itemView.findViewById(R.id.gtrain_end);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.gtrain_end){

            }
        }
    }
}
