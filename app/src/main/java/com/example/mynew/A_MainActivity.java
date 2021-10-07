package com.example.mynew;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;


public class A_MainActivity extends AppCompatActivity {
    private TextView title;
    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        title = findViewById(R.id.textView);
        start = findViewById(R.id.button);


        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(A_MainActivity.this, A_CategoryActivity.class);
                startActivity(intent);

            }
        });

    }
}