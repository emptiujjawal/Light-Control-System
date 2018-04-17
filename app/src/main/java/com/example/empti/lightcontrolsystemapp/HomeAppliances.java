package com.example.empti.lightcontrolsystemapp;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeAppliances extends AppCompatActivity {
    private Switch led1,led2;

    //firebase
    private DatabaseReference mstatesDatabase;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mstateshowDatabase;

    //progress
    private ProgressDialog mprogress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_appliances);

        //firebase
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        mstatesDatabase = FirebaseDatabase.getInstance().getReference().child("123").child("states");
        mstateshowDatabase = FirebaseDatabase.getInstance().getReference().child("123").child("states");

        led1 = (Switch)findViewById(R.id.switch1);
        led2 = (Switch)findViewById(R.id.switch2);

        mstateshowDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String ledstate1 = dataSnapshot.child("001").getValue().toString();
                String ledstate2 = dataSnapshot.child("002").getValue().toString();

                if (ledstate1 == "true" ){
                    led1.setChecked(true);
                }else{
                    led1.setChecked(false);
                }

                if (ledstate2 == "true" ){
                    led2.setChecked(true);
                }else{
                    led2.setChecked(false);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        led1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked == true){

                    //progress
                    mprogress = new ProgressDialog(HomeAppliances.this);
                    mprogress.setTitle("Led State Changes");
                    mprogress.setMessage("Please Wait Savings!!");
                    mprogress.show();

                    String statesChangeOn = "true";
                    mstatesDatabase.child("001").setValue(statesChangeOn).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                mprogress.dismiss();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"There was some error in Saving Changes",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
                else{
                    String statesChangeoff = "false";
                    mstatesDatabase.child("001").setValue(statesChangeoff).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                mprogress.dismiss();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"There was some error in Saving Changes",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
            }
        });

        led2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked == true){

                    //progress
                    mprogress = new ProgressDialog(HomeAppliances.this);
                    mprogress.setTitle("Led State Changes");
                    mprogress.setMessage("Please Wait Savings!!");
                    mprogress.show();

                    String statesChangeOn2 = "true";
                    mstatesDatabase.child("002").setValue(statesChangeOn2).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                mprogress.dismiss();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"There was some error in Saving Changes",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
                else{
                    String statesChangeoff2 = "false";
                    mstatesDatabase.child("002").setValue(statesChangeoff2).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                mprogress.dismiss();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"There was some error in Saving Changes",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
            }
        });
    }


}
