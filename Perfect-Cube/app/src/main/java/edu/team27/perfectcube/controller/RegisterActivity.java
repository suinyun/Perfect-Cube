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
import edu.team27.perfectcube.model.User;

public class RegisterActivity extends AppCompatActivity {

    private Activity a = this;
    Button submitButton;
    EditText username;
    EditText password;
    TextView warning;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        submitButton = findViewById(R.id.submitButton);
        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        warning = findViewById(R.id.warning);
        cancelButton = findViewById(R.id.warning);


        warning.setVisibility(View.GONE);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<User> users = LoginData.getUsers();
                boolean userNameTaken = false;
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUsername().equals(username.getText()
                        .toString())) {
                        userNameTaken = true;
                    }
                }
                if(userNameTaken) {
                    warning.setVisibility(View.VISIBLE);
                } else {
                    //users.add(username.getText().toString());
                    //passwords.add(password.getText().toString());
                    User newUser = new User(username.getText().toString(),
                            password.getText().toString());
                    users.add(newUser);
                    LoginData.setUsers(users);
                    //LoginData.setPasswords(passwords);
                    Intent intent = new Intent(a, FakeAppActivity.class);
                    startActivity(intent);
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
