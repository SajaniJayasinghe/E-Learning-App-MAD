package com.example.mynew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class S_MainActivity extends AppCompatActivity {

    private static int QUICK_LEARN_SCREAN = 5000;

    //variables
    Animation topAnim ,bottomAnim;
    ImageView image;
    TextView logo,slog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        //Animation
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //hooks
        image = findViewById(R.id.homepage);
        logo = findViewById(R.id.homeheader);
        slog = findViewById(R.id.homeheader1);

        image.setAnimation(topAnim);
        image.setAnimation(bottomAnim);
        slog.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(S_MainActivity.this, S_MainActivity2.class);
                startActivity(intent);
                finish();
            }
        },QUICK_LEARN_SCREAN);
    }
}