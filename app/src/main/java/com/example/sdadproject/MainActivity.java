package com.example.sdadproject;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class MainActivity extends AppCompatActivity {


    private CallbackManager callbackManager;
    private LoginButton loginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, ForegroundS.class);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            startForegroundService(intent);

        }else{
            startService(intent);
        }




        loginButton = findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("Demo", "Login Successful");
            }

            @Override
            public void onCancel() {
                Log.d("Demo", "Login Failed");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("Demo", "Login Error");
            }
        });
        
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        openPage1();
    }
    public void openPage1(){

        Intent intent = new Intent(this, Page1.class);
        startActivity(intent);
    }
}