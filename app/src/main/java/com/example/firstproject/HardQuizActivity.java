package com.example.firstproject;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HardQuizActivity extends AppCompatActivity {
    Button backbtn;
    private TextView textViewAdditionQuestion;
    private RadioButton radioButtonAdditionOption1;
    private RadioButton radioButtonAdditionOption2;
    private RadioButton radioButtonAdditionOption3;
    private TextView textViewSubtractionQuestion;
    private RadioButton radioButtonSubtractionOption1;
    private RadioButton radioButtonSubtractionOption2;
    private RadioButton radioButtonSubtractionOption3;
    private TextView textViewMultiplicationQuestion;
    private RadioButton radioButtonMultiplicationOption1;
    private RadioButton radioButtonMultiplicationOption2;
    private RadioButton radioButtonMultiplicationOption3;
    private TextView textViewDivisionQuestion;
    private RadioButton radioButtonDivisionOption1;
    private RadioButton radioButtonDivisionOption2;
    private RadioButton radioButtonDivisionOption3;
    private static final String TAG = "HardQuizActivity";


    private String[][] questions = {
            // Questions for addition
            {"What is 47 + 51 + 73?", "174", "180","171"},
            // Questions for subtraction
            {"What is 79 - 24 - 31?", "28","24","21"},
            // Questions for multiplication
            {"What is 17 * 8 * 2?", "260","272","251"},
            // Questions for division
            {"What is 512 / 8?", "32","54","64"}
    };


    private int currentQuestionIndex = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hard_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        textViewAdditionQuestion = findViewById(R.id.textViewAdditionQuestion);
        radioButtonAdditionOption1 = findViewById(R.id.radioButtonAdditionOption1);
        radioButtonAdditionOption2 = findViewById(R.id.radioButtonAdditionOption2);
        radioButtonAdditionOption3 = findViewById(R.id.radioButtonAdditionOption3);
        textViewSubtractionQuestion = findViewById(R.id.textViewSubtractionQuestion);
        radioButtonSubtractionOption1 = findViewById(R.id.radioButtonSubtractionOption1);
        radioButtonSubtractionOption2 = findViewById(R.id.radioButtonSubtractionOption2);
        radioButtonSubtractionOption3 = findViewById(R.id.radioButtonSubtractionOption3);
        textViewMultiplicationQuestion = findViewById(R.id.textViewMultiplicationQuestion);
        radioButtonMultiplicationOption1 = findViewById(R.id.radioButtonMultiplicationOption1);
        radioButtonMultiplicationOption2 = findViewById(R.id.radioButtonMultiplicationOption2);
        radioButtonMultiplicationOption3 = findViewById(R.id.radioButtonMultiplicationOption3);
        textViewDivisionQuestion = findViewById(R.id.textViewDivisionQuestion);
        radioButtonDivisionOption1 = findViewById(R.id.radioButtonDivisionOption1);
        radioButtonDivisionOption2 = findViewById(R.id.radioButtonDivisionOption2);
        radioButtonDivisionOption3 = findViewById(R.id.radioButtonDivisionOption3);


        loadQuestion();
        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleUserResponse();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: Activity started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: Activity resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: Activity paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: Activity stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Activity destroyed");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: Activity restarted");
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed: Back button pressed");

    }
    private void loadQuestion() {
        String[] currentQuestion = questions[currentQuestionIndex];
        textViewAdditionQuestion.setText(currentQuestion[0]);
        textViewSubtractionQuestion.setText(questions[currentQuestionIndex + 1][0]);
        textViewMultiplicationQuestion.setText(questions[currentQuestionIndex + 2][0]);
        textViewDivisionQuestion.setText(questions[currentQuestionIndex + 3][0]);

        radioButtonAdditionOption1.setText(currentQuestion[1]);
        radioButtonSubtractionOption1.setText(questions[currentQuestionIndex + 1][1]);
        radioButtonMultiplicationOption1.setText(questions[currentQuestionIndex + 2][1]);
        radioButtonDivisionOption1.setText(questions[currentQuestionIndex + 3][1]);

        radioButtonAdditionOption2.setText(currentQuestion[2]);
        radioButtonSubtractionOption2.setText(questions[currentQuestionIndex + 1][2]);
        radioButtonMultiplicationOption2.setText(questions[currentQuestionIndex + 2][2]);
        radioButtonDivisionOption2.setText(questions[currentQuestionIndex + 3][2]);

        radioButtonAdditionOption3.setText(currentQuestion[3]);
        radioButtonSubtractionOption3.setText(questions[currentQuestionIndex + 1][3]);
        radioButtonMultiplicationOption3.setText(questions[currentQuestionIndex + 2][3]);
        radioButtonDivisionOption3.setText(questions[currentQuestionIndex + 3][3]);
    }

    private void handleUserResponse() {
        if(!isQuestionAnswered()){
            Toast.makeText(this, "Please answer all questions", Toast.LENGTH_SHORT).show();
            return;
        }
        String additionAnswer = radioButtonAdditionOption1.isChecked() ? radioButtonAdditionOption1.getText().toString() :
                (radioButtonAdditionOption2.isChecked() ? radioButtonAdditionOption2.getText().toString() : radioButtonAdditionOption3.getText().toString());
        String subtractionAnswer = radioButtonSubtractionOption1.isChecked() ? radioButtonSubtractionOption1.getText().toString() :
                (radioButtonSubtractionOption2.isChecked() ? radioButtonSubtractionOption2.getText().toString() : radioButtonSubtractionOption3.getText().toString());
        String multiplicationAnswer = radioButtonMultiplicationOption1.isChecked() ? radioButtonMultiplicationOption1.getText().toString() :
                (radioButtonMultiplicationOption2.isChecked() ? radioButtonMultiplicationOption2.getText().toString() : radioButtonMultiplicationOption3.getText().toString());
        String divisionAnswer = radioButtonDivisionOption1.isChecked() ? radioButtonDivisionOption1.getText().toString() :
                (radioButtonDivisionOption2.isChecked() ? radioButtonDivisionOption2.getText().toString() : radioButtonDivisionOption3.getText().toString());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Answers");

        String message = "First Question: " + (additionAnswer.equals(questions[currentQuestionIndex][3]) ? "Correct" : "Wrong")
                + "\nSecond Question: " + (subtractionAnswer.equals(questions[currentQuestionIndex + 1][2]) ? "Correct" : "Wrong")
                + "\nThird Question: " + (multiplicationAnswer.equals(questions[currentQuestionIndex + 2][2]) ? "Correct" : "Wrong")
                + "\nFourth Question: " + (divisionAnswer.equals(questions[currentQuestionIndex + 3][3]) ? "Correct" : "Wrong");

        builder.setMessage(message);

        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                onBackPressed();
            }
        });

        builder.setNegativeButton("Restart", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                resetRadioButtons();
                dialog.dismiss();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean isQuestionAnswered() {
        return (radioButtonAdditionOption1.isChecked() || radioButtonAdditionOption2.isChecked() || radioButtonAdditionOption3.isChecked())
                && (radioButtonSubtractionOption1.isChecked() || radioButtonSubtractionOption2.isChecked() || radioButtonSubtractionOption3.isChecked())
                && (radioButtonMultiplicationOption1.isChecked() || radioButtonMultiplicationOption2.isChecked() || radioButtonMultiplicationOption3.isChecked())
                && (radioButtonDivisionOption1.isChecked() || radioButtonDivisionOption2.isChecked() || radioButtonDivisionOption3.isChecked());
    }
    private void resetRadioButtons() {
        radioButtonAdditionOption1.setChecked(false);
        radioButtonAdditionOption2.setChecked(false);
        radioButtonAdditionOption3.setChecked(false);
        radioButtonSubtractionOption1.setChecked(false);
        radioButtonSubtractionOption2.setChecked(false);
        radioButtonSubtractionOption3.setChecked(false);
        radioButtonMultiplicationOption1.setChecked(false);
        radioButtonMultiplicationOption2.setChecked(false);
        radioButtonMultiplicationOption3.setChecked(false);
        radioButtonDivisionOption1.setChecked(false);
        radioButtonDivisionOption2.setChecked(false);
        radioButtonDivisionOption3.setChecked(false);
    }

}