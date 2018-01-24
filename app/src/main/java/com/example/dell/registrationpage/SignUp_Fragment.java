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
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import static android.R.id.message;
import static com.example.dell.registrationpage.R.string.already_user;
import static com.example.dell.registrationpage.R.string.login;
import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by Dell on 1/18/2018.
 */

public class SignUp_Fragment extends Fragment {
    private View view;
    EditText fullName,email,id,password,cnfpassword;
    Spinner year,batch,department;
    Button verify,signup;
    String fName,eMail,iD,pwd,cnfpwd,yr,bt,dept,aUser;
    TextView alreadyUser,verifyEmail;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.signup_layout, container, false);
        initViews();
        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();
        mRef=firebaseDatabase.getReference();
        progressDialog=new ProgressDialog(getActivity());
        signup.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                progressDialog.setMessage("Signing up...");
                progressDialog.show();
                fName=fullName.getText().toString();
                eMail=email.getText().toString();
                iD=id.getText().toString();
                pwd=password.getText().toString();
                cnfpwd=cnfpassword.getText().toString();
                yr=year.getSelectedItem().toString();
                bt=batch.getSelectedItem().toString();
                dept=department.getSelectedItem().toString();
                if(TextUtils.isEmpty(fName)||TextUtils.isEmpty(iD)||TextUtils.isEmpty(eMail)||TextUtils.isEmpty(pwd)||TextUtils.isEmpty(cnfpwd)||TextUtils.isEmpty(bt)||TextUtils.isEmpty(dept)||TextUtils.isEmpty(yr)){
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(),"Empty Credentials",Toast.LENGTH_LONG).show();
                }
                else if(yr.equals("--Select Year--")||bt.equals("--Select Batch--")||dept.equals("--Select Department--")){
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(),"Empty Credentials",Toast.LENGTH_LONG).show();
                }
                else if(pwd.compareTo(cnfpwd)!=0){
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(),"Passwords do not match",Toast.LENGTH_LONG).show();
                }
                else{


                    mAuth.createUserWithEmailAndPassword(eMail,pwd).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                user = mAuth.getCurrentUser();
                                if (user != null) {
                                    sendVerificationEmail();

                                } else {
                                    Toast.makeText(getActivity(), "Signed out", Toast.LENGTH_LONG).show();
                                }
                                                            }
                            else {
                                progressDialog.dismiss();
                                    FirebaseAuthException e = (FirebaseAuthException )task.getException();
                                    Toast.makeText(getActivity(), "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        return view;
    }

    private void sendVerificationEmail() {
        final StudentInfo studentInfo=new StudentInfo(fName,eMail,iD,pwd,bt,yr,dept,0,null);
        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(),"Email has been sent to registered id please verify to login",Toast.LENGTH_LONG).show();
                            mRef.child("Student").child(user.getUid()).child("Personal details").setValue(studentInfo);
                            FragmentManager fragmentManager=getFragmentManager();
                            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                            Login_Fragment login_fragment=new Login_Fragment();
                            fragmentTransaction.replace(R.id.frameContainer,login_fragment);
                            fragmentTransaction.commit();

                        }
                        else
                        {

                            Toast.makeText(getActivity(),"Mail failed to sent",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void initViews(){

        fullName=(EditText) view.findViewById(R.id.fullName);
        id=(EditText)view.findViewById(R.id.id);
        email=(EditText)view.findViewById(R.id.userEmailId);
        password=(EditText) view.findViewById(R.id.password);
        cnfpassword=(EditText) view.findViewById(R.id.confirmPassword);
        year=(Spinner) view.findViewById(R.id.year);
        batch=(Spinner) view.findViewById(R.id.Batch);
        department=(Spinner) view.findViewById(R.id.department);
        signup=(Button) view.findViewById(R.id.signUpBtn);
        //verify=(Button) view.findViewById(R.id.verifyDetailsBtn);
        alreadyUser=(TextView) view.findViewById(R.id.already_user);
        aUser=alreadyUser.getText().toString().trim();



        SpannableString ss = new SpannableString(aUser);
        ClickableSpan clickableSpan=new ClickableSpan() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                Login_Fragment login_fragment=new Login_Fragment();
                fragmentTransaction.replace(R.id.frameContainer,login_fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };
        ss.setSpan(clickableSpan, 22, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        alreadyUser.setText(ss);
        alreadyUser.setMovementMethod(LinkMovementMethod.getInstance());
        alreadyUser.setHighlightColor(Color.TRANSPARENT);


// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(),
                R.array.batch, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        batch.setAdapter(adapter1);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.year, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        year.setAdapter(adapter2);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getActivity(),
                R.array.department, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        department.setAdapter(adapter3);

    }


}


