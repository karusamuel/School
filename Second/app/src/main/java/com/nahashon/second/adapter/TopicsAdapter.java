package com.nahashon.second.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nahashon.second.R;
import com.nahashon.second.activity.NoteViewer;

import java.util.ArrayList;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.TopicViewHolder> {
    private ArrayList<String> topicsArray;
    private Intent intent;
    class TopicViewHolder extends RecyclerView.ViewHolder{
        TextView topicName;
        ImageView video,text;
        public TopicViewHolder(View view){
            super(view);
            topicName = view.findViewById(R.id.topicName);
            video = view.findViewById(R.id.videoMode);
            text = view.findViewById(R.id.textMode);


        }


    }

    public int getItemCount(){

      return topicsArray.size();
    }
    public TopicViewHolder onCreateViewHolder(ViewGroup parent,int type){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.topics_adapter_layout,parent,false);
        return new TopicViewHolder(view);


    }
    public  void onBindViewHolder(TopicViewHolder holder, final int position){
        holder.topicName.setText(topicsArray.get(position));
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(view,position);

            }
        });

    }
    private void startActivity(View view,int position){
        Intent noteIntent = new Intent(view.getContext(), NoteViewer.class);
        noteIntent.putExtra("Level",intent.getStringExtra("Level"));
        noteIntent.putExtra("Subject",intent.getStringExtra("Subject") );
        noteIntent.putExtra("Topic", topicsArray.get(position));
        view.getContext().startActivity(noteIntent);
    }

    public TopicsAdapter(ArrayList<String> list,Intent intent){
        this.intent = intent;
        topicsArray=list;


    }



}
