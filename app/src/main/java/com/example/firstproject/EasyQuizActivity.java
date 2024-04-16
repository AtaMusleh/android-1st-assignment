package com.example.firstproject;

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

public class EasyQuizActivity extends AppCompatActivity {
    Button backbtn;
    private TextView textViewAdditionQuestion;
    private RadioButton radioButtonAdditionOption1;
    private RadioButton radioButtonAdditionOption2;
    private TextView textViewSubtractionQuestion;
    private RadioButton radioButtonSubtractionOption1;
    private RadioButton radioButtonSubtractionOption2;
    private TextView textViewMultiplicationQuestion;
    private RadioButton radioButtonMultiplicationOption1;
    private RadioButton radioButtonMultiplicationOption2;
    private TextView textViewDivisionQuestion;
    private RadioButton radioButtonDivisionOption1;
    private RadioButton radioButtonDivisionOption2;
    private static final String TAG = "EasyQuizActivity";


private String[][] questions = {
        // Questions for addition
        {"What is 12 + 5?", "19", "17"},
        // Questions for subtraction
        {"What is 9 - 4?", "5","4"},
        // Questions for multiplication
        {"What is 7 * 2?", "14","12"},
        // Questions for division
        {"What is 12 / 2?", "6","7"}
};


    private int currentQuestionIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_easy_quiz);
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
        textViewSubtractionQuestion = findViewById(R.id.textViewSubtractionQuestion);
        radioButtonSubtractionOption1 = findViewById(R.id.radioButtonSubtractionOption1);
        radioButtonSubtractionOption2 = findViewById(R.id.radioButtonSubtractionOption2);
        textViewMultiplicationQuestion = findViewById(R.id.textViewMultiplicationQuestion);
        radioButtonMultiplicationOption1 = findViewById(R.id.radioButtonMultiplicationOption1);
        radioButtonMultiplicationOption2 = findViewById(R.id.radioButtonMultiplicationOption2);
        textViewDivisionQuestion = findViewById(R.id.textViewDivisionQuestion);
        radioButtonDivisionOption1 = findViewById(R.id.radioButtonDivisionOption1);
        radioButtonDivisionOption2 = findViewById(R.id.radioButtonDivisionOption2);


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
    }

private void handleUserResponse() {
    String additionAnswer = radioButtonAdditionOption1.isChecked() ? radioButtonAdditionOption1.getText().toString() : radioButtonAdditionOption2.getText().toString();
    String subtractionAnswer = radioButtonSubtractionOption1.isChecked() ? radioButtonSubtractionOption1.getText().toString() : radioButtonSubtractionOption2.getText().toString();
    String multiplicationAnswer = radioButtonMultiplicationOption1.isChecked() ? radioButtonMultiplicationOption1.getText().toString() : radioButtonMultiplicationOption2.getText().toString();
    String divisionAnswer = radioButtonDivisionOption1.isChecked() ? radioButtonDivisionOption1.getText().toString() : radioButtonDivisionOption2.getText().toString();

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Answers");

    String message = "Addition Answer: " + (additionAnswer.equals(questions[currentQuestionIndex][2]) ? "Correct" : "Wrong")
            + "\nSubtraction Answer: " + (subtractionAnswer.equals(questions[currentQuestionIndex + 1][1]) ? "Correct" : "Wrong")
            + "\nMultiplication Answer: " + (multiplicationAnswer.equals(questions[currentQuestionIndex + 2][1]) ? "Correct" : "Wrong")
            + "\nDivision Answer: " + (divisionAnswer.equals(questions[currentQuestionIndex + 3][1]) ? "Correct" : "Wrong");

    builder.setMessage(message);

    builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            dialog.dismiss();
            onBackPressed();
        }
    });

    builder.setNegativeButton("Restart", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {

            radioButtonAdditionOption1.setChecked(false);
            radioButtonAdditionOption2.setChecked(false);
            radioButtonSubtractionOption1.setChecked(false);
            radioButtonSubtractionOption2.setChecked(false);
            radioButtonMultiplicationOption1.setChecked(false);
            radioButtonMultiplicationOption2.setChecked(false);
            radioButtonDivisionOption1.setChecked(false);
            radioButtonDivisionOption2.setChecked(false);
            //currentQuestionIndex = 0;
            dialog.dismiss();

        }
    });

    AlertDialog dialog = builder.create();
    dialog.show();
}

}