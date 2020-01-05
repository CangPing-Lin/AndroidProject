package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText emailId;
    EditText password;
    Button bLogin;
    TextView registerLink;
   FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth=FirebaseAuth.getInstance();

        emailId = (android.widget.EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        registerLink = (TextView) findViewById(R.id.tvRegisterHere);


        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }

        });
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String email =emailId.getText().toString().trim();
                String pwd= password.getText().toString().trim();
                if(email.isEmpty()){
                    emailId.setError("請輸入信箱");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty()){
                    password.setError("請輸入密碼");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Fields are Empty!",Toast.LENGTH_SHORT);
                }
                else if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Signup Unsuccessful,Please try again!!!",Toast.LENGTH_SHORT);
                            }
                            else{
                                startActivity(new Intent(LoginActivity.this,UserAreaActivity.class));
                            }

                        }
                    });
                }
                else{
                    Toast.makeText(LoginActivity.this,"Error Occurred!!!",Toast.LENGTH_SHORT);
                }

            }

        });
    }
}
