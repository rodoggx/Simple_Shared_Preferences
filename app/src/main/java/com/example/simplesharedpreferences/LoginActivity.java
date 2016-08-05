package com.example.simplesharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivityTAG_";
    private TextView loginUser;
    private TextView MainPW;
    private EditText loginEmail;
    private EditText loginPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        loginUser = (TextView) findViewById(R.id.loginUser);
        String userName = getIntent().getStringExtra("mainUser");
        loginUser.setText(userName);

        MainPW = (TextView) findViewById(R.id.loginPW);
        String userPW = getIntent().getStringExtra("mainPW");
    }

}
