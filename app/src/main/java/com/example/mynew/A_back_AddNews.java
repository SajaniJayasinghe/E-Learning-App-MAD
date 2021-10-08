package com.example.mynew;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class A_back_AddNews extends AppCompatActivity {
    EditText news;
    Button btnAdd,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        news = (EditText)findViewById(R.id.news);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnBack = (Button)findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performAuth();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void performAuth() {
        String anews = news.getText().toString();

        if (anews.isEmpty())
        {
            showError(news, "Enter the question!!!");
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
        map.put("news",news.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("News").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(A_back_AddNews.this,"Data Added Successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(A_back_AddNews.this,"Error While Insertion",Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void clearAll()
    {
        news.setText("");
    }
}