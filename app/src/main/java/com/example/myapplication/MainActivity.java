package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;

    private int currentIndex = 0;
    private int score = 0;
    private int buttonClicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                setNextQuestion();

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

    private void checkAnswerCorrectness(boolean userAnswer){
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        if(userAnswer == correctAnswer){
            resultMessageId = R.string.correct_answer;
            score++;
        }
        else{
            resultMessageId = R.string.incorrect_answer;
        }
        Toast.makeText(this,resultMessageId,Toast.LENGTH_SHORT).show();
    }

    private void setNextQuestion(){
        if(buttonClicked == questions.length)
            finish();
        questionTextView.setText(questions[currentIndex].getQuestionId());

    }

    public void finish(){
        String Title="Wynik";
        new AlertDialog.Builder(this)
                .setTitle(Title)
                .setMessage("Zdobył*ś " + score +"/" + questions.length + "pkt")
                .show();

    }


}