package com.example.dell.registrationpage;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.dell.registrationpage.Utils.Login_Fragment;
import static com.example.dell.registrationpage.Utils.SignUp_Fragment;

/**
 * Created by Dell on 1/17/2018.
 */

public class Login_Fragment extends Fragment implements View.OnClickListener {
    private static View view;
    private static Button loginbutton;
    private static EditText email,password;
    private static LinearLayout loginLayout;
    private static FragmentManager fragmentManager;
    private static CheckBox show_hide_password;
    private static TextView signUp;
    String sUp;
    private FirebaseDatabase mFirebase;
    private DatabaseReference mRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_layout, container, false);
        mFirebase=FirebaseDatabase.getInstance();
        mRef=mFirebase.getReference();
        mAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(getActivity());
        initViews();
        setListeners();
        return view;
    }
    private void initViews() {
        fragmentManager = getActivity().getFragmentManager();
        email = (EditText) view.findViewById(R.id.login_emailid);
        password = (EditText) view.findViewById(R.id.login_password);
        loginbutton = (Button) view.findViewById(R.id.loginBtn);
        signUp = (TextView) view.findViewById(R.id.createAccount);
        show_hide_password = (CheckBox) view.findViewById(R.id.show_hide_password);
        loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);
        signUp=(TextView) view.findViewById(R.id.createAccount);
        sUp=signUp.getText().toString().trim();
        SpannableString ss = new SpannableString(sUp);
        ClickableSpan clickableSpan=new ClickableSpan() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                SignUp_Fragment signUp_fragment=new SignUp_Fragment();
                fragmentTransaction.replace(R.id.frameContainer,signUp_fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };
        ss.setSpan(clickableSpan, 18,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        signUp.setText(ss);
        signUp.setMovementMethod(LinkMovementMethod.getInstance());
        signUp.setHighlightColor(Color.TRANSPARENT);

    }

        //setListner
        private void setListeners(){
            loginbutton.setOnClickListener(this);
            //signUp.setOnClickListener(this);
            show_hide_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if(isChecked){
                        show_hide_password.setText(R.string.hide_pwd);// change
                        password.setInputType(InputType.TYPE_CLASS_TEXT);
                        password.setTransformationMethod(HideReturnsTransformationMethod
                                .getInstance());// show password
                    }else{
                        show_hide_password.setText(R.string.show_pwd);// change
                        password.setInputType(InputType.TYPE_CLASS_TEXT
                                | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        password.setTransformationMethod(PasswordTransformationMethod
                                .getInstance());// hide password
                    }
                }
            });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginBtn:
                progressDialog.setMessage("Loging In");
                progressDialog.show();
                checkValidation();
                break;
        }
    }
    protected void checkValidation(){
        String getEmail=email.getText().toString();
        String getPassword=password.getText().toString();
        //Pattern p=Pattern.compile(Utils.regEx);
        //Matcher m=p.matcher(getEmail);
        if(TextUtils.isEmpty(getEmail)||TextUtils.isEmpty(getPassword)){
            new Customtoast().Show_Toast(getActivity(),view,"Empty credentials");
        }
        /*else if(!m.find()){
            new Customtoast().Show_Toast(getActivity(),view,"Invalid Email");
        }*/
        else{

            mAuth.signInWithEmailAndPassword(getEmail,getPassword).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        //startActivity(new Intent(getActivity(),StudentDash.class));
                        checkIfEmailVerified();
                    }else{
                        progressDialog.dismiss();
                        FirebaseAuthException e = (FirebaseAuthException) task.getException();
                        Toast.makeText(getActivity(), "Failed Registration: " + e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }



    }

    private void checkIfEmailVerified() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified())
        {
            // user is verified, so you can finish this activity or send user to activity which you want.
            startActivity(new Intent(getActivity(),TeacherDashboard.class));
            Toast.makeText(getActivity(), "Successfully logged in", Toast.LENGTH_SHORT).show();
        }
        else
        {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            Toast.makeText(getActivity(), "Email not verified", Toast.LENGTH_SHORT).show();

        }
    }


}
