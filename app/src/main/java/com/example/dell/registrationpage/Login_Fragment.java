package com.example.dell.registrationpage;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
  //  public login_fragment(){
    //}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_layout, container, false);
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

        // Setting text selector over textviews
        /*XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            show_hide_password.setTextColor(csl);
            signUp.setTextColor(csl);
        } catch (Exception e) {
        }*/
    }

        //setListner
        private void setListeners(){
            loginbutton.setOnClickListener(this);
            signUp.setOnClickListener(this);
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
                checkValidation();
                break;
          /*  case R.id.createAccount:
                fragmentManager.beginTransaction().replace(R.id.frameContainer,new SignUp_Fragment (), SignUp_Fragment  ).commit();
                break;*/

        }


    }
    protected void checkValidation(){
        String getEmail=email.getText().toString();
        String getPassword=password.getText().toString();
        Pattern p=Pattern.compile(Utils.regEx);
        Matcher m=p.matcher(getEmail);
        if(TextUtils.isEmpty(getEmail)||TextUtils.isEmpty(getPassword)){
            new Customtoast().Show_Toast(getActivity(),view,"Empty credentials");
        }
        else if(!m.find()){
            new Customtoast().Show_Toast(getActivity(),view,"Invalid Email");
        }
        else{
            Toast.makeText(getActivity(),"Login Successful",Toast.LENGTH_SHORT).show();
        }


    }
}
