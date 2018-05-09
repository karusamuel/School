package com.nahashon.second.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nahashon.second.R;


public class AnswerActivity extends AppCompatActivity {
    TextView name,time,question,answer;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_activity_layout);
        name= findViewById(R.id.name);
        time= findViewById(R.id.date);
        question= findViewById(R.id.Question);
        answer= findViewById(R.id.answer);



        Intent intent =  getIntent();
        String senderName = intent.getStringExtra("Sender");
        getSupportActionBar().setTitle(senderName+" Question");

        name.setText(intent.getStringExtra("Sender"));
        time.setText(intent.getStringExtra("Time"));
        question.setText(intent.getStringExtra("Question"));
        answer.setText(intent.getStringExtra("Answer"));


    }
}
