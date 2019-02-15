package com.mabnets.www.smartattendance;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.M)
public class Fingerprinthandler extends FingerprintManager.AuthenticationCallback {

    private Context context;


    public Fingerprinthandler(Context context) {
        this.context=context;

    }
    public void startAuth(FingerprintManager fingerprintManager,FingerprintManager.CryptoObject cryptoObject){
        CancellationSignal cancellationSignal=new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject,cancellationSignal,0,this,null);
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        this.update("There was an Auth error "+errString,false);
    }

    @Override
    public void onAuthenticationFailed() {
        this.update("Auth Failed",false);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        this.update("Error:"+helpString,false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("Correct match...please wait",true);

    }

    private void update(String s, boolean b) {

        TextView textView= (TextView)((Activity)context).findViewById(R.id.status);
        ImageView imageView=(ImageView) ((Activity)context).findViewById(R.id.fiv);


        textView.setText(s);

        if(b== false){
            textView.setTextColor(ContextCompat.getColor(context,R.color.colorAccent));

        }else{
            textView.setTextColor(ContextCompat.getColor(context,R.color.colorPrimary));
            imageView.setImageResource(R.drawable.ic_done_black_24dp);
        }
    }
}
