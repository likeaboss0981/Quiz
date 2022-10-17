package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button promptButton;
    private TextView questionTextView;

    private int currentIndex = 0;
    private int score = 0;
    private int buttonClicked = 0;

    private static final String TAG = "MainActivity";
    private static final String KEY_CURRENT_INDEX = "currentIndex";
    public static final String KEY_EXTRA_ANSWER = "extraanswer";
    private static final int REQUEST_CODE_PROMPT = 0;
    private boolean answerWasShown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Wywołana została matoda życia :onCreate");
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }
        promptButton = findViewById(R.id.promptButton);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);

        trueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                buttonClicked++;
                checkAnswerCorrectness(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicked++;
                checkAnswerCorrectness(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex + 1)%questions.length;
                answerWasShown = false;
                setNextQuestion();

            }
        });

        promptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PromptActivity.class);
                boolean correctAnswer = questions[currentIndex].isTrueAnswer();
                intent.putExtra(KEY_EXTRA_ANSWER,correctAnswer);
                startActivityForResult(intent,REQUEST_CODE_PROMPT);
            }
        });
        setNextQuestion();
    }

    private Question[] questions = new Question[]{
            new Question(R.string.dwusieczna,true),
            new Question(R.string.czworokat,false),
            new Question(R.string.liczba_pi,false),
            new Question(R.string.prostokat,false),
            new Question(R.string.trojkat_rownoboczny,true)
    };

    private void checkAnswerCorrectness(boolean userAnswer) {
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        if (answerWasShown) {
            resultMessageId = R.string.answer_was_shown;
        }
        else {
            if (userAnswer == correctAnswer) {
                resultMessageId = R.string.correct_answer;
                score++;
            } else {
                resultMessageId = R.string.incorrect_answer;
            }
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }

    private void setNextQuestion(){
        if(buttonClicked == questions.length)
            finish();
        questionTextView.setText(questions[currentIndex].getQuestionId());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK)
            return;
        if(requestCode == REQUEST_CODE_PROMPT){
            if(data == null)
                return;
            answerWasShown = data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER_SHOWN,false);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"Wywołana została matoda życia :onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"Wywołana została matoda życia :onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Wywołana została matoda życia :onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"Wywołana została matoda życia :onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"Wywołana została matoda życia :onResume");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"Wywołana została metoda: onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX, currentIndex);
    }

    public void finish(){
        String Title="Wynik";
        new AlertDialog.Builder(this)
                .setTitle(Title)
                .setMessage("Zdobył*ś " + score +"/" + questions.length + "pkt")
                .show();

    }


}