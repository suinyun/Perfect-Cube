package edu.team27.perfectcube.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import edu.team27.perfectcube.model.UserTypeConverter;

public class RegisterActivity extends AppCompatActivity {

    private Activity a = this;
    Button submitButton;
    EditText username;
    EditText password;
    TextView warning;
    TextView warning1;
    TextView warning2;
    Spinner spinner;

    public String spinnerType;
    private UserDatabase db; //I added the userbase intialized in the WelcomeActivity to RegisterActivity.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = UserDatabase.getDatabase(this.getApplicationContext());

        submitButton = findViewById(R.id.submitButton);
        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        warning = findViewById(R.id.warning);
        warning1 = findViewById(R.id.warning1);
        warning2 = findViewById(R.id.warning2);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, UserType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                spinnerType = adapterView.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinnerType = "USER";

            }
        });

        warning.setVisibility(View.GONE);
        warning1.setVisibility(View.GONE);
        warning2.setVisibility(View.GONE);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    //one or both fields are empty
                    warning2.setVisibility(View.GONE);
                    warning1.setVisibility(View.VISIBLE);
                } else {
                    if (LoginData.findUser(username.getText().toString())) {
                        //username is taken
                        warning.setVisibility(View.VISIBLE);
                    } else {
                        //I changed the last parameter in the addUser call. We can expect all registering members to be USERS, right?
                        LoginData.addUser(username.getText().toString(),password.getText().toString(),UserType.USER);
                        db.userDao().insertUsers(new User(username.getText().toString(), password.getText().toString(),
                                UserType.USER)); //This line was added to add the user to the database
                        Intent intent = new Intent(a, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}
