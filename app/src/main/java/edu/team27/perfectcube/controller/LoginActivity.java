package edu.team27.perfectcube.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import edu.team27.perfectcube.R;
import edu.team27.perfectcube.model.LoginData;

public class LoginActivity extends AppCompatActivity {

    private Activity a = this;
    Button loginButton;
    Button cancelButton;
    EditText username;
    EditText password;
    TextView warning1;
    TextView warning2;

    private int badAttempts = 0;
    boolean fraud = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginData.updateUserInfo();

        loginButton = findViewById(R.id.loginButton);
        cancelButton = findViewById(R.id.cancelButton);
        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        warning1 = findViewById(R.id.warning1);
        warning2 = findViewById(R.id.warning2);

        warning1.setVisibility(View.GONE);
        warning2.setVisibility(View.GONE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tryUser = username.getText().toString();
                String tryPass = password.getText().toString();

                //ArrayList<String> users = new ArrayList<>();
                //ArrayList<String> passwords = new ArrayList<>();

                if (!fraud) {
                    if (tryUser.isEmpty() || tryPass.isEmpty()) {
                        //one or both fields are empty
                        warning2.setVisibility(View.GONE);
                        warning1.setVisibility(View.VISIBLE);
                    } else {
                        //text exists in both fields
                        warning1.setVisibility(View.GONE);
                        if (LoginData.findUser(tryUser)) {
                            if (LoginData.getPass(tryUser).equals(tryPass)) {
                                //login is successful
                                Intent intent = new Intent(a, ListActivity.class);
                                Bundle extras = new Bundle();
                                extras.putString("gender", "None");
                                extras.putString("age", "None");
                                extras.putString("name", "");
                                extras.putString("username", tryUser);

                                // add bundle to intent
                                intent.putExtras(extras);

                                // start the activity
                                startActivity(intent);
                            } else {
                                //incorrect password
                                badAttempts++;
                                if (badAttempts == 4) {
                                    //too many unsuccessful login attempts; display some new error message
                                    warning2.setVisibility(View.GONE);
                                    fraud = true;
                                } else {
                                    warning2.setVisibility(View.VISIBLE);
                                }
                            }
                        } else {
                            //incorrect username
                            warning2.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(a, WelcomeActivity.class);
                startActivity(intent);
            }

        });
    }
}
