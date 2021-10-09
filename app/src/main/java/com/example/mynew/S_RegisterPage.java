package com.example.mynew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class S_RegisterPage extends AppCompatActivity {

    TextView btn;
    EditText inputName, inputPhoneNumber,inputEmail,inputPassword,inputConfirmPassword;
    Button btnRegister;
    FirebaseAuth mAuth;
    ProgressDialog mLoadingBar;
    DatabaseReference user;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        btn = findViewById(R.id.login);
        inputName = findViewById(R.id.reg_user_name);
        inputPhoneNumber  = findViewById(R.id.reg_phone);
        inputEmail = findViewById(R.id.reg_email);
        inputPassword = findViewById(R.id.reg_password);
        inputConfirmPassword = findViewById(R.id.reg_confirmpassword);
        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(S_RegisterPage.this);
        db=FirebaseDatabase.getInstance();
        user = db.getReference("User");
        btnRegister = findViewById(R.id.reg_signup);

 //Already have an account ?Login textview button and redirected register page
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuth();
            }
        });

 //login text view
        btn.setOnClickListener((v) ->{
            startActivity(new Intent(S_RegisterPage.this, S_LoginPage.class));
        });
    }

    private void PerformAuth() {
        String name = inputName.getText().toString();
        String phoneNumber = inputPhoneNumber.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();

 //validation
        if(name.isEmpty() || name.length()<4)
        {
            showError(inputName,"Your Name is not valid !!");
        }
        else if(phoneNumber.isEmpty() || phoneNumber.length()>10)
        {
            showError(inputPhoneNumber,"Phone Number is not valid !!");
        }
        else if (email.isEmpty() || !email.contains("@"))
        {
            showError(inputEmail,"Email is not valid. @ sign is missing");
        }
        else if (email.isEmpty() || !email.contains(".com"))
        {
            showError(inputEmail,"Email is not valid. .com is missing");
        }
        else if (password.isEmpty() || password.length()>7)
        {
            showError(inputPassword, "Password must be 6 character");
        }
        else if (confirmPassword.isEmpty() || !confirmPassword.equals(password))
        {
            showError(inputConfirmPassword,"Password mismatch !!");
        }
        else
        {
            mLoadingBar.setTitle("Please wait while Register");
            mLoadingBar.setMessage("Registration");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

 //create authentication using user email and password
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        mLoadingBar.dismiss();
                        sendUserToNextActivity();

//save data to database
                        User newUser = new User();
                        newUser.setName(inputName.getText().toString());
                        newUser.setPhoneNumber(inputPhoneNumber.getText().toString());
                        newUser.setEmail(inputEmail.getText().toString());

                        user.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(newUser)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(S_RegisterPage.this,"Register Successful" +task.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(S_RegisterPage.this,"Registration Fail" +task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else
                    {
                        mLoadingBar.dismiss();
                        Toast.makeText(S_RegisterPage.this,"Registration Fail"+task.getException().toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void sendUserToNextActivity() {
        Intent intent = new Intent(S_RegisterPage.this, S_LoginPage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}