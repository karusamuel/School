package com.nahashon.second.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nahashon.second.R;
import com.nahashon.second.adapter.LevelClass;

import java.util.List;


public class GridAdapter extends BaseAdapter {
    private List<LevelClass> levels;
    public GridAdapter(List<LevelClass> levels){
        this.levels = levels;
    }

    public int getCount(){
        return levels.size();

    }

    @Override
    public Object getItem(int i) {


        return null;
    }

    public View getView(int pos, View view, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view1 =   inflater.inflate(R.layout.grid_view_layout,parent,false);

        TextView level =view1.findViewById(R.id.level_name);
        TextView description= view1.findViewById(R.id.level_description);
        ImageView imageView = view1.findViewById(R.id.imageView);
        description.setText(levels.get(pos).getDescription());
        level.setText(levels.get(pos).getName());
        Glide.with(parent.getContext()).load(levels.get(pos).getImg_link()).crossFade(1000).into(imageView);

return view1;
    }
    public long getItemId(int position){

        return position;

    }
}
