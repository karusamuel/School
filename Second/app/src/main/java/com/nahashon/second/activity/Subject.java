package com.nahashon.second.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nahashon.second.R;
import com.nahashon.second.adapter.Adapter;
import com.nahashon.second.adapter.SubjectArrayAdapter;

import java.util.ArrayList;

public class Subject extends AppCompatActivity {

    FirebaseDatabase mD;
    DatabaseReference mDatabase;
    String level;
    ListView subjectListView;
    BaseAdapter adapter;
    int[] array;
    ArrayList<String> subjectArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        mD = FirebaseDatabase.getInstance();

        subjectListView = findViewById(R.id.subject_list_view);
//Getting the intents
        Intent subject = getIntent();
        level = subject.getStringExtra("Level");

        //set the Action bar with level name




        mDatabase=mD.getReference().child("School").child(level);

        adapter = new SubjectArrayAdapter(this,subjectArrayList);


        //this code adds items to my Adapter;
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String mySubject = snapshot.getValue().toString();
                    subjectArrayList.add(mySubject);


                }
                adapter.notifyDataSetChanged();
                subjectListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Subject.this,databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //list view On item Click listener
        subjectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Intent topicIntent = new Intent(Subject.this, Topic.class);
                    topicIntent.putExtra("Level", level);
                    topicIntent.putExtra("Subject", subjectArrayList.get(position));
                    startActivity(topicIntent);
            }
        });
    }
}
