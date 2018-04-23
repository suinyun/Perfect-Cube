package edu.team27.perfectCube.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import edu.team27.perfectCube.R;

import static edu.team27.perfectCube.controller.LoginActivity.sc;

/**
 * does stuff so a user can search/filter
 *
 * */
public class SearchActivity extends Activity {

    private EditText simpleEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Spinner genderSpinner = findViewById(R.id.gender_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender_spinner_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        genderSpinner.setAdapter(genderAdapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object p = adapterView.getItemAtPosition(position);
                sc.setFilterGender(p.toString());


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sc.setFilterGender("");

            }
        });


        Spinner ageSpinner = findViewById(R.id.age_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> ageAdapter = ArrayAdapter.createFromResource(this,
                R.array.age_spinner_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        ageSpinner.setAdapter(ageAdapter);
        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object p = adapterView.getItemAtPosition(position);
                sc.setFilterAge(p.toString());


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sc.setFilterAge("");

            }
        });

        Button searchButton = findViewById(R.id.execute_search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                simpleEditText = findViewById(R.id.nameEntry);
                Editable set = simpleEditText.getText();
                String setString = set.toString();
                if (setString.isEmpty()) {
                    sc.setFilterName("");
                } else {
                    sc.setFilterName(set.toString());
                }

                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                Bundle extras = new Bundle();
                extras.putString("gender", sc.getFilterGender());
                extras.putString("age", sc.getFilterAge());
                extras.putString("name", sc.getFilterName());

                // add bundle to intent
                intent.putExtras(extras);
                startActivity(intent);

            }
        });
    }

}
