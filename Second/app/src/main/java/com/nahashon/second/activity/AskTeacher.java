package com.nahashon.second.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nahashon.second.R;
import com.nahashon.second.adapter.Adapter;
import com.nahashon.second.adapter.Question;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AskTeacher extends AppCompatActivity {
    ImageView sendQuestion;
    EditText newQuestion;
    ListView questionList;
    DatabaseReference mRef;
    FirebaseDatabase database;
    RecyclerView list1;
    List<Question> list = new ArrayList<>();
    List<Question> invert = new ArrayList<>();


    public void answered(View view) {

    }


    public void pending(View view) {

    }


    public void archives(View view) {

    }

    public void myQ(View view) {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_teacher);



        //Firebase Instance and References.
        database = FirebaseDatabase.getInstance();

        mRef = database.getReference().child("Questions");
        mRef.keepSynced(true);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    list.add(snapshot.getValue(Question.class));

                }

               for(int i=list.size();i>=0;i--){
                    invert.add(list.get(i));


               }
               list1.setAdapter(new Adapter(invert));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Calling the ImageView and the textView to type my question
        newQuestion = findViewById(R.id.new_question);
        sendQuestion = findViewById(R.id.send_question);
        //questionList = findViewById(R.id.question_list);
        list1 = findViewById(R.id.list1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list1.setLayoutManager(layoutManager);


        sendQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sendQuiz()) {
                    Toast.makeText(getApplicationContext(), "Sent", Toast.LENGTH_LONG).show();
                    newQuestion.setText("");
                }
            }
        });


    }


    public boolean sendQuiz() {
        boolean results = true;
        if (newQuestion.getText().toString().isEmpty() || newQuestion.getText().toString().length() < 10) {
            results = false;


        } else {
            String key = mRef.push().getKey();
            mRef.child(key).setValue(new Question("Sam", DateFormat.getDateTimeInstance().format(new Date()), newQuestion.getText().toString().trim()));
        }
        return results;

    }

}
