package edu.team27.perfectCube.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.team27.perfectCube.R;
import edu.team27.perfectCube.model.LoginData;
import edu.team27.perfectCube.model.SearchCriteria;

/**
 * creates the login screen and what each
 * field does
 *
 * */
public class LoginActivity extends AppCompatActivity {

    private final Activity a = this;
    private EditText username;
    private EditText password;
    private TextView warning1;
    private TextView warning2;

    private int badAttempts = 0;
    private boolean fraud = false;

    static SearchCriteria sc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginData.updateUserInfo();

        Button loginButton = findViewById(R.id.loginButton);
        Button cancelButton = findViewById(R.id.cancelButton);
        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        warning1 = findViewById(R.id.warning1);
        warning2 = findViewById(R.id.warning2);

        warning1.setVisibility(View.GONE);
        warning2.setVisibility(View.GONE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Editable u = username.getText();
                Editable p = password.getText();

                String tryUser = u.toString();
                String tryPass = p.toString();

                if (!fraud) {
                    if (tryUser.isEmpty() || tryPass.isEmpty()) {
                        //one or both fields are empty
                        warning2.setVisibility(View.GONE);
                        warning1.setVisibility(View.VISIBLE);
                    } else {
                        //text exists in both fields
                        warning1.setVisibility(View.GONE);
                        if (LoginData.findUser(tryUser)) {
                            String pass = LoginData.getPass(tryUser);
                            if (pass.equals(tryPass)) {
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
                                    //too many unsuccessful login attempts; display some
                                    // new error message
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

            sc = new SearchCriteria("", "", "");

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
