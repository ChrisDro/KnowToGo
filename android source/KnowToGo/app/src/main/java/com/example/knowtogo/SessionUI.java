package com.example.knowtogo;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Random;

public class SessionUI extends AppCompatActivity {

    static final int ON_TIMEOUT = 1;
    static final int ON_ENTER_PRESSED = 2;

    //UI elements
    Button endButton;
    TextView problemText;
    TextView answerText;
    TextView timeLeftText;
    TextView correctText;
    TextView incorrectText;
    TextView timeUpText;
    TextView progressText;
    Button delButton;
    Button enterButton;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;

    //For timer
    CountDownTimer timer;

    //For checking answer
    String correctAnswer;
    Animation fadeOut;

    //tracks num problems asked
    int problemsAsked;
    int correctAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_ui_view);

        //Get UI elements
        endButton = (Button)findViewById(R.id.SessionUIEndButton);
        problemText = (TextView)findViewById(R.id.ProblemTextView);
        answerText = (TextView)findViewById(R.id.AnswerTextView);
        timeLeftText = (TextView)findViewById(R.id.TimeRemainingTextView);
        correctText = (TextView)findViewById(R.id.CorrectTextView);
        incorrectText = (TextView)findViewById(R.id.IncorrectTextView);
        timeUpText = (TextView)findViewById(R.id.TimeUpTextView);
        progressText = (TextView)findViewById(R.id.ProgressText);
        delButton = (Button)findViewById(R.id.Backspace);
        enterButton = (Button)findViewById(R.id.EnterButton);
        button0 = (Button)findViewById(R.id.Button0);
        button1 = (Button)findViewById(R.id.Button1);
        button2 = (Button)findViewById(R.id.Button2);
        button3 = (Button)findViewById(R.id.Button3);
        button4 = (Button)findViewById(R.id.Button4);
        button5 = (Button)findViewById(R.id.Button5);
        button6 = (Button)findViewById(R.id.Button6);
        button7 = (Button)findViewById(R.id.Button7);
        button8 = (Button)findViewById(R.id.Button8);
        button9 = (Button)findViewById(R.id.Button9);

        //begin
        init();
        //start session
        startProblem();

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                timer.cancel();
                try {
                    ((Globals) getApplication()).getBtSocket().getOutputStream().write("0".toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent i = new Intent(SessionUI.this, HomePage.class);
                startActivity(i);
            }
        });

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(answerText.getText().toString().length() > 0){
                    answerText.setText(answerText.getText().toString().substring(0, answerText.getText().toString().length()-1));
                }
            }
        });

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                checkAnswer(ON_ENTER_PRESSED);
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                answerText.append("0");
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                answerText.append("1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                answerText.append("2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                answerText.append("3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                answerText.append("4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                answerText.append("5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                answerText.append("6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                answerText.append("7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                answerText.append("8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                answerText.append("9");
            }
        });
    }

    private void init(){
        problemsAsked = 0;
        correctAnswers = 0;

        problemText.setText("");
        answerText.setText("");
        timeLeftText.setText("Time Remaining: 5");
        progressText.setText(problemsAsked + "/" + Integer.toString(((Globals) getApplication()).getSessionLength()));
        correctText.setAlpha(0);
        incorrectText.setAlpha(0);
        timeUpText.setAlpha(0);
    }

    private void startProblem(){
        if(problemsAsked++ < ((Globals) getApplication()).getSessionLength()){
            //start timer
            timer = new CountDownTimer(5000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeLeftText.setText("Time Remaining: " + (millisUntilFinished/1000 + 1));
                }

                @Override
                public void onFinish() {
                    timeLeftText.setText("Time Remaining: 0");
                    checkAnswer(ON_TIMEOUT);
                }
            }.start();

            //create problem
            problemText.setText(createRandProblem());
            answerText.setText("");
            progressText.setText(problemsAsked + "/" + Integer.toString(((Globals) getApplication()).getSessionLength()));
        }
        else{
            try {
                ((Globals) getApplication()).getBtSocket().getOutputStream().write("0".toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            ((Globals) getApplication()).setFinalScore(correctAnswers);

            Intent i = new Intent(SessionUI.this, SessionEnd.class);
            startActivity(i);
        }
    }

    private String createRandProblem(){
        int operand1, operand2;
        String operator;

        Random rand = new Random();
        operator = ((Globals) getApplication()).getOperators().get(rand.nextInt(((Globals) getApplication()).getOperators().size()));

        if(operator == " + "){
            if(((Globals) getApplication()).getDifficulty() == Globals.EASY_MODE){
                operand1 = rand.nextInt(9) + 1;
                operand2 = rand.nextInt(9) + 1;
            }
            else if(((Globals) getApplication()).getDifficulty() == Globals.MEDIUM_MODE){
                operand1 = rand.nextInt(90) + 10;
                operand2 = rand.nextInt(90) + 10;
            }
            else{
                operand1 = rand.nextInt(900) + 100;
                operand2 = rand.nextInt(900) + 100;
            }
            correctAnswer = Integer.toString(operand1 + operand2);
            return operand1 + operator + operand2 + " = ";
        }
        else if(operator == " - "){
            if(((Globals) getApplication()).getDifficulty() == Globals.EASY_MODE){
                operand1 = rand.nextInt(9) + 1;
                operand2 = rand.nextInt(9) + 1;
            }
            else if(((Globals) getApplication()).getDifficulty() == Globals.MEDIUM_MODE){
                operand1 = rand.nextInt(90) + 10;
                operand2 = rand.nextInt(90) + 10;
            }
            else{
                operand1 = rand.nextInt(900) + 100;
                operand2 = rand.nextInt(900) + 100;
            }
            if(operand1 > operand2){
                correctAnswer = Integer.toString(operand1 - operand2);
                return operand1 + operator + operand2 + " = ";
            }
            correctAnswer = Integer.toString(operand2 - operand1);
            return operand2 + operator + operand1 + " = ";
        }
        else if(operator == " * "){
            if(((Globals) getApplication()).getDifficulty() == Globals.EASY_MODE){
                operand1 = rand.nextInt(5) + 1;
                operand2 = rand.nextInt(5) + 1;
            }
            else if(((Globals) getApplication()).getDifficulty() == Globals.MEDIUM_MODE){
                operand1 = rand.nextInt(5) + 6;
                operand2 = rand.nextInt(5) + 6;
            }
            else{
                operand1 = rand.nextInt(5) + 11;
                operand2 = rand.nextInt(5) + 11;
            }
            correctAnswer = Integer.toString(operand1 * operand2);
            return operand1 + operator + operand2 + " = ";
        }
        else if(operator == " / "){
            if(((Globals) getApplication()).getDifficulty() == Globals.EASY_MODE){
                operand1 = rand.nextInt(5) + 1;
                operand2 = rand.nextInt(5) + 1;
            }
            else if(((Globals) getApplication()).getDifficulty() == Globals.MEDIUM_MODE){
                operand1 = rand.nextInt(5) + 6;
                operand2 = rand.nextInt(5) + 6;
            }
            else{
                operand1 = rand.nextInt(5) + 11;
                operand2 = rand.nextInt(5) + 11;
            }
            correctAnswer = Integer.toString(operand1);
            return (operand1*operand2) + operator + operand2 + " = ";
        }

        return "Error in Create Problem";
    }

    private void checkAnswer(int reason){
        if(reason == ON_TIMEOUT){
            if(answerText.getText().toString().equals(correctAnswer)){
                correctAnswers++;
                try {
                    ((Globals) getApplication()).getBtSocket().getOutputStream().write("1".toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                correctText.setAlpha(1);
                correctText.animate().alpha(0.0f);
            }
            else{
                try {
                    ((Globals) getApplication()).getBtSocket().getOutputStream().write("0".toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                timeUpText.setAlpha(1);
                timeUpText.animate().alpha(0.0f);
            }
        }
        else if(reason == ON_ENTER_PRESSED){
            timer.cancel();
            if(answerText.getText().toString().equals(correctAnswer)){
                correctAnswers++;
                try {
                    ((Globals) getApplication()).getBtSocket().getOutputStream().write("1".toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                correctText.setAlpha(1);
                correctText.animate().alpha(0.0f);
            }
            else{
                try {
                    ((Globals) getApplication()).getBtSocket().getOutputStream().write("0".toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                incorrectText.setAlpha(1);
                incorrectText.animate().alpha(0.0f);
            }
        }
        startProblem();
    }
}
