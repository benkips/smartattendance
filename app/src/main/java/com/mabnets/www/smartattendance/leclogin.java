package com.mabnets.www.smartattendance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class leclogin extends AppCompatActivity {
    private EditText lecidno;
    private EditText lecjobno;
    private Button lecbtnlogin;
    private Mycommand mycommand;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leclogin);

        lecidno=(EditText)findViewById(R.id.lecidno);
        lecjobno=(EditText)findViewById(R.id.lecjobno);
        lecbtnlogin=(Button)findViewById(R.id.lecbtnlogin);
        mycommand=new Mycommand(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("processing..");

        lecbtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String lecidnoo=lecidno.getText().toString().trim();
             String lecijobno=lecjobno.getText().toString().trim();
            loginvalidation(lecidnoo,lecijobno);
            }
        });
    }
    private void loginvalidation(final String i, final String j){
        if(i.isEmpty()){
            lecidno.setError("idnumber is invalid");
            lecidno.requestFocus();
            return;
        }else if(j.isEmpty()){
            lecjobno.setError("job number is invalid");
            lecjobno.requestFocus();
            return;
        }else{
            if(i.length()<6){
                lecidno.setError("idnumber must be above 6 characters");
                lecidno.requestFocus();
                return;
            }else{
                String url="http://shanice.mabnets.com/loginlec.php";
                StringRequest str=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("success")){
                            progressDialog.dismiss();
                            Toast.makeText(leclogin.this, response, Toast.LENGTH_SHORT).show();
                            Bundle bundle=new Bundle();
                            bundle.putString("lecid", i);
                            Intent intent=new Intent(leclogin.this,Lecmain.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            CustomIntent.customType(leclogin.this,"left-to-right");
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(leclogin.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof TimeoutError) {
                            progressDialog.dismiss();
                            Toast.makeText(leclogin.this, "error time out ", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NoConnectionError) {
                            progressDialog.dismiss();
                            Toast.makeText(leclogin.this, "error no connection", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NetworkError) {
                            progressDialog.dismiss();
                            Toast.makeText(leclogin.this, "error network error", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof AuthFailureError) {
                            progressDialog.dismiss();
                            Toast.makeText(leclogin.this, "errorin Authentication", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError) {
                            progressDialog.dismiss();
                            Toast.makeText(leclogin.this, "error while parsing", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ServerError) {
                            progressDialog.dismiss();
                            Toast.makeText(leclogin.this, "error  in server", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ClientError) {
                            progressDialog.dismiss();
                            Toast.makeText(leclogin.this, "error with Client", Toast.LENGTH_SHORT).show();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(leclogin.this, "error while loading", Toast.LENGTH_SHORT).show();
                        }
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> param=new HashMap<>();
                        param.put("jobno",j);
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
