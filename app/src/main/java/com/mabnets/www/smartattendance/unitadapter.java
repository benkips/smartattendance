package com.mabnets.www.smartattendance;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class unitadapter extends RecyclerView.Adapter<unitadapter.unitHolder> {
    private Context context;
    private ArrayList unititems;
    private String studeid;

    public unitadapter(Context context, ArrayList unititem,String stuiid) {
        this.context = context;
        this.unititems = unititem;
        this.studeid=stuiid;
    }

    @NonNull
    @Override
    public unitHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflator=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflator.inflate(R.layout.stuinf,viewGroup,false);
        unitadapter.unitHolder un= new unitadapter.unitHolder(view);
        return  un;
    }

    @Override
    public void onBindViewHolder(@NonNull unitHolder unitHolder, int i) {
        final units units=(units)unititems.get(i);
        unitHolder.tvunit.setText(units.subject);
        unitHolder.tvecii.setText(units.lecturer);
        unitHolder.cvstude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager manager=((AppCompatActivity)context).getSupportFragmentManager();
                Bundle bundle=new Bundle();
                bundle.putString("lec",units.lecturer);
                bundle.putString("sub",units.subject);
                bundle.putString("stuidd",studeid);
                Fragment fragment=new Finger_printanime();
                fragment.setArguments(bundle);
                manager.beginTransaction().replace(R.id.framelayout,fragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(unititems!=null){
            return unititems.size();
        }
        return 0;
    }

    public  static  class unitHolder extends RecyclerView.ViewHolder{
        private TextView tvunit;
        private TextView tvecii;
        private CardView cvstude;
        public unitHolder(@NonNull View itemView) {
            super(itemView);
            tvunit=(TextView)itemView.findViewById(R.id.tvunit);
            tvecii=(TextView)itemView.findViewById(R.id.tvlecz);
            cvstude=(CardView)itemView.findViewById(R.id.cvstude);
        }
    }
}
