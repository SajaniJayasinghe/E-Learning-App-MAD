package com.example.mynew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.mynew.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class S_EditProfile extends AppCompatActivity {

    Button updateButton,Cancel;
    EditText edtName, edtPhoneNumber, edtEmail;
    ImageView imageView;
    FirebaseUser mUser;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_edit_profile);
        
        mAuth = FirebaseAuth.getInstance();

        edtName = findViewById(R.id.user_name);
        edtPhoneNumber = findViewById(R.id.user_phoneNo);
        edtEmail = findViewById(R.id.user_email);
        imageView = findViewById(R.id.userpro_image);
        updateButton = findViewById(R.id.update);
        Cancel = findViewById(R.id.Cancel);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");

        edtName.setText(S_GlobalVariable.currentUser.getName());
        edtPhoneNumber.setText(S_GlobalVariable.currentUser.getPhoneNumber());
        edtEmail.setText(S_GlobalVariable.currentUser.getEmail());

 //cancel button
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(S_EditProfile.this, S_UserProfile.class));
            }
        });

 //update button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String phoneNumber = edtPhoneNumber.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();

                UpdateFirebaseData(name,phoneNumber,email);
                mUser = FirebaseAuth.getInstance().getCurrentUser();
            }
        });
    }

 //update profile
      private void UpdateFirebaseData(String name, String phoneNumber, String email) {
           ProgressDialog progressDialog = new ProgressDialog(this);
           progressDialog.setMessage("Please wait...");
           progressDialog.setCancelable(false);
           progressDialog.show();

 //get a unique user
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(mAuth.getCurrentUser().getUid());
        Map<String,Object>updates = new HashMap<String,Object>();
        updates.put("name",name);
        updates.put("phoneNumber",phoneNumber);
        updates.put("email",email);

        databaseReference.updateChildren(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
           @Override
           public void onComplete(@NonNull Task<Void> task) {
              if(task.isSuccessful()){
                  progressDialog.dismiss();;
                  Toast.makeText(S_EditProfile.this,"Data Updated",Toast.LENGTH_SHORT).show();
                  sendUserToNextActivity();
                  FirebaseDatabase.getInstance().getReference("User")
                      .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                      .addListenerForSingleValueEvent(new ValueEventListener() {
                          @Override
                          public void onDataChange(DataSnapshot snapshot) {
                              S_GlobalVariable.currentUser = snapshot.getValue(User.class);
                          }
                          @Override
                          public void onCancelled(DatabaseError error) {
                          }
                      });
              }else{
                  progressDialog.dismiss();
                  Toast.makeText(S_EditProfile.this,"Data Not Updated",Toast.LENGTH_SHORT).show();
              }
           }
       });
    }
    private void sendUserToNextActivity() {
        Intent intent = new Intent(S_EditProfile.this, S_HomePage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}