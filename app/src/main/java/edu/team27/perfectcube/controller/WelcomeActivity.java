package edu.team27.perfectcube.controller;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import edu.team27.perfectcube.R;
import edu.team27.perfectcube.model.LoginData;
import edu.team27.perfectcube.model.ShelterDatabase;
import edu.team27.perfectcube.model.ShelterInfo;
import edu.team27.perfectcube.model.UserDatabase;

public class WelcomeActivity extends AppCompatActivity {

    private Activity a = this;
    //public LoginData ld = new LoginData(WelcomeActivity.this);
    private static UserDatabase db;
    private static ShelterDatabase sdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        db = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "user-database").allowMainThreadQueries()
                .build();
        sdb = Room.databaseBuilder(getApplicationContext(),
                ShelterDatabase.class, "shelter-database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
        //From what I read, it was a bad idea to use the getInstance method,
        //and that it's a better idea to create one instance and use it everywhere,
        //so I created the database in the Welcome Activity (at startup)

        if (ShelterInfo.needsToRead()) {
            InputStream inputStream = getResources().openRawResource(R.raw.data);
            CsvFileReader csvFile = new CsvFileReader(inputStream);
            List<ShelterInfo> rawShelterList = csvFile.read();
            Log.d("Shelters::", rawShelterList.toString());
            ShelterDatabase sdb = WelcomeActivity.getSdb();
            int length = rawShelterList.size();
            for (int i = 0; i < length; i++) {
                sdb.shelterDao().insertShelters(rawShelterList.get(i));
            }
            WelcomeActivity.setSdb(sdb);
        }

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

    public static UserDatabase getDb() {
        return db;
    }

    public static void setDb(UserDatabase database) {
        db = database;
    }

    public static ShelterDatabase getSdb()  { return  sdb; }

    public static void setSdb(ShelterDatabase database) { sdb = database; }
}
