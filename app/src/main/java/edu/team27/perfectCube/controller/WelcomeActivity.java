package edu.team27.perfectCube.controller;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;
import java.util.List;

import edu.team27.perfectCube.R;
import edu.team27.perfectCube.model.ShelterDatabase;
import edu.team27.perfectCube.model.ShelterInfo;
import edu.team27.perfectCube.model.UserDatabase;

/**
 * first screen
 * */
public class WelcomeActivity extends AppCompatActivity {

    private final Activity a = this;
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
                .allowMainThreadQueries().build();
        //From what I read, it was a bad idea to use the getInstance method,
        //and that it's a better idea to create one instance and use it everywhere,
        //so I created the database in the Welcome Activity (at startup)

        if (ShelterInfo.needsToRead()) {
            Resources r = getResources();
            InputStream inputStream = r.openRawResource(R.raw.data);
            CsvFileReader csvFile = new CsvFileReader(inputStream);
            List<ShelterInfo> rawShelterList = csvFile.read();
            Log.d("APP", rawShelterList.toString());
            ShelterDatabase sdb = WelcomeActivity.getSdb();
            int length = rawShelterList.size();
            for (int i = 0; i < length; i++) {
                sdb.shelterDao().insertShelters(rawShelterList.get(i));
            }
            WelcomeActivity.setSdb(sdb);
        }

        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);

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

    /**
     * @return a database of users
     * */
    public static UserDatabase getDb() {
        return db;
    }

    /**
     * @param database a database of users
     * */
    public static void setDb(UserDatabase database) {
        db = database;
    }

    /**
     * @return a database of shelters
     * */
    public static ShelterDatabase getSdb()  { return  sdb; }

    /**
     * @param database a database of shelters
     * */
    public static void setSdb(ShelterDatabase database) { sdb = database; }
}
