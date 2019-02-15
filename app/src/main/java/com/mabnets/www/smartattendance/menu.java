package com.mabnets.www.smartattendance;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import maes.tech.intentanim.CustomIntent;

public class menu extends AppCompatActivity {
private CardView cardlec;
private CardView cardstudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cardlec=(CardView)findViewById(R.id.lecbtn);
        cardstudent=(CardView)findViewById(R.id.studentbtn);


        cardlec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menu.this,leclogin.class));
                CustomIntent.customType(menu.this,"left-to-right");
            }
        });
        cardstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menu.this,studentlogin.class));
                CustomIntent.customType(menu.this,"left-to-right");
            }
        });

    }
}
