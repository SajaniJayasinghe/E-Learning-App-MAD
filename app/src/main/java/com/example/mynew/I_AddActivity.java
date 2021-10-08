package com.example.mynew;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class I_AddActivity extends AppCompatActivity {

    EditText title,name,number,email,des,adurl;
    Button ibtnAdd,ibtnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iadd);

        title = (EditText)findViewById(R.id.txtTitle);
        name = (EditText)findViewById(R.id.txtName);
        number = (EditText)findViewById(R.id.txtNumber);
        email = (EditText)findViewById(R.id.txtEmail);
        des = (EditText)findViewById(R.id.txtDescription);
        adurl = (EditText)findViewById(R.id.txtImageUrl);

        ibtnAdd = (Button)findViewById(R.id.ibtnAdd);
        ibtnBack = (Button)findViewById(R.id.ibtnBack);

        ibtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performAuth();

            }
        });

        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }

    //field validation
    private void performAuth() {
        String advtitle = title.getText().toString();
        String advname = name.getText().toString();
        String advnumber = number.getText().toString();
        String advemail = email.getText().toString();
        String advdes = des.getText().toString();
        String advurl = adurl.getText().toString();

        if (advtitle.isEmpty() || advtitle.length() < 4)
        {
            showError(title, "Your Title is not valid !!");
        }
        else if (advname.isEmpty() || advname.length() < 4)
        {
            showError(name, "Your Name is not valid !!");
        }
        else if (advnumber.isEmpty() || advnumber.length()>10)
        {
            showError(number,"Phone Number is not valid !!");
        }
        else if (advemail.isEmpty() || !advemail.contains("@"))
        {
            showError(email,"Email is not valid");
        }
        else if (advemail.isEmpty() || !advemail.contains(".com"))
        {
            showError(email,"Email is not valid");
        }
        else if (advdes.isEmpty() )
        {
            showError(des, "Your Description is not valid !!");
        }
        else if (advurl.isEmpty() )
        {
            showError(adurl, "Your Image URL is not valid !!");
        }
        else
        {
            insertData();
            clearAll();
        }

    }
    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }


    private void insertData()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("aTitle",title.getText().toString());
        map.put("aName",name.getText().toString());
        map.put("aNumber",number.getText().toString());
        map.put("aEmail",email.getText().toString());
        map.put("aDes",des.getText().toString());
        map.put("aurl",adurl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("advertisements").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(I_AddActivity.this, "Data Inserted Successfully!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {
                        Toast.makeText(I_AddActivity.this, "Error while Insertion", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void clearAll()
    {
        title.setText("");
        name.setText("");
        number.setText("");
        email.setText("");
        des.setText("");
        adurl.setText("");
    }

}
