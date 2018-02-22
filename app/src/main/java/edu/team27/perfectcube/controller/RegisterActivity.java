package edu.team27.perfectcube.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import edu.team27.perfectcube.R;
import edu.team27.perfectcube.model.LoginData;
import edu.team27.perfectcube.model.User;
import edu.team27.perfectcube.model.UserType;

public class RegisterActivity extends AppCompatActivity {

    private Activity a = this;
    Button submitButton;
    EditText username;
    EditText password;
    TextView warning;
    CheckedTextView userTypeCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        submitButton = findViewById(R.id.submitButton);
        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        warning = findViewById(R.id.warning);
        userTypeCheckBox = findViewById(R.id.checkBox);

        warning.setVisibility(View.GONE);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> users = LoginData.getUsers();
                ArrayList<String> passwords = LoginData.getPasswords();
                

                UserType usertype;

                if (userTypeCheckBox.isChecked()) {
                    usertype = UserType.ADMINISTRATOR;
                }


                if(users.contains(username.getText().toString())) {
                    warning.setVisibility(View.VISIBLE);
                } else {
                    users.add(username.getText().toString());
                    passwords.add(password.getText().toString());
                    LoginData.setUsers(users);
                    LoginData.setPasswords(passwords);
                    Intent intent = new Intent(a, FakeAppActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
