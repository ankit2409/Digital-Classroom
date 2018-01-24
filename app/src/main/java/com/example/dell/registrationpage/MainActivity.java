package com.example.dell.registrationpage;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private static final String fragment_tag = "myFragment";
    FirebaseDatabase mfirebase;
    DatabaseReference mRef;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Login_Fragment login_Fragment=new Login_Fragment();
        if (savedInstanceState == null) {

            FragmentManager fragmentManager=getFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameContainer,login_Fragment,fragment_tag);
            fragmentTransaction.commit();
        } else {
            login_Fragment = (Login_Fragment) getFragmentManager().findFragmentByTag(fragment_tag);
        }
    }
}