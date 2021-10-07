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



public class A_MathsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView question,qCount,timer;
    private Button optionA, optionB, optionC, optionD;
    private List<A_AQuestionModel> questionList;
    DatabaseReference databaseReference;
    private int queNum;
    private CountDownTimer countDown;
    private int score;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths);

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

        questionList = new ArrayList<>();

//        recyclerView = (RecyclerView) findViewById(R.id.rv1);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        FirebaseRecyclerOptions<Question> options =
//                new FirebaseRecyclerOptions.Builder<Question>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("QUIZ"), Question.class)
//                        .build();
//
//        questionsAdapter = new QuestionsAdapter(options);
//        recyclerView.setAdapter(questionsAdapter);
//    setQuestion();
//    }
//
//
//        @Override
//        protected void onStart() {
//            super.onStart();
//            questionsAdapter.startListening();
//        }
//
//        @Override
//        protected void onStop() {
//            super.onStop();
//            questionsAdapter.stopListening();
//        }


//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference databaseReference = database.getReference();
//        databaseReference.child("QUIZ").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                //get all of children
//                Iterable<DataSnapshot> children = snapshot.getChildren();
//
//                for (DataSnapshot child : children) {
//                    QuestionModel questionModel = child.getValue(QuestionModel.class);
//                    questionList.add(questionModel);
//                }
//
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        setQuestion();
//    }




        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Maths");

//        myRef.setValue("Hello, World!");


//         Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    A_AQuestionModel questionModel = dataSnapshot.getValue(A_AQuestionModel.class);
                    questionList.add(questionModel);
                }


                setQuestion();
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

//        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (task.isSuccessful()){
//                    DataSnapshot snapshot = task.getResult();
//
//                    for (DataSnapshot dataSnapshot : snapshot){
//                        questionList.add(new QuestionModel(dataSnapshot.toString("question"),
//                                dataSnapshot.toString("optionA"),
//                                dataSnapshot.toString("optionB"),
//                                dataSnapshot.toString("optonC"),
//                                dataSnapshot.toString("optonD"),
//                                dataSnapshot.toString("correctAns")));
//                    }
//                }else {
//                    Toast.makeText(Questions.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });

//        databaseReference = FirebaseDatabase.getInstance().getReference("QUIZ");
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    QuestionModel questionModel = dataSnapshot.getValue(QuestionModel.class);
//                    questionList.add(questionModel);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    QuestionModel questionModel = dataSnapshot.getValue(QuestionModel.class);
//                    questionList.add(questionModel);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//            }
//        });
//        setQuestion();
//    }

//        dquestion = new Firebase("https://mad-27cd8-default-rtdb.asia-southeast1.firebasedatabase.app/"+queNum+"/question");
//
//        dquestion.addValueEventListener(new com.firebase.client.ValueEventListener() {
//            @Override
//            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
//                String question = dataSnapshot.getValue(String.class);
//                aquestion.setText(question);
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });

//        doptionA = new Firebase("https://mad-27cd8-default-rtdb.asia-southeast1.firebasedatabase.app/QUIZ"+queNum+"/optionA");
//
//        doptionA.addValueEventListener(new com.firebase.client.ValueEventListener() {
//            @Override
//            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
//                String optionA = dataSnapshot.getValue(String.class);
//                aoptionA.setText(optionA);
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        doptionB = new Firebase("https://mad-27cd8-default-rtdb.asia-southeast1.firebasedatabase.app/QUIZ"+queNum+"/optionB");
//
//        doptionB.addValueEventListener(new com.firebase.client.ValueEventListener() {
//            @Override
//            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
//                String optionB = dataSnapshot.getValue(String.class);
//                aoptionB.setText(optionB);
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        doptionC = new Firebase("https://mad-27cd8-default-rtdb.asia-southeast1.firebasedatabase.app/QUIZ"+queNum+"/optionC");
//
//        doptionC.addValueEventListener(new com.firebase.client.ValueEventListener() {
//            @Override
//            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
//                String optionC = dataSnapshot.getValue(String.class);
//                aoptionC.setText(optionC);
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        doptionD = new Firebase("https://mad-27cd8-default-rtdb.asia-southeast1.firebasedatabase.app/QUIZ"+queNum+"/optionD");
//
//        doptionC.addValueEventListener(new com.firebase.client.ValueEventListener() {
//            @Override
//            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
//                String optionC = dataSnapshot.getValue(String.class);
//                aoptionC.setText(optionC);
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        queNum++;


        questionList.add(new A_AQuestionModel("Question1","A","B","C","D","1"));
        questionList.add(new A_AQuestionModel("Question2","A","B","C","D","2"));
        questionList.add(new A_AQuestionModel("Question3","A","B","C","D","3"));
        questionList.add(new A_AQuestionModel("Question4","A","B","C","D","4"));
        questionList.add(new A_AQuestionModel("Question5","A","B","C","D","4"));
        questionList.add(new A_AQuestionModel("Question1","A","B","C","D","1"));
        questionList.add(new A_AQuestionModel("Question2","A","B","C","D","2"));
        questionList.add(new A_AQuestionModel("Question3","A","B","C","D","3"));
        questionList.add(new A_AQuestionModel("Question4","A","B","C","D","4"));
        questionList.add(new A_AQuestionModel("Question5","A","B","C","D","4"));

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
            Intent intent = new Intent(A_MathsActivity.this, A_ScoreActivity.class);
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