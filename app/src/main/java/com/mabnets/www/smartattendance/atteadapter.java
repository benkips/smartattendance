package com.mabnets.www.smartattendance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import maes.tech.intentanim.CustomIntent;

public class atteadapter extends RecyclerView.Adapter<atteadapter.attendanceholder>{
    private Context Context;
    private ArrayList attenitems;

    public atteadapter(android.content.Context context, ArrayList attenitem) {
        Context = context;
        this.attenitems = attenitem;
    }

    @NonNull
    @Override
    public attendanceholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflator=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflator.inflate(R.layout.lecrecordsinf,viewGroup,false);
        atteadapter.attendanceholder at= new atteadapter.attendanceholder(view);
        return at;
    }

    @Override
    public void onBindViewHolder(@NonNull attendanceholder attendanceholder, int i) {
        final attendance attendance=(attendance)attenitems.get(i);
        attendanceholder.tvu.setText(attendance.unit);
        attendanceholder.tvs.setText(attendance.no );
        attendanceholder.cvrecod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("unitz",attendance.unit);
                Intent intent=new Intent(Context,indrecords.class);
                intent.putExtras(bundle);
                Context.startActivity(intent);
                CustomIntent.customType(Context,"right-to-left");
            }
        });

    }

    @Override
    public int getItemCount() {
        if(attenitems!=null){
            return attenitems.size();
        }
        return 0;
    }

    public static  class attendanceholder extends RecyclerView.ViewHolder{
        private TextView tvu;
        private TextView tvs;
         private  CardView cvrecod;
        public attendanceholder(@NonNull View itemView) {
            super(itemView);
            tvu=(TextView)itemView.findViewById(R.id.tvlecunit);
            tvs=(TextView)itemView.findViewById(R.id.tvs);
            cvrecod=(CardView)itemView.findViewById(R.id.cvrecordz);
        }
    }
}
