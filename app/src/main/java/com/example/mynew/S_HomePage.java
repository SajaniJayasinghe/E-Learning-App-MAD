package com.example.mynew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class S_HomePage extends AppCompatActivity {

    RelativeLayout user ,book ,news,advertisment,multiplicationTable,subjectDetails,miniQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        user = findViewById(R.id.L1);
        news = findViewById(R.id.L2);
        subjectDetails = findViewById(R.id.L3);
        book = findViewById(R.id.L4);
        miniQuiz = findViewById(R.id.L5);
        advertisment = findViewById(R.id.L7);
        multiplicationTable = findViewById(R.id.L8);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(S_HomePage.this, S_UserProfile.class));
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(S_HomePage.this, S_BookStore.class));
            }
        });

        miniQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(S_HomePage.this, A_MainActivity.class));
            }
        });

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(S_HomePage.this, A_NewsActivity.class));
            }
        });

        advertisment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(S_HomePage.this, I_MainActivity.class));
            }
        });

        multiplicationTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(S_HomePage.this, I_MathTable.class));
            }
        });

        subjectDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(S_HomePage.this, R_MainActivity.class));
            }
        });
    }
}