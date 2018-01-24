package com.example.dell.registrationpage;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PersonalDetailsActivity_Student extends AppCompatActivity {

    String setStudentName,setStudentId,setStudentBatch,setStudentYear,setStudentDept,setStudentEmail,setStudentScore;
    TextView studentName,studentId,studentBatch,studentYear,studentDept,studentEmail,studentScore;
    ImageView studentImage;
    private FirebaseDatabase mfirebase;
    private DatabaseReference mRef;
    private FirebaseAuth mAuth;
    private FirebaseUser studentDetails;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details_student);
        progressDialog=new ProgressDialog(PersonalDetailsActivity_Student.this);
        progressDialog.setMessage("Fetching details");
        progressDialog.show();
        mfirebase=FirebaseDatabase.getInstance();
        mRef=mfirebase.getReference();
        mAuth=FirebaseAuth.getInstance();
        studentName=(TextView)findViewById(R.id.get_student_name);
        studentId=(TextView)findViewById(R.id.get_student_id);
        studentBatch=(TextView)findViewById(R.id.get_student_batch);
        studentYear=(TextView)findViewById(R.id.get_student_year);
        studentDept=(TextView)findViewById(R.id.get_student_department);
        studentEmail=(TextView)findViewById(R.id.get_student_email);
        studentScore=(TextView)findViewById(R.id.get_student_score);
        studentImage=(ImageView)findViewById((R.id.stu_img));
        studentDetails=mAuth.getCurrentUser();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
    public void showData(DataSnapshot dataSnapshot){
        progressDialog.dismiss();
        StudentInfo studentInfo=new StudentInfo();
        for(DataSnapshot ds:dataSnapshot.getChildren()){
            studentInfo.setName(ds.child(studentDetails.getUid()).child("Personal details").getValue(StudentInfo.class).getName());
            studentInfo.setBatch(ds.child(studentDetails.getUid()).child("Personal details").getValue(StudentInfo.class).getBatch());
            studentInfo.setYear(ds.child(studentDetails.getUid()).child("Personal details").getValue(StudentInfo.class).getYear());
            studentInfo.setEmail(ds.child(studentDetails.getUid()).child("Personal details").getValue(StudentInfo.class).getEmail());
            studentInfo.setDepartment(ds.child(studentDetails.getUid()).child("Personal details").getValue(StudentInfo.class).getDepartment());
            studentInfo.setImage(ds.child(studentDetails.getUid()).child("Personal details").getValue(StudentInfo.class).getImage());
            studentInfo.setId(ds.child(studentDetails.getUid()).child("Personal details").getValue(StudentInfo.class).getId());
            studentInfo.setScore(ds.child(studentDetails.getUid()).child("Personal details").getValue(StudentInfo.class).getScore());
        }
        System.out.println(studentInfo.getName());
        studentName.setText(studentInfo.getName());
        studentBatch.setText(studentInfo.getBatch());
        studentYear.setText(studentInfo.getYear());
        studentEmail.setText(studentInfo.getEmail());
        studentScore.setText(studentInfo.getScore()+"");
        studentDept.setText(studentInfo.getDepartment());
        studentId.setText(studentInfo.getId());
    }
}
