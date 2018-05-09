package com.nahashon.second.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nahashon.second.R;
import com.nahashon.second.adapter.TopicsAdapter;

import java.util.ArrayList;

public class Topic extends AppCompatActivity {
    DatabaseReference mDatabase;
    FirebaseDatabase mD;
    RecyclerView allTopics;
    ArrayList<String> topicsArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);


//Topic list
        allTopics = findViewById(R.id.all_topics);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        allTopics.setLayoutManager(linearLayoutManager);

        //Getting the intent sent
        Intent mTopics = getIntent();
        final String level = mTopics.getStringExtra("Level");
        final String subject = mTopics.getStringExtra("Subject");

        //set the action bar with name of subject

        mD = FirebaseDatabase.getInstance();



        mDatabase=mD.getReference().child(subject).child(level + subject);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String topic = snapshot.getKey().toString();
                    topicsArray.add(topic);
                    // adapter.notifyDataSetChanged();

                }
                allTopics.setAdapter(new TopicsAdapter(topicsArray,getIntent()));


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //setting the Onclick on individual item on the topic list.


    }
}