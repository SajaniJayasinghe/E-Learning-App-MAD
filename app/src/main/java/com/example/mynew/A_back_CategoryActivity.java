package com.example.mynew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class A_back_CategoryActivity extends AppCompatActivity {

    private String selectedTopicName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        final LinearLayout physics = findViewById(R.id.physicsLayout);
        final LinearLayout chemistry = findViewById(R.id.chemistryLayout);
        final LinearLayout biology = findViewById(R.id.biologyLayout);
        final LinearLayout maths = findViewById(R.id.mathsLayout);

        final Button startBtn = findViewById(R.id.start);
        final Button news1 = findViewById(R.id.news1);


        physics.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                selectedTopicName = "physics";

                physics.setBackgroundResource(R.drawable.round_back_white_stroke10);

                chemistry.setBackgroundResource(R.drawable.round_back_white10);
                biology.setBackgroundResource(R.drawable.round_back_white10);
                maths.setBackgroundResource(R.drawable.round_back_white10);

//                Intent intent = new Intent(startBtn.getContext(), QuestionsActivity.class);
//                intent.putExtra("selectedTopic", selectedTopicName);
//                startBtn.getContext().startActivity(intent);

            }
        });

        chemistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName = "chemistry";

                chemistry.setBackgroundResource(R.drawable.round_back_white_stroke10);

                physics.setBackgroundResource(R.drawable.round_back_white10);
                biology.setBackgroundResource(R.drawable.round_back_white10);
                maths.setBackgroundResource(R.drawable.round_back_white10);

//                Intent intent = new Intent(startBtn.getContext(), QuestionsActivity.class);
//                intent.putExtra("selectedTopic", selectedTopicName);
//                startBtn.getContext().startActivity(intent);
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

//                Intent intent = new Intent(startBtn.getContext(), QuestionsActivity.class);
//                intent.putExtra("selectedTopic", selectedTopicName);
//                startBtn.getContext().startActivity(intent);
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

//                Intent intent = new Intent(startBtn.getContext(), QuestionsActivity.class);
//                intent.putExtra("selectedTopic", selectedTopicName);
//                startBtn.getContext().startActivity(intent);

            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTopicName.isEmpty()){
                    Toast.makeText(A_back_CategoryActivity.this, "Please Select A Topic", Toast.LENGTH_SHORT).show();
                }
                else if (selectedTopicName == "physics"){
//                    Intent intent = new Intent(startBtn.getContext(), QuestionsActivity.class);
//                    intent.putExtra("selectedTopic", selectedTopicName);
//                    startBtn.getContext().startActivity(intent);
                    Intent intent = new Intent(A_back_CategoryActivity.this, A_back_QuestionsActivity.class);
                    startActivity(intent);
                }
                else if (selectedTopicName == "chemistry"){
//                    Intent intent = new Intent(startBtn.getContext(), QuestionsActivity.class);
//                    intent.putExtra("selectedTopic", selectedTopicName);
//                    startBtn.getContext().startActivity(intent);
                    Intent intent = new Intent(A_back_CategoryActivity.this, A_back_ChemActivity.class);
                    startActivity(intent);
                }
                else if (selectedTopicName == "biology"){
//                    Intent intent = new Intent(startBtn.getContext(), QuestionsActivity.class);
//                    intent.putExtra("selectedTopic", selectedTopicName);
//                    startBtn.getContext().startActivity(intent);
                    Intent intent = new Intent(A_back_CategoryActivity.this, A_back_BiologyActivity.class);
                    startActivity(intent);
                }
                else if (selectedTopicName == "maths"){
//                    Intent intent = new Intent(startBtn.getContext(), QuestionsActivity.class);
//                    intent.putExtra("selectedTopic", selectedTopicName);
//                    startBtn.getContext().startActivity(intent);
                    Intent intent = new Intent(A_back_CategoryActivity.this, A_back_MathsActivity.class);
                    startActivity(intent);
                }
            }
        });

        news1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(A_back_CategoryActivity.this, A_back_NewsActivity.class);
                startActivity(intent);
            }
        });
    }
}