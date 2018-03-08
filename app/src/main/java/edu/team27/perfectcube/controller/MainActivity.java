package edu.team27.perfectcube.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import edu.team27.perfectcube.R;
import edu.team27.perfectcube.model.ShelterInfo;
import edu.team27.perfectcube.recyclerview.Adapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter theAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<ShelterInfo> input = readCSV();

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        theAdapter = new Adapter(input);
        recyclerView.setAdapter(theAdapter);

    }

    private List<ShelterInfo> readCSV() {
        List<ShelterInfo> data = new ArrayList<>();

        try {
            InputStreamReader inputStream = new InputStreamReader(getAssets()
                    .open("homeless_shelter_database.csv"));
            BufferedReader bufferedReader = new BufferedReader(inputStream);

            String line;
            bufferedReader.readLine();
            line = bufferedReader.readLine();
            int index = 0;
            while (line != null) {
                String[] lines = line.split(",");
                data.add(index, new ShelterInfo(lines[1],
                        (lines[2]), lines[3], lines[6], lines[8]));
            }
            bufferedReader.close();
            theAdapter.setShelters(data);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return data;
    }

}
