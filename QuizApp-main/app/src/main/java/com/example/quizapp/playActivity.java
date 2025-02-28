package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class playActivity extends AppCompatActivity {
    String[] question_list = {
            "Who is the founder of the Indian Space Research Organisation (ISRO)?",
            "What was the Jallianwala Bagh massacre, and in which year did it occur?",
            "What is the name of the ancient Indian text on performing arts, including dance, music, and drama?",
            "Which of the following islands is known as Peacock Island?",
            "In which of the following Himalayas, the highest mountain peaks of Himalayas are found?",
            "How many cup has RCB won till 2024?",
            "What are ‘Nightjars’, recently seen in news?",
            "What is the purpose of the “International Medical Device Regulators Forum (IMDRF)”, recently joined by India?",
            "Megalencephaly condition involves the unusual size of which part of the body?",
            "Which state has the highest population of Snow Leopard Count in India?"
    };

    String[] choose_list = {
            "Satish Dhawan", "APJ Abdul Kalam", "Dr. Vikram Sarabhai", "Nambi Narayanan",
            "1914", "1919", "1928", "1947",
            "Natya Shastra", "Anubhav Shastra", "Satvik Bhav Shastra", "Vithi Shastra",
            "Divar Island", "Sagardwip Island", "Majuli Island", "Umananda Island",
            "Trans Himalayan region", "Western Himalayas", "Central Himalayas", "Eastern Himalayas",
            "0", "1", "4", "6",
            "Rare fossils discovered in Assam","They are medium sized nocturnal insectivorous birds","A new cybersecurity application","It is a technology to improve railway services",
            "Promote medical tourism","Manufacture medical devices","Harmonize medical device regulations","supply medical equipment",
            "Brain","Heart","Lever","Lungs",
            "Kashmir","Uttarakhand","Sikkim","Himachal Pradesh"
    };

    String[] correct_list = {
            "Dr. Vikram Sarabhai", "1919", "Natya Shastra", "Umananda Island", "Central Himalayas", "0",
            "They are medium sized nocturnal insectivorous birds","Harmonize medical device regulations",
            "Brain","Himachal Pradesh"
    };

    TextView cpt_question, text_question;
    Button btn_choose1, btn_choose2, btn_choose3, btn_choose4, btn_next;

    int currentQuestion = 0;
    int scorePlayer = 0;
    boolean isclickBtn = false;
    String valueChoose = "";
    Button btn_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // Initialize UI elements
        cpt_question = findViewById(R.id.cpt_question);
        text_question = findViewById(R.id.text_question);
        btn_choose1 = findViewById(R.id.btn_choose1);
        btn_choose2 = findViewById(R.id.btn_choose2);
        btn_choose3 = findViewById(R.id.btn_choose3);
        btn_choose4 = findViewById(R.id.btn_choose4);

        btn_next = findViewById(R.id.btn_next);

        // Back button to finish activity
        findViewById(R.id.image_back).setOnClickListener(
                a -> finish()
        );

        // Load the first question
        remplirData();

        // Next button functionality
        btn_next.setOnClickListener(
                view -> {
                    if (isclickBtn) {
                        isclickBtn = false;

                        if (!valueChoose.equals(correct_list[currentQuestion])) {
                            // Incorrect answer
                            Toast.makeText(playActivity.this, "Wrong Answer", Toast.LENGTH_LONG).show();
                            btn_click.setBackgroundResource(R.drawable.background_btn_erreur);
                        } else {
                            // Correct answer
                            Toast.makeText(playActivity.this, "Correct Answer", Toast.LENGTH_LONG).show();
                            btn_click.setBackgroundResource(R.drawable.background_btn_correct);
                            scorePlayer++;
                        }

                        // Move to next question after a delay
                        new Handler().postDelayed(() -> {
                            if (currentQuestion != question_list.length - 1) {
                                currentQuestion++;
                                remplirData();
                                valueChoose = "";

                                // Reset button colors and enable them
                                btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
                                btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
                                btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
                                btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);


                                btn_choose1.setEnabled(true);
                                btn_choose2.setEnabled(true);
                                btn_choose3.setEnabled(true);
                                btn_choose4.setEnabled(true);

                            } else {
                                // End of quiz, navigate to ResultActivity
                                Intent intent = new Intent(playActivity.this, ResulteActivity.class);
                                intent.putExtra("Result", scorePlayer);
                                startActivity(intent);
                                finish();
                            }
                        }, 2000);
                    } else {
                        Toast.makeText(playActivity.this, "You must choose an option!", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    // Populate question and answers
    void remplirData() {
        cpt_question.setText((currentQuestion + 1) + "/" + question_list.length);
        text_question.setText(question_list[currentQuestion]);

        btn_choose1.setText(choose_list[4 * currentQuestion]);
        btn_choose2.setText(choose_list[4 * currentQuestion + 1]);
        btn_choose3.setText(choose_list[4 * currentQuestion + 2]);
        btn_choose4.setText(choose_list[4 * currentQuestion + 3]);

    }

    // Button click event to choose an answer
    public void ClickChoose(View view) {
        btn_click = (Button) view;

        // Reset button backgrounds if already clicked
        if (isclickBtn) {
            btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);
        }
        chooseBtn();
    }

    // Highlight selected button and save the choice
    void chooseBtn() {
        btn_click.setBackgroundResource(R.drawable.background_btn_choose_color);
        isclickBtn = true;
        valueChoose = btn_click.getText().toString();
    }
}
