package edu.team27.perfectCube.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.team27.perfectCube.R;
import edu.team27.perfectCube.model.ShelterDatabase;
import edu.team27.perfectCube.model.ShelterInfo;

import static edu.team27.perfectCube.controller.LoginActivity.sc;

/**
 * organizes and presents the list of shelters
 *
 * */
public class ListActivity extends Activity {

    static ShelterInfo selected;

    private String filterGender;
    private String filterAge;
    private String filterName;

    /**
     * begins the activity
     * @param saveInstanceState the Bundle
     * */
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_list);

        ListView lv = findViewById(R.id.list_view);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        filterGender = sc.getFilterGender();
        filterAge = sc.getFilterAge();
        filterName = sc.getFilterName();
        final String username = bundle.getString("username");

        List<ShelterInfo> filteredList = new ArrayList<>();
        ShelterDatabase sdb = WelcomeActivity.getSdb();
        List<ShelterInfo> rawShelterList = new ArrayList<>();
        ShelterInfo[] sdbList = sdb.shelterDao().loadAllShelters();
        rawShelterList.addAll(Arrays.asList(sdbList));


        for (int i = 0; i < rawShelterList.size(); i++) {

            boolean addShelter = true;
            ShelterInfo s = rawShelterList.get(i);
            String g = s.getGender();
            String n = s.getShelterName();

            if ("Male".equals(filterGender)) {
                if (g.contains("Women")) {
                    addShelter = false;
                }
            } else if ("Female".equals(filterGender)) {
                if (g.contains("Male")) {
                    addShelter = false;
                }
            } else {
                addShelter = true;
            }

            if ("Families with Newborns".equals(filterAge)) {
                if (!g.contains("ewborn") && !g.contains("nyone")) {
                    addShelter = false;
                }
            } else if ("Children".equals(filterAge)) {
                if (!g.contains("hildren") && !g.contains("nyone")) {
                    addShelter = false;
                }
            } else if ("Young Adults".equals(filterAge)) {
                if (!g.contains("oung adults") && !g.contains("nyone")) {
                    addShelter = false;
                }
            } else {
                addShelter = true;
            }

            if (!"".equals(filterName)) {
                if (!n.contains(filterName)) {
                    addShelter = false;
                } else {
                    addShelter = true;
                }
            }

            if (addShelter) {
                filteredList.add(rawShelterList.get(i));
            }
        }

        final List<ShelterInfo> shelterList = filteredList;

        //adding shelter names to the array list
        List<String> namelist = new ArrayList<>();
        for (ShelterInfo shelter: filteredList) {
            namelist.add(shelter.getShelterName());

        }

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        android.widget.ListAdapter adapter = new ListAdapter(ListActivity.this, namelist);
        lv.setAdapter(adapter);

        //single click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected item
                selected = shelterList.get(position);

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
                extras.putString("gender", filterGender);
                extras.putString("age", filterAge);
                extras.putString("name", filterName);
                extras.putString("username", username);

                // add bundle to intent
                i.putExtras(extras);

                startActivity(i);
            }
        });

        Button logoutButton = findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(intent);

            }
        });

        Button searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);

            }
        });

        Button mapButton = findViewById(R.id.mapButton);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);

            }
        });
    }
}
