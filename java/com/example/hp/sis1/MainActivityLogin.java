package com.example.hp.sis1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivityLogin extends AppCompatActivity {
    EditText UsernameEt, PasswordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        UsernameEt = (EditText) findViewById(R.id.etUserName);
        PasswordEt = (EditText) findViewById(R.id.etPassword);
    }

    public void OnLogin(View view) {
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";
//        checkDataEntered();
        checkUsername();
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkUsername() {
        boolean isValid = true;
        if (isEmpty(UsernameEt)) {
            UsernameEt.setError("You must enter username to login!");
            isValid = false;
        }
        if (isValid) {
            String username = UsernameEt.getText().toString();
            String password = PasswordEt.getText().toString();
            if (username.equals("username") && password.equals("password")) {
                //everything checked we open new activity


                Toast t = Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT);
                t.show();
                Intent i = new Intent(this, Dashboard.class);
                startActivity(i);
//                //we close this activity
                this.finish();
            } else {
                Toast t = Toast.makeText(this, "Wrong username or password!", Toast.LENGTH_SHORT);
                t.show();
                this.finish();
            }
        }


    }
}

