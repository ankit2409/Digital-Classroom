package com.example.dell.registrationpage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.IOException;

public class UploadImage extends AppCompatActivity {

        private static final int GALLERY_RESULT=2;
        private TextView selectimg;
        private FirebaseStorage mfireasestorage;
        private StorageReference mstorage;
        private ProgressDialog mprogress;
        private ImageView mimage;
        private Button upload;
        private Uri uri;
    FloatingActionButton floatingActionButton;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_upload_image);
            selectimg = (TextView) findViewById(R.id.selectimage);
            mimage=(ImageView) findViewById(R.id.image);
            upload=(Button) findViewById(R.id.upload);
            mfireasestorage=FirebaseStorage.getInstance();
            mstorage= mfireasestorage.getReference().child("Assignments");
            floatingActionButton=(FloatingActionButton)findViewById(R.id.select);

            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    floatingActionButton.setVisibility(View.INVISIBLE);
                    // TODO: Fire an intent to show an image picker
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/jpeg");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                    startActivityForResult(Intent.createChooser(intent, "Complete action using"), GALLERY_RESULT);

                }
            });
            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    uploadImage();
                }
            });
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == GALLERY_RESULT && resultCode == RESULT_OK
                    && data != null && data.getData() != null) {
                uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    mimage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    private void uploadImage() {
        if(uri!=null) {
            mprogress=new ProgressDialog(this);
            mprogress.setMessage("Uploading..");
            mprogress.show();
            StorageReference filePath = mstorage.child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests") double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                    //double progress =  (100.0 * taskSnapshot.getBytesTransferred() )/taskSnapshot.getTotalByteCount();
                    mprogress.setMessage("Upload is " + (int)progress + "% done");
                }
            }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                    mprogress.dismiss();
                    Toast.makeText(UploadImage.this,"Upload paused",Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    mprogress.dismiss();

                    Toast.makeText(UploadImage.this, "Upload fail "+e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(UploadImage.this, "Upload done", Toast.LENGTH_LONG).show();
                    mimage.setImageResource(android.R.color.transparent);
                    mprogress.dismiss();
                    floatingActionButton.setVisibility(View.VISIBLE);
                }
            });
        }
    }
    }
