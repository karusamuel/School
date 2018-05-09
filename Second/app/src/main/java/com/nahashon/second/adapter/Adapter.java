package com.nahashon.second.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nahashon.second.R;
import com.nahashon.second.activity.AnswerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nahashon on 4/28/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.Myholder> {
    List<Question> list = new ArrayList<>();
    public Adapter (List<Question> list){
        this.list=list;

    }


    public Myholder onCreateViewHolder(ViewGroup parent, int type) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.question_list, parent, false);
        return new Myholder(view);


    }

    public void onBindViewHolder(Myholder holder, final int position) {
        holder.time.setText(list.get(position).getTime());
        holder.question.setText(list.get(position).getQuestion());
        holder.answer.setText(list.get(position).getAnswer());
        holder.name.setText(list.get(position).getSender());
        holder.view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(view.getContext(),AnswerActivity.class));

                Intent intent = new Intent(view.getContext(),AnswerActivity.class);
                intent.putExtra("Question",list.get(position).getQuestion());
                intent.putExtra("Answer",list.get(position).getAnswer());
                intent.putExtra("Time",list.get(position).getTime());
                intent.putExtra("Sender",list.get(position).getSender());
                view.getContext().startActivity(intent);
            }
        });


    }


    public int getItemCount() {

        return list.size();
    }


    class Myholder extends RecyclerView.ViewHolder {
        TextView name,question,answer,time;
        View view;
        public Myholder(View view) {
            super(view);
            name=view.findViewById(R.id.sender_name);
            question=view.findViewById(R.id.the_question);
            answer=view.findViewById(R.id.the_answer);
            time=view.findViewById(R.id.the_time);
            this.view = view;




        }


    }


}
