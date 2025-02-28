package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ResulteActivity extends AppCompatActivity {

    TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resulte);
        textView = findViewById(R.id.textView);

        // Get the score from Intent
        int score = getIntent().getIntExtra("Result", 0);

        // Debugging: Show the score in a toast to verify it's correctly received
        Toast.makeText(ResulteActivity.this, "Final Score: " + score, Toast.LENGTH_LONG).show();

        // Display score in TextView
        textView.setText("Score: " + score);

        // Restart button functionality
        findViewById(R.id.btn_restart).setOnClickListener(
                restart -> {
                    Intent intent = new Intent(ResulteActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
        );
    }
}