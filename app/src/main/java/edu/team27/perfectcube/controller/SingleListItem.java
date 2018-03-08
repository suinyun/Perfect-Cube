package edu.team27.perfectcube.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import edu.team27.perfectcube.R;

/**
 * Created by emmad on 3/8/2018.
 */

public class SingleListItem extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.single_list_item_view);

        TextView txtsn = (TextView) findViewById(R.id.shelter_name);
        TextView txtg = (TextView) findViewById(R.id.gender);
        TextView txtc = (TextView) findViewById(R.id.capacity);
        TextView txta = (TextView) findViewById(R.id.address);
        TextView txtpn = (TextView) findViewById(R.id.phone_number);

        Intent i = getIntent();

        // getting attached intent data
        String name = i.getStringExtra("Name");
        String capacity = i.getStringExtra("Capacity");
        String gender = i.getStringExtra("Demographic Restrictions");
        String address = i.getStringExtra("Address");
        String phone = i.getStringExtra("Phone Number");

        // displaying selected product name
        txtsn.setText(name);
        txtc.setText(capacity);
        txtg.setText(gender);
        txta.setText(address);
        txtpn.setText(phone);

    }
}
