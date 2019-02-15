package com.mabnets.www.smartattendance;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class indadapter extends RecyclerView.Adapter<indadapter.indholder> {
    private Context context;
    private ArrayList indlists;

    public indadapter(Context context, ArrayList indlist) {
        this.context = context;
        this.indlists = indlist;
    }
    public void updateData( ArrayList indlist) {
        this.indlists = indlist;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public indholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflator=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflator.inflate(R.layout.individualrec,viewGroup,false);
        indadapter.indholder in= new indadapter.indholder(view);
        return in;
    }

    @Override
    public void onBindViewHolder(@NonNull indholder indholder, int i) {
        final innnrecord innnrecord=(innnrecord)indlists.get(i);
        indholder.tvreg.setText(innnrecord.reg);
    }

    @Override
    public int getItemCount() {
        if(indlists!=null){
            return indlists.size();
        }
        return 0;
    }

    public static class  indholder extends RecyclerView.ViewHolder{
        private TextView tvreg;
        public indholder(@NonNull View itemView) {
            super(itemView);
            tvreg=(TextView)itemView.findViewById(R.id.tvregz);
        }
    }
}
