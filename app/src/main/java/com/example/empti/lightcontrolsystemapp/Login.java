package com.example.empti.lightcontrolsystemapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button login;
    private EditText user, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //firebase
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {

            finish();
            //start profile activity
            startActivity(new Intent(Login.this, HomeAppliances.class));
        }

        //UI Interface
        login = (Button)findViewById(R.id.logbtn);
        user = (EditText)findViewById(R.id.textuser);
        password = (EditText)findViewById(R.id.textpass);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = user.getText().toString().trim();
                String pass = password.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            Toast.makeText(Login.this,"Welcome Authentication Successful",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Login.this,HomeAppliances.class));
                            finish();
                        }
                        else{
                            Toast.makeText(Login.this,"You have no Authentication",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
