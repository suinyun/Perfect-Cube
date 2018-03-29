package edu.team27.perfectcube.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.team27.perfectcube.R;
import edu.team27.perfectcube.model.ShelterInfo;
import edu.team27.perfectcube.model.User;

/**
 * Created by emmad on 3/8/2018.
 */

public class SingleListItem extends Activity {

    Button reservationbutton;
    Button cancelbutton;
    EditText bedCount;
    User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.single_list_item_view);

        final ShelterInfo shelter = ListActivity.selected;

        TextView txtsn = (TextView) findViewById(R.id.shelter_name);
        TextView txtg = (TextView) findViewById(R.id.gender);
        final TextView txtc = (TextView) findViewById(R.id.capacity);
        TextView txta = (TextView) findViewById(R.id.address);
        TextView txtpn = (TextView) findViewById(R.id.phone_number);
        reservationbutton = findViewById(R.id.reservationbutton);
        cancelbutton = findViewById(R.id.cancelbutton);
        bedCount = findViewById(R.id.bedCount);

        Intent i = getIntent();

        // getting attached intent data
        final String name = i.getStringExtra("Name");
        final int capacity = i.getIntExtra("Capacity", -1);
        String gender = i.getStringExtra("Demographic Restrictions");
        String address = i.getStringExtra("Address");
        String phone = i.getStringExtra("Phone Number");

        // displaying selected product name
        txtsn.setText(name);
        txtc.setText("Vacancies: " + String.valueOf(capacity));
        txtg.setText(gender);
        txta.setText(address);
        txtpn.setText(phone);


        reservationbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int bedCountNum;
                try {
                    bedCountNum = Integer.parseInt(bedCount.getText().toString());
                } catch (Exception e) {
                    bedCount.setError("not an int");
                    Toast.makeText(getBaseContext(), "not an int", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (bedCountNum < 0) {
                    bedCount.setError("less than 0");
                    Toast.makeText(getBaseContext(), "less than 0", Toast.LENGTH_SHORT).show();
                    return;
                } else if (bedCountNum > capacity) {
                    bedCount.setError("greater than capacity");
                    Toast.makeText(getBaseContext(), "greater than capacity", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //3 things: make sure that user cant reserve the same shelter twice
                    // you want to decrease the amount of beds
                    // change the textview of the updated capacity

                    // updating user info
                    user.setReservationNumber(bedCountNum);
                    user.setReservationLocation(name);

                    //update shelter info
                    shelter.setCapacity(capacity - bedCountNum);

                    //update display
                    txtc.setText("Vacancies: " + String.valueOf(
                            shelter.getCapacity()));
                }


            }
        });


        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user.getReservationLocation() == shelter.getShelterName()) {
                    shelter.setCapacity(capacity + user.getReservationNumber());
                    txtc.setText("Vacancies: " + String.valueOf(shelter.getCapacity()));
                    user.setReservationNumber(0);
                    user.setReservationLocation("");
                }
            }
        });

    }
}
