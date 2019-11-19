package com.rokkhi.demofieldwork.Utils;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.rokkhi.demofieldwork.Ui.AddBuildingActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Normalfunc {

   // FirebaseFunctions firebaseFunctions;
    FirebaseFirestore firebaseFirestore;
    Context context;
    private static final String TAG = "Normalfunc";

    public Normalfunc(){
        firebaseFirestore= FirebaseFirestore.getInstance();

    }

    public Normalfunc(Context context){
        //firebaseFunctions= FirebaseFunctions.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();
        this.context= context;

    }

    public List<String> splitstring(String ss){

        String[] array=ss.trim().split(" +");

        List<String> xx=new ArrayList<>();

        for(int i=0;i<array.length;i++){
            if(i>0)xx.addAll(splitchar(array[i].toLowerCase()));
        }

        xx.addAll(splitchar(ss.toLowerCase()));

        return xx;
    }

    public List<String> splitchar(String ss){

        List<String> xx=new ArrayList<>();
        String yy="";

        if(ss.length()>0 && ss.charAt(0)== '+'){
            xx.add(ss);
            ss=ss.substring(3);
        }

        for(int j=0;j<ss.length();j++){
            yy=yy + ss.charAt(j);
            xx.add(yy.toLowerCase());
        }

        return xx;
    }

    public void checklengthEmptyOrNot(EditText s1, EditText s2, EditText s3, EditText s4){

        if (s1.length()==0){
            s1.setError("Empty N.I.D field");
        }else if (s2.length()==0){
            s2.setError("Empty Phone Number");
        }
        else if (s3.length()==0){
            s3.setError("Empty Mail Id");
        }
        else if (s4.length()==0){
            s4.setError("Empty Referral Id");
        }


    }

    public void checkStringlengthEmpty(EditText s1, EditText s2, EditText s3, EditText s4,EditText s5,EditText s6){

        if (s1.length()==0){
            s1.setError("");
        }else if (s2.length()==0){
            s2.setError("");
        }else if (s3.length()==0){
            s3.setError("");
        }else if (s4.length()==0){
            s4.setError("");
        }else if (s5.length()==0){
            s5.setError("");
        }else if (s6.length()==0){
            s6.setError("");
        }
    }

    public void getTheCurrentDate(){
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        String currentDate=sdf.format(new Date());
        Toast.makeText(context, currentDate, Toast.LENGTH_SHORT).show();
    }

    private void getcalendar(){

                        /*Calendar calendar=Calendar.getInstance();

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(AddBuildingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                b_follwing.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();*/

    }


}
