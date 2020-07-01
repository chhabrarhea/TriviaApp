package com.example.triviaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;


import com.example.triviaapp.data.AsyncResponse;
import com.example.triviaapp.data.Question;
import com.example.triviaapp.data.QuestionBank;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ArrayList<Question> questions;
TextView ques;
ImageButton next;
ImageButton prev;
Button tButton;
Button fButton;
int i;
int correctAns;
TextView score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ques=findViewById(R.id.question_textview);
        score=findViewById(R.id.counter_text);
        questions=new QuestionBank().getQuestions(new AsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionsList) {
                i=0;
                correctAns=0;
                ques.setText(questionsList.get(0).getQues());

            }
        });



    }

    private void fadeView() {
        final CardView cardView = findViewById(R.id.cardView);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);

        alphaAnimation.setDuration(350);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        cardView.setAnimation(alphaAnimation);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setCardBackgroundColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cardView.setCardBackgroundColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void shakeAnimation() {
        Animation shake = AnimationUtils.loadAnimation(MainActivity.this,
                R.anim.shake_animation);
        final CardView cardView = findViewById(R.id.cardView);
        cardView.setAnimation(shake);

        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setCardBackgroundColor(Color.RED);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cardView.setCardBackgroundColor(Color.WHITE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
    public void nextPressed(View view){
        if(i<100){
            i++;
            ques.setText(questions.get(i).getQues());}
    }
    public void prevPressed(View view){
        if(i>0){
            i--;
            ques.setText(questions.get((i)).getQues());}
    }
    public void truePressed(View view){  if(questions.get(i).getAns()==true)
    {
        correctAns++;
        score.setText(correctAns+" out of 100");
        fadeView();
    }
    else
        shakeAnimation();

        i++;
        ques.setText(questions.get(i).getQues());
    }
    public void falsePressed(View view){
        if(questions.get(i).getAns()==false)
        {
            correctAns++;
            score.setText(correctAns+" out of 100");
            fadeView();
        }
        else
        {


            shakeAnimation();
        }
        i++;
        ques.setText(questions.get(i).getQues());
    }



}