package com.example.dell.registrationpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class CreateAssignment extends AppCompatActivity {

    Button writetxt,uploadimg,uploadpdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_assignment);
        writetxt=(Button)findViewById(R.id.writeQuestion);
        uploadimg=(Button) findViewById(R.id.uploadimage);
        uploadpdf=(Button)findViewById(R.id.uploadpdf);
    }
    public void OnClick(View view){
        switch(view.getId()){
            case R.id.writeQuestion:
                //startActivity(new Intent(CreateAssignment.this,WriteQuection.class));
                Toast.makeText(CreateAssignment.this,"not ready",Toast.LENGTH_LONG).show();
                break;
            case R.id.uploadimage:
                startActivity(new Intent(CreateAssignment.this,UploadImage.class));
                break;
            case R.id.uploadpdf:
                startActivity(new Intent(CreateAssignment.this,UploadFile.class));
                Toast.makeText(CreateAssignment.this,"not ready",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
