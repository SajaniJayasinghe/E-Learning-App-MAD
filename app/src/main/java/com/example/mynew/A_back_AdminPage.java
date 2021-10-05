package com.example.mynew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mynew.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class A_back_AdminPage extends AppCompatActivity {

    EditText adminEmail ,adminPassword;
    Button adminButton;
    FirebaseAuth mAuth;
    ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadmin_page);

        adminEmail = findViewById(R.id.admin_login_email);
        adminPassword = findViewById(R.id.admin_login_password);
        adminButton = findViewById(R.id.admin_login_button);


        adminButton.setOnClickListener((v) ->{
            checkCredentials();} );
        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(A_back_AdminPage.this);

    }

    private void checkCredentials() {

        String email = adminEmail.getText().toString();
        String password = adminPassword.getText().toString();

        if(email.isEmpty() || !email.contains("@"))
        {
            showError(adminEmail,"Email is not valid");
        }
        else if(password.isEmpty() || password.length()>7)
        {
            showError(adminPassword,"Password must be 6 character");
        }
        else
        {
            mLoadingBar.setTitle("Login");
            mLoadingBar.setMessage("Please wait a moment");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        mLoadingBar.dismiss();
                        //Fetch the user details
                        FirebaseDatabase.getInstance().getReference("Admin")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        S_GlobalVariable.currentUser = dataSnapshot.getValue(User.class);
                                        Intent userProfile = new Intent(A_back_AdminPage.this, R_back_AdminHomePage.class);
                                        startActivity(userProfile);
                                        Toast.makeText(A_back_AdminPage.this,"Successfully Login",Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError error) {
                                    }
                                });
                        // Intent intent = new Intent(LoginPage.this,UserProfile.class);
                        // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        // startActivity(intent);
                    }
                    else {
                        Toast.makeText(A_back_AdminPage.this,"There is no Account",Toast.LENGTH_SHORT).show();
                        mLoadingBar.dismiss();
                    }
                }
            });
        }
    }

    private void showError(EditText input , String s) {
        input.setError(s);
        input.requestFocus();
    }
}