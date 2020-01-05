package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    EditText emailId;
    EditText password;
    Button bRegister;
    TextView registerOf;
    ProgressBar progressBar;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        emailId = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);
        registerOf = (TextView) findViewById(R.id.etRegisterof);
        progressBar=findViewById(R.id.progressBar);

        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
           public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser =mFirebaseAuth.getCurrentUser();
                if( mFirebaseUser !=null){
                    Toast.makeText(RegisterActivity.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(RegisterActivity.this,UserAreaActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =emailId.getText().toString().trim();
                String pwd= password.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    emailId.setError("請輸入信箱");
                    emailId.requestFocus();
                }
                else if(TextUtils.isEmpty(pwd)){
                    password.setError("請輸入密碼");
                    password.requestFocus();
                }
                else if(pwd.length()<6){
                    password.setError("密碼長度要大於6");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Fields are Empty!",Toast.LENGTH_SHORT);
                }
                else if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(RegisterActivity.this,new OnCompleteListener<AuthResult>(){
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this,"Login Error,Please login again!!!",Toast.LENGTH_SHORT);
                            }
                            else{
                                progressBar.setVisibility(View.VISIBLE);
                                Intent intToHome = new Intent(RegisterActivity.this,UserAreaActivity.class);
                                startActivity(intToHome);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Error Occurred!!!",Toast.LENGTH_SHORT);
                }
            }
        });
        registerOf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intSignUp);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
