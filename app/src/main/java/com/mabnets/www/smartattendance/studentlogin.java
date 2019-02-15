package com.mabnets.www.smartattendance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.ClientError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import maes.tech.intentanim.CustomIntent;

public class studentlogin extends AppCompatActivity {
    private EditText stuidno;
    private EditText stureg;
    private Button stubtnlogin;
    private Mycommand mycommand;
    private ProgressDialog  progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);

        stureg=(EditText)findViewById(R.id.streglog);
        stuidno=(EditText)findViewById(R.id.stidlog);
        stubtnlogin=(Button)findViewById(R.id.stbtnlogin);
        mycommand=new Mycommand(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("processing..");

        stubtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String studentidno= stuidno.getText().toString().trim();
               String studentregno=stureg.getText().toString().trim();
                loginvalidation(studentidno,studentregno);
            }
        });

    }
    private void loginvalidation(final String i, final String r){
        if(i.isEmpty()){
            stuidno.setError("idnumber is invalid");
            stuidno.requestFocus();
            return;
        }else if(r.isEmpty()){
            stureg.setError("Registration is invalid");
            stureg.requestFocus();
            return;
        }else{
            if(i.length()!=8){
                stuidno.setError("idnumber must be 8 characters");
                stuidno.requestFocus();
                return;
            }else{
                String url="http://shanice.mabnets.com/loginstudents.php";
                StringRequest str=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("success")){
                            progressDialog.dismiss();
                            Toast.makeText(studentlogin.this, response, Toast.LENGTH_SHORT).show();
                            Bundle bundle=new Bundle();
                            bundle.putString("studentid", i);
                            Intent intent=new Intent(studentlogin.this,Studentmain.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            CustomIntent.customType(studentlogin.this,"left-to-right");
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(studentlogin.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof TimeoutError) {
                            progressDialog.dismiss();
                            Toast.makeText(studentlogin.this, "error time out ", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NoConnectionError) {
                            progressDialog.dismiss();
                            Toast.makeText(studentlogin.this, "error no connection", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NetworkError) {
                            progressDialog.dismiss();
                            Toast.makeText(studentlogin.this, "error network error", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof AuthFailureError) {
                            progressDialog.dismiss();
                            Toast.makeText(studentlogin.this, "errorin Authentication", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError) {
                            progressDialog.dismiss();
                            Toast.makeText(studentlogin.this, "error while parsing", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ServerError) {
                            progressDialog.dismiss();
                            Toast.makeText(studentlogin.this, "error  in server", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ClientError) {
                            progressDialog.dismiss();
                            Toast.makeText(studentlogin.this, "error with Client", Toast.LENGTH_SHORT).show();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(studentlogin.this, "error while loading", Toast.LENGTH_SHORT).show();
                        }
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> param=new HashMap<>();
                        param.put("reg",r);
                        param.put("idno",i);
                        return param;
                    }
                };
                mycommand.add(str);
                progressDialog.show();
                mycommand.execute();
                mycommand.remove(str);

            }
        }
    }
    @Override
    public void finish() {
        super.finish();
        CustomIntent.customType(this,"right-to-left");
    }


}
