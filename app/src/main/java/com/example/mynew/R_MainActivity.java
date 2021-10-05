package com.example.mynew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class R_MainActivity extends AppCompatActivity {

    RelativeLayout maths;
    RelativeLayout bio;
    RelativeLayout chem;
    RelativeLayout phy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        maths = findViewById(R.id.RL1);
        bio = findViewById(R.id.RDL2);
        chem = findViewById(R.id.RL3);
        phy = findViewById(R.id.RL4);

        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(R_MainActivity.this, R_mathssub.class));
            }
        });

        bio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(R_MainActivity.this, R_biosub.class));
            }
        });

        chem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(R_MainActivity.this, R_chemsub.class));
            }
        });

        phy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(R_MainActivity.this, R_physub.class));
            }
        });
    }
}