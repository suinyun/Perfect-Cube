package edu.team27.perfectcube.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import edu.team27.perfectcube.R;
import edu.team27.perfectcube.model.LoginData;
import edu.team27.perfectcube.model.User;
import edu.team27.perfectcube.model.UserDatabase;
import edu.team27.perfectcube.model.UserType;

public class RegisterActivity extends AppCompatActivity {

    private Activity a = this;
    Button submitButton;
    EditText username;
    EditText password;
    TextView warning;
    Spinner userTypeSpinner;
    int reservationNumber;
    String reservationLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        submitButton = findViewById(R.id.submitButton);
        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        warning = findViewById(R.id.warning);
        userTypeSpinner = findViewById(R.id.spinner);
        /*
          Set up the adapter to display the allowable majors in the spinner
         */
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, UserType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);

        warning.setVisibility(View.GONE);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<User> users = LoginData.getUserInfo();
                //ArrayList<String> passwords = LoginData.getPasswords();



                if(users.contains(username.getText().toString())) {
                    warning.setVisibility(View.VISIBLE);
                } else {
                    User newUser = new User(username.getText().toString(),
                            password.getText().toString(),
                            UserType.USER);
                    users.add(newUser);
                    //passwords.add(password.getText().toString());
                    LoginData.setUserInfo(users);
                    //LoginData.setPasswords(passwords);
                    //reservationNumber = 0;
                    //reservationLocation = "";
                    UserDatabase db = WelcomeActivity.getDb();
                    db.userDao().insertUsers(newUser);
                    WelcomeActivity.setDb(db);
                    Intent intent = new Intent(a, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
