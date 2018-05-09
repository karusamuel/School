package com.nahashon.second.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nahashon.second.R;

import java.util.ArrayList;

/**
 * Created by Nahashon on 5/9/2018.
 */

public class SubjectArrayAdapter extends BaseAdapter {
    private ArrayList<String> subjectName;
    private int[] pics;
    private Context context;
    public SubjectArrayAdapter(Context context,ArrayList<String> subjectName){
        this.subjectName=subjectName;
        this.pics=pics;


    }
    public int getCount(){
       return subjectName.size();

    }
    public View getView(int position,View viewType,ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.subjectview,parent,false);
//        ImageView img=view.findViewById(R.id.imageView2);
//        img.setImageResource(pics[position]);
        TextView name=view.findViewById(R.id.textView3);
        name.setText(subjectName.get(position));
        TextView date=view.findViewById(R.id.textView2);
        date.setText("xxxxxxxxxxxxxxxxx");
        return view;

    }
    public long getItemId(int position){

       return  position;
    }

    public Object getItem(int position){
        return null;
    }


}
