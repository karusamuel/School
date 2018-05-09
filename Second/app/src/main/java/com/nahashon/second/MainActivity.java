package com.nahashon.second;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nahashon.second.adapter.GridAdapter;
import com.nahashon.second.adapter.LevelClass;
import com.nahashon.second.activity.Subject;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Context context = MainActivity.this;
    DatabaseReference mDatabase;
    FirebaseDatabase mD;
    List<LevelClass> mLevel = new ArrayList<>();
    GridView grid;
    AVLoadingIndicatorView avl;
    TextView levelName, levelDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.gridView);
        avl = findViewById(R.id.avl);


        mD = FirebaseDatabase.getInstance();
       // mD.setPersistenceEnabled(true);

        mDatabase=mD.getReference().child("Levels");

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Intent subjectIntent = new Intent(context, Subject.class);
                    subjectIntent.putExtra("Level", mLevel.get(position).getName());
                    startActivity(subjectIntent);
            }
        });
        mDatabase.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    LevelClass levels = snapshot.getValue(LevelClass.class);
                    mLevel.add(levels);
                    avl.setVisibility(View.GONE);
                    grid.setVisibility(View.VISIBLE);

                }
                grid.setAdapter(new GridAdapter(mLevel));
            }

            public void onCancelled(DatabaseError error) {



            }
        });


    }

}
