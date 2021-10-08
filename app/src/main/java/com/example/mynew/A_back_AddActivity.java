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

public class A_back_AddActivity extends AppCompatActivity {

    EditText question,optionA,optionB,optionC,optionD,correctAns;
    Button btnAdd,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadd);

        question = (EditText)findViewById(R.id.question);
        optionA = (EditText)findViewById(R.id.optionA);
        optionB = (EditText)findViewById(R.id.optionB);
        optionC = (EditText)findViewById(R.id.optionC);
        optionD = (EditText)findViewById(R.id.optionD);
        correctAns = (EditText)findViewById(R.id.correctAns);

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
        String aquestion = question.getText().toString();
        String aoptionA = optionA.getText().toString();
        String aoptionB = optionB.getText().toString();
        String aoptionC = optionC.getText().toString();
        String aoptionD = optionD.getText().toString();
        String acorrectAns = correctAns.getText().toString();

        if (aquestion.isEmpty())
        {
            showError(question, "Enter the question!!!");
        }

        else if (aoptionA.isEmpty())
        {
            showError(optionA, "Enter Option A!!!");
        }

        else if (aoptionB.isEmpty())
        {
            showError(optionB, "Enter Option B!!!");
        }

        else if (aoptionC.isEmpty())
        {
            showError(optionC, "Enter Option C!!!");
        }

        else if (aoptionD.isEmpty())
        {
            showError(optionD, "Enter Option D!!!");
        }

        else if (acorrectAns.isEmpty())
        {
            showError(correctAns, "Enter the Correct Answer!!!");
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
        map.put("question",question.getText().toString());
        map.put("optionA",optionA.getText().toString());
        map.put("optionB",optionB.getText().toString());
        map.put("optionC",optionC.getText().toString());
        map.put("optionD",optionD.getText().toString());
        map.put("correctAns",correctAns.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("QUIZ").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(A_back_AddActivity.this,"Data Added Successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(A_back_AddActivity.this,"Error While Insertion",Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void clearAll()
    {
        question.setText("");
        optionA.setText("");
        optionB.setText("");
        optionC.setText("");
        optionD.setText("");
        correctAns.setText("");
    }
}