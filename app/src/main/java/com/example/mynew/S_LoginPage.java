package com.example.mynew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynew.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class S_LoginPage extends AppCompatActivity {

    TextView btn,forgotTextLink;
    EditText inputEmail ,inputPassword;
    Button btnLogin;
    FirebaseAuth mAuth;
    ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        btn = findViewById(R.id.login_new);
        inputEmail = findViewById(R.id.login_email);
        inputPassword = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.login_button);
        forgotTextLink = findViewById(R.id.login_forgotpassword);

        //forgot Password
        forgotTextLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter Your Email to Received Reset Link.");
                passwordResetDialog.setView(resetMail);

                //password reset
                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //extract the email and send reset link
                        String mail = resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(S_LoginPage.this,"Reset Link Sent To Your Email !",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(S_LoginPage.this,"Error. Reset Link is Not sent!" +e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close the dialog
                    }
                });
                passwordResetDialog.create().show();
            }
        });

        btnLogin.setOnClickListener((v) ->{
            checkCredentials();} );
        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(S_LoginPage.this);

        //New user redirected register page
        btn.setOnClickListener((v)-> {
            startActivity(new Intent(S_LoginPage.this, S_RegisterPage.class));
        });
    }

    //check the correct email and password
    private void checkCredentials() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if(email.isEmpty() || !email.contains("@"))         //@ sign is compulsory
        {
            showError(inputEmail,"Email is not valid");
        }
        else if (email.isEmpty() || !email.contains(".com"))  //.com is compulsory
        {
            showError(inputEmail,"Email is not valid");
        }
        else if(password.isEmpty() || password.length()>7)  //password >=7
        {
            showError(inputPassword,"Password must be 6 character");
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
                        FirebaseDatabase.getInstance().getReference("User") //Database name
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .addListenerForSingleValueEvent(new ValueEventListener() {

                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) { //A DataSnapshot instance contains data from a Firebase Database location. Any time you read Database data, you receive the data as a DataSnapshot.
                                        S_GlobalVariable.currentUser = dataSnapshot.getValue(User.class);
                                        Intent userProfile = new Intent(S_LoginPage.this, S_HomePage.class);
                                        startActivity(userProfile);

                                        Toast.makeText(S_LoginPage.this,"Successfully Login",Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(S_LoginPage.this,"There is no Account",Toast.LENGTH_SHORT).show();
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