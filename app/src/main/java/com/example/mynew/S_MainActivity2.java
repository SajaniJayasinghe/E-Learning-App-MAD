package com.example.mynew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class S_MainActivity2 extends AppCompatActivity {

    Button user , admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        user = findViewById(R.id.user);
        admin = findViewById(R.id.admin);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(S_MainActivity2.this, S_LoginPage.class));
            }
        });

       admin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
           startActivity(new Intent(S_MainActivity2.this, A_back_AdminPage.class));
          }
       });

    }
}