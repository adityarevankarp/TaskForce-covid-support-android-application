package com.revankars.taskforce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class commadapter extends RecyclerView.Adapter<commadapter.ComViewHolder>{


    Context context;
    ArrayList<communitymodel> list;

    public commadapter(Context context, ArrayList<communitymodel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ComViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.singlerowcommunityname,parent,false);
        return new ComViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ComViewHolder holder, int position) {

        communitymodel CommunityModel = list.get(position);
        holder.communitytext.setText(CommunityModel.getCommunityName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ComViewHolder extends RecyclerView.ViewHolder{
        TextView communitytext;
        public ComViewHolder(@NonNull View itemView) {
            super(itemView);
            communitytext = (TextView)itemView.findViewById(R.id.Communitynametextview);


        }
    }
}

