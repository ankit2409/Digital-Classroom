package com.example.dell.registrationpage;

import android.app.Fragment;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Dell on 1/18/2018.
 */

public class SignUp_Fragment extends Fragment  {
    private View view;
    EditText fullName,email,id,password,cnfpassword;
    Spinner year,batch,department;
    Button signup;
    String fName,eMail,iD,pwd,cnfpwd,yr,bt,dept;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.signup_layout, container, false);
        initViews();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                fName=fullName.getText().toString();
                eMail=email.getText().toString();
                iD=id.getText().toString();
                pwd=password.getText().toString();
                cnfpwd=cnfpassword.getText().toString();
                yr=year.getSelectedItem().toString();
                bt=batch.getSelectedItem().toString();
                dept=department.getSelectedItem().toString();

                if(TextUtils.isEmpty(fName)||TextUtils.isEmpty(iD)||TextUtils.isEmpty(eMail)||TextUtils.isEmpty(pwd)||TextUtils.isEmpty(cnfpwd)||TextUtils.isEmpty(bt)||TextUtils.isEmpty(dept)||TextUtils.isEmpty(yr)){
                    Toast.makeText(getActivity(),"Empty Credentials",Toast.LENGTH_LONG).show();
                }
                else if(pwd.compareTo(cnfpwd)!=0){
                    Toast.makeText(getActivity(),"Passwords do not match",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getActivity(),"Login Successful",Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
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





        /*// Setting text selector over textviews
        XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            login.setTextColor(csl);
            terms_conditions.setTextColor(csl);
        } catch (Exception e) {
        }
    }*/
    }





}
