package com.example.byjusupmarvelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.byjusupmarvelapp.UsersModel.User;
import com.example.byjusupmarvelapp.databinding.ActivityAuthenticationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class AuthenticationActivity extends AppCompatActivity {
    ActivityAuthenticationBinding binding;

    private FirebaseAuth mAuth;
    FirebaseDatabase bd;
    DatabaseReference users;
    ConstraintLayout constraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityAuthenticationBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Toast.makeText(this, "User not null", Toast.LENGTH_LONG).show();
        } else Toast.makeText(this, "User null", Toast.LENGTH_LONG).show();
    }

   public void registerWindow() {
       AlertDialog.Builder dialog = new AlertDialog.Builder(this);
       dialog.setTitle("Registration");
       LayoutInflater inflater = LayoutInflater.from(this);
       View registrationDialog = inflater.inflate(R.layout.registration_dialog, null);
       dialog.setView(registrationDialog);
   }


    public void onClickSignUp(View view) {
        registerWindow();
        if (!TextUtils.isEmpty(binding.etLogin.getText().toString()) && !TextUtils.isEmpty(binding.etPassword.getText().toString())) {
            mAuth.createUserWithEmailAndPassword(binding.etLogin.getText().toString(), binding.etPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "User Successfully SigUp ", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getApplicationContext(), "User Do Not SigUp ", Toast.LENGTH_LONG).show();
                }
            });
        }else Toast.makeText(getApplicationContext(), "Please Enter Email and Password ", Toast.LENGTH_LONG).show();
    }

    public void onClickSignIn(View view) {
registerWindow();
    }
}