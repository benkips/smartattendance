package com.mabnets.www.smartattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import maes.tech.intentanim.CustomIntent;

public class launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launcher);
        getSupportActionBar().hide();



        logo culogoo=new logo();
        culogoo.start();
    }
    private class logo extends Thread{
        @Override
        public void run() {
            try {
                sleep(1000*5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(launcher.this,menu.class));
            launcher.this.finish();
        }
    }
    @Override
    public void finish() {
        super.finish();
        CustomIntent.customType(this,"right-to-left");
    }
}
