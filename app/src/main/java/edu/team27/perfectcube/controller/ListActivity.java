package edu.team27.perfectcube.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import edu.team27.perfectcube.R;
import edu.team27.perfectcube.model.ShelterInfo;

public class ListActivity extends Activity {

    private ListView lv;
    private ListAdapter adapter;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_list);

        lv = findViewById(R.id.list_view);

        //Reading the CSV
        InputStream inputStream = getResources().openRawResource(R.raw.data);
        CsvFileReader csvFile = new CsvFileReader(inputStream);
        final List<ShelterInfo> shelterlist = csvFile.read();

        //adding shelter names to the array list
        List<String> namelist = new ArrayList<>();
        for (ShelterInfo shelter: shelterlist) {
            namelist.add(shelter.getShelterName());

        }

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        adapter = new ListAdapter(ListActivity.this, namelist);
        if (namelist != null) {
            lv.setAdapter(adapter);
        }

        //single click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected item
                ShelterInfo selected = shelterlist.get(position);

                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), SingleListItem.class);

                // sending data to new activity
                i.putExtra("Name", "\n" + selected.getShelterName() + "\n");
                i.putExtra("Capacity", "Vacancies: " + selected.getCapacity());
                i.putExtra("Demographic Restrictions", "Accepting: " + selected.getGender());
                i.putExtra("Address", selected.getAddress());
                i.putExtra("Phone Number", selected.getPhoneNumber());

                startActivity(i);
            }
        });


    }
}
