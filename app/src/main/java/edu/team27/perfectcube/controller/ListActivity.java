package edu.team27.perfectcube.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.team27.perfectcube.R;
import edu.team27.perfectcube.model.ShelterDatabase;
import edu.team27.perfectcube.model.ShelterInfo;

public class ListActivity extends Activity {

    private ListView lv;
    private ListAdapter adapter;
    Button logoutButton;
    Button searchButton;
    static ShelterInfo selected;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_list);

        lv = findViewById(R.id.list_view);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String filterG = bundle.getString("gender");
        final String filterA = bundle.getString("age");
        final String filterN = bundle.getString("name");
        final String username = bundle.getString("username");

        List<ShelterInfo> filteredList = new ArrayList<>();
        ShelterDatabase sdb = WelcomeActivity.getSdb();
        //List<ShelterInfo> rawShelterList = sdb.shelterDao().loadAllShelters();
        ArrayList<ShelterInfo> rawShelterList = new ArrayList<>();
        rawShelterList.addAll(Arrays.asList(sdb.shelterDao().loadAllShelters()));


        for (int i = 0; i < rawShelterList.size(); i++) {

            boolean addShelter = true;

            if (filterG.equals("Male")) {
                if (rawShelterList.get(i).getGender().contains("Women")) {
                    addShelter = false;
                }
            } else if (filterG.equals("Female")) {
                if (rawShelterList.get(i).getGender().contains("Men")) {
                    addShelter = false;
                }
            }

            if (filterA.equals("Families with Newborns")) {
                if (!rawShelterList.get(i).getGender().contains("ewborn") && !rawShelterList.get(i).getGender().contains("nyone")) {
                    addShelter = false;
                }
            } else if (filterA.equals("Children")) {
                if (!rawShelterList.get(i).getGender().contains("hildren") && !rawShelterList.get(i).getGender().contains("nyone")) {
                    addShelter = false;
                }
            } else if (filterA.equals("Young Adults")) {
                if (!rawShelterList.get(i).getGender().contains("Young adults") && !rawShelterList.get(i).getGender().contains("nyone")) {
                    addShelter = false;
                }
            }

            if (!filterN.isEmpty()) {
                if (!rawShelterList.get(i).getShelterName().contains(filterN)) {
                    addShelter = false;
                }
            }

            if (addShelter) {
                filteredList.add(rawShelterList.get(i));
            }
        }

        final List<ShelterInfo> shelterlist = filteredList;

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
                selected = shelterlist.get(position);

                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), SingleListItem.class);

                // sending data to new activity
                i.putExtra("Name", "\n" + selected.getShelterName() + "\n");
                i.putExtra("Capacity", selected.getCapacity());
                i.putExtra("Demographic Restrictions", "Accepting: " + selected.getGender());
                i.putExtra("Address", selected.getAddress());
                i.putExtra("Phone Number", selected.getPhoneNumber() + "\n\n");
                //i.putExtra("username", username);

                Bundle extras = new Bundle();
                extras.putString("gender", filterG);
                extras.putString("age", filterA);
                extras.putString("name", filterN);
                extras.putString("username", username);

                // add bundle to intent
                i.putExtras(extras);

                startActivity(i);
            }
        });

        logoutButton = findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(intent);

            }
        });

        searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);

            }
        });


    }
}
