package edu.team27.perfectcube.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import edu.team27.perfectcube.R;

/**
 * Created by emmad on 3/11/2018.
 */

public class SearchActivity extends Activity {

    Spinner genderSpinner;
    Spinner ageSpinner;
    Button searchButton;
    EditText simpleEditText;

    public String filterGender;
    public String filterAge;
    public String filterName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        genderSpinner = findViewById(R.id.gender_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> genderadapter = ArrayAdapter.createFromResource(this,
                R.array.gender_spinner_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        genderadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        genderSpinner.setAdapter(genderadapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                filterGender = adapterView.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                filterGender = "None";

            }
        });


        ageSpinner = findViewById(R.id.age_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> ageadapter = ArrayAdapter.createFromResource(this,
                R.array.age_spinner_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        ageadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        ageSpinner.setAdapter(ageadapter);
        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                filterAge = adapterView.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                filterAge = "None";

            }
        });

        searchButton = findViewById(R.id.execute_search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                simpleEditText = findViewById(R.id.nameEntry);
                if (simpleEditText.getText().toString().isEmpty()) {
                    filterName = "";
                } else {
                    filterName = simpleEditText.getText().toString();
                }

                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                Bundle extras = new Bundle();
                extras.putString("gender", filterGender);
                extras.putString("age", filterAge);
                extras.putString("name", filterName);

                // add bundle to intent
                intent.putExtras(extras);
                startActivity(intent);

            }
        });
    }

}
