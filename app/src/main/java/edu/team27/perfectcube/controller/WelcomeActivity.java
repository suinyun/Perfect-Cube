package edu.team27.perfectcube.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.team27.perfectcube.R;
import edu.team27.perfectcube.model.LoginData;
import edu.team27.perfectcube.model.UserDatabase;

public class WelcomeActivity extends AppCompatActivity {

    private Activity a = this;
    //public LoginData ld = new LoginData(WelcomeActivity.this);
    private UserDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        db = UserDatabase.getDatabase(this.getApplicationContext());

        //LoginData.setContext(WelcomeActivity.this); //I couldn't figure out a different way to pass the context to LoginData successfully.
        //I'm not convinced that passing a context is necessary, but it's what Brooklyn showed me, so I tried to follow it.

        Button loginButton = (Button) findViewById(R.id.loginButton);
        Button registerButton = (Button) findViewById(R.id.registerButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(a, LoginActivity.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(a, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
