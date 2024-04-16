package com.example.firstproject;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button lessonsbtn;
    Button quizbtn;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Activity created");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lessonsbtn = findViewById(R.id.lessonsbtn);
        quizbtn = findViewById(R.id.quizbtn);

        lessonsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LessonActivity.class);
                startActivity(intent);
            }
        });

        quizbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
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
    moveTaskToBack(true);
}

private void showDialog() {
    View dialogView = getLayoutInflater().inflate(R.layout.dialog_quiz_options, null);

    RadioGroup radioGroupDifficulty = dialogView.findViewById(R.id.radioGroupDifficulty);
    Button buttonStartQuiz = dialogView.findViewById(R.id.buttonStartQuiz);

    SharedPreferences preferences = getSharedPreferences("QuizPreferences", MODE_PRIVATE);
    String selectedDifficulty = preferences.getString("DifficultyLevel", "");

    if (selectedDifficulty.equals("Easy")) {
        radioGroupDifficulty.check(R.id.radioButtonEasy);
    } else if (selectedDifficulty.equals("Hard")) {
        radioGroupDifficulty.check(R.id.radioButtonHard);
    }

    Dialog dialog = new Dialog(this);
    dialog.setContentView(dialogView);
    dialog.getWindow().setLayout(1000, 500);

    buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int checkedRadioButtonId = radioGroupDifficulty.getCheckedRadioButtonId();
            String difficultyLevel = null;

            if (checkedRadioButtonId != -1) {
                if (checkedRadioButtonId == R.id.radioButtonEasy) {
                    difficultyLevel = "Easy";
                } else if (checkedRadioButtonId == R.id.radioButtonHard) {
                    difficultyLevel = "Hard";
                }
            }

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("DifficultyLevel", difficultyLevel);
            editor.apply();

            if (difficultyLevel != null) {
                Intent intent;
                if (difficultyLevel.equals("Easy")) {
                    intent = new Intent(MainActivity.this, EasyQuizActivity.class);
                } else {
                    intent = new Intent(MainActivity.this, HardQuizActivity.class);
                }
                intent.putExtra("difficultyLevel", difficultyLevel);
                startActivity(intent);
            }

            dialog.dismiss();
        }
    });

    dialog.show();
}



}