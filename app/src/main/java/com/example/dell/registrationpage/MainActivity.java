package com.example.dell.registrationpage;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        SignUp_Fragment signUp_fragment=new SignUp_Fragment();
        fragmentTransaction.replace(R.id.frameContainer,signUp_fragment);
        fragmentTransaction.commit();
    }
}
