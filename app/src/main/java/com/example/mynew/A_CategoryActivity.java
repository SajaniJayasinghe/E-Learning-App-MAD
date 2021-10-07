package com.example.mynew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


public class A_CategoryActivity extends AppCompatActivity {

    private String selectedTopicName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category2);

        final LinearLayout physics = findViewById(R.id.physicsLayout);
        final LinearLayout chemistry = findViewById(R.id.chemistryLayout);
        final LinearLayout biology = findViewById(R.id.biologyLayout);
        final LinearLayout maths = findViewById(R.id.mathsLayout);

        final Button startBtn = findViewById(R.id.startQuizBtn);

        physics.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                selectedTopicName = "physics";

                physics.setBackgroundResource(R.drawable.round_back_white_stroke10);

                chemistry.setBackgroundResource(R.drawable.round_back_white10);
                biology.setBackgroundResource(R.drawable.round_back_white10);
                maths.setBackgroundResource(R.drawable.round_back_white10);


//                Intent intent = new Intent(CategoryActivity.this, Questions.class);
//                intent.putExtra("selectedTopic", selectedTopicName);
//                startActivity(intent);
            }

        });

        chemistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopicName = "chemistry";

                chemistry.setBackgroundResource(R.drawable.round_back_white_stroke10);

                physics.setBackgroundResource(R.drawable.round_back_white10);
                biology.setBackgroundResource(R.drawable.round_back_white10);
                maths.setBackgroundResource(R.drawable.round_back_white10);

//                Intent intent = new Intent(CategoryActivity.this, ChemiActivity.class);
//                intent.putExtra("selectedTopic", selectedTopicName);
//                startActivity(intent);
            }
        });

        biology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName = "biology";

                biology.setBackgroundResource(R.drawable.round_back_white_stroke10);

                chemistry.setBackgroundResource(R.drawable.round_back_white10);
                physics.setBackgroundResource(R.drawable.round_back_white10);
                maths.setBackgroundResource(R.drawable.round_back_white10);

//                Intent intent = new Intent(CategoryActivity.this, BioActivity.class);
//                intent.putExtra("selectedTopic", selectedTopicName);
//                startActivity(intent);
            }
        });

        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName = "maths";

                maths.setBackgroundResource(R.drawable.round_back_white_stroke10);

                chemistry.setBackgroundResource(R.drawable.round_back_white10);
                biology.setBackgroundResource(R.drawable.round_back_white10);
                physics.setBackgroundResource(R.drawable.round_back_white10);

//                Intent intent = new Intent(CategoryActivity.this, MathsActivity.class);
//                intent.putExtra("selectedTopic", selectedTopicName);
//                startActivity(intent);
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTopicName.isEmpty()){
                    Toast.makeText(A_CategoryActivity.this, "Please Select A Topic", Toast.LENGTH_SHORT).show();
                }
                else if (selectedTopicName == "physics"){

                    Intent intent = new Intent(A_CategoryActivity.this, A_Questions.class);
                    startActivity(intent);
                }
                else if (selectedTopicName == "chemistry"){

                    Intent intent = new Intent(A_CategoryActivity.this, A_ChemiActivity.class);
                    startActivity(intent);
                }
                else if (selectedTopicName == "biology"){

                    Intent intent = new Intent(A_CategoryActivity.this, A_BioActivity.class);
                    startActivity(intent);
                }
                else if (selectedTopicName == "maths"){

                    Intent intent = new Intent(A_CategoryActivity.this, A_MathsActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}