package com.example.mynew;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class A_ChemiActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView question,qCount,timer;
    private Button optionA, optionB, optionC, optionD;
    // private List<QuestionModel> questionList;
    ArrayList<A_AQuestionModel> questionList = new ArrayList<>();
    DatabaseReference databaseReference;
    private int queNum ;
    private CountDownTimer countDown;
    private int score;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio);

        question = findViewById(R.id.question);
        qCount = findViewById(R.id.quest_num);
        timer = findViewById(R.id.countdown);

        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);

        optionA.setOnClickListener(this);
        optionB.setOnClickListener(this);
        optionC.setOnClickListener(this);
        optionD.setOnClickListener(this);

        getQuestionsList();

        score = 0;
    }

    private void getQuestionsList() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Chem");

        questionList.add(new A_AQuestionModel("The maximum number of electron pairs of an atom that are associated with principal quantum number n=3 is, ","3","4","5","9","4"));
        questionList.add(new A_AQuestionModel("The number of stable resonance structures that can be drawn for the oxalate ion [C2O42-]/(O2C-CO2)2-  is,","2","3","4","5","4"));
        questionList.add(new A_AQuestionModel("What is the IUPAC name of the following compound? HOCH2CH2CH2COCH2NH2","5-hydroxy-2-oxo-1-pentanamine","1-amino-5-hydroxy-2-pentanone"," 1-amino-5-hydroxy-2-oxopentane","5-amino-4-oxo-1-pentanol","2"));
        questionList.add(new A_AQuestionModel("Identify the pair of elements which has the largest difference in electronegativities."," B and Al","Be and Al","B and Si","B and C","3"));
        questionList.add(new A_AQuestionModel("Which of the following statements with regard to polymers is correct?","Nylon is an addition polymer","Teflon is a condensation polymer","Bakelite is a linear polymer","The number of carbon atoms in the repeating unit of natural rubber is 4","3"));


        setQuestion();

    }
    private void setQuestion(){
        timer.setText(String.valueOf(10));

        question.setText(questionList.get(0).getQuestion());
        optionA.setText(questionList.get(0).getOptionA());
        optionB.setText(questionList.get(0).getOptionB());
        optionC.setText(questionList.get(0).getOptionC());
        optionD.setText(questionList.get(0).getOptionD());



        qCount.setText(String.valueOf(1)+"/"+String.valueOf(questionList.size()));
        statTimer();

        queNum = 0;

    }


    private void statTimer(){
        countDown = new CountDownTimer(12000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished < 10000)
                    timer.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                changeQuestion();
            }
        };
        countDown.start();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {

        String selectedOption = "0";
        switch (v.getId()){
            case R.id.optionA:
                selectedOption = "1";
                break;

            case R.id.optionB:
                selectedOption ="2";
                break;

            case R.id.optionC:
                selectedOption="3";
                break;

            case R.id.optionD:
                selectedOption="4";
                break;

            default:

        }
        countDown.cancel();

        checkAnswer(selectedOption, v);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(String selectedOption, View view) {
        if(selectedOption == questionList.get(queNum).getCorrectAns()){
            //Right Answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            score++;
        }
        else {
            //Wrong Answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.RED));


            switch (questionList.get(queNum).getCorrectAns()){
                case "1":
                    optionA.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case "2":
                    optionB.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case "3":
                    optionC.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case "4":
                    optionD.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
            }
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeQuestion();
            }
        }, 1500);

    }
    private void changeQuestion(){
        if(queNum < questionList.size() - 1){
            queNum++;

            playAnim(question, 0,0);
            playAnim(optionA, 0,1);
            playAnim(optionB, 0,2);
            playAnim(optionC, 0,3);
            playAnim(optionD, 0,4);


            qCount.setText(String.valueOf(queNum+1)+"/"+String.valueOf(questionList.size()));

            timer.setText(String.valueOf(10));
            statTimer();

        }
        else {
            //go to scores
            Intent intent = new Intent(A_ChemiActivity.this, A_ScoreActivity.class);
            intent.putExtra("SCORE", String.valueOf(score) + "/"+String.valueOf(questionList.size()));
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            //Questions.this.finish();
        }
    }
    private void playAnim(View view, final int value, int viewNum){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (value == 0){
                            switch (viewNum)
                            {
                                case 0:
                                    ((TextView)view).setText(questionList.get(queNum).getQuestion());
                                    break;
                                case 1:
                                    ((Button)view).setText(questionList.get(queNum).getOptionA());
                                    break;
                                case 2:
                                    ((Button)view).setText(questionList.get(queNum).getOptionB());
                                    break;
                                case 3:
                                    ((Button)view).setText(questionList.get(queNum).getOptionC());
                                    break;
                                case 4:
                                    ((Button)view).setText(questionList.get(queNum).getOptionD());
                                    break;
                            }

                            if (viewNum != 0){
                                ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000B76")));
                            }

                            playAnim(view,1,viewNum);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        countDown.cancel();
    }
}