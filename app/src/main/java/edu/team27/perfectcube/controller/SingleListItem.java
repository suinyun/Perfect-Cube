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
import edu.team27.perfectcube.model.LoginData;
import edu.team27.perfectcube.model.ShelterDatabase;
import edu.team27.perfectcube.model.ShelterInfo;
import edu.team27.perfectcube.model.User;
import edu.team27.perfectcube.model.UserDatabase;

/**
 * Created by emmad on 3/8/2018.
 */

public class SingleListItem extends Activity {

    Button reservationbutton;
    Button cancelbutton;
    Button backbutton;
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
        backbutton = findViewById(R.id.backbutton);
        bedCount = findViewById(R.id.bedCount);

        Intent i = getIntent();

        // getting attached intent data
        final String name = i.getStringExtra("Name");
        final String capacity = i.getStringExtra("Capacity");
        String gender = i.getStringExtra("Demographic Restrictions");
        String address = i.getStringExtra("Address");
        String phone = i.getStringExtra("Phone Number");
        //String username = i.getStringExtra("username");

        Bundle bundle = i.getExtras();
        final String filterGender = bundle.getString("gender");
        final String filterAge = bundle.getString("age");
        final String filterName = bundle.getString("name");
        final String username = bundle.getString("username");

        user = LoginData.getUser(username);

        // displaying selected product name
        txtsn.setText(name);
        txtc.setText("Vacancies: " + capacity);
        txtg.setText(gender);
        txta.setText(address);
        txtpn.setText(phone);

        final ShelterDatabase sdb = WelcomeActivity.getSdb();
        final UserDatabase db = WelcomeActivity.getDb();


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
                } else if (bedCountNum == 0) {
                    Toast.makeText(getBaseContext(), "Please enter a number greater than 0", Toast.LENGTH_SHORT).show();
                } else if (bedCountNum > Integer.parseInt(capacity)) {
                    bedCount.setError("greater than capacity");
                    Toast.makeText(getBaseContext(), "greater than capacity", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!user.getReservationLocation().equals("")) {
                    String msg = "you already have a reservation at\n" + user.getReservationLocation();
                    bedCount.setError(msg);
                    Toast.makeText(getBaseContext(), "You must cancel your current reservation", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //3 things: make sure that user cant reserve the same shelter twice
                    // you want to decrease the amount of beds
                    // change the textview of the updated capacity

                    // updating user info
                    user.setReservationNumber(bedCountNum);
                    user.setReservationLocation(shelter.getShelterName());

                    //update shelter info
                    if (shelter.getShelterName().equals("Eden Village")) {
                        //32 for families, 80 singles

                    } else if (shelter.getShelterName().equals("Our House")
                            || shelter.getShelterName().equals("Hope Atlanta")) {
                        //76 family rooms
                        //22 apartments
                        String capnum = shelter.getCapacity();
                        String[] capsplit = capnum.split("\\s");
                        capnum = capsplit[0];
                        int cap = Integer.parseInt(capnum);
                        String newcap = String.valueOf(cap - bedCountNum);
                        for (int i = 1; i < capsplit.length; i++) {
                            newcap = newcap + " " + capsplit[i];
                        }
                        shelter.setCapacity(newcap);
                    } else {
                        int cap = Integer.parseInt(shelter.getCapacity());
                        String newcap = String.valueOf(cap - bedCountNum);
                        shelter.setCapacity(newcap);
                    }


                    //update display
                    String message = "Vacancies: " + String.valueOf(shelter.getCapacity());
                    txtc.setText(message);

                    //update databases
                    UserDatabase db = WelcomeActivity.getDb();
                    db.userDao().updateUsers(user);
                    WelcomeActivity.setDb(db);

                    ShelterDatabase sdb = WelcomeActivity.getSdb();
                    sdb.shelterDao().updateShelters(shelter);
                    WelcomeActivity.setSdb(sdb);
                }

            }
        });


        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    boolean a = user.getReservationLocation().equals(shelter.getShelterName());
                    if (a) {
                        shelter.setCapacity(Integer.toString(Integer.parseInt(shelter.getCapacity()) + user.getReservationNumber()));
                        String message = "Vacancies: " + String.valueOf(shelter.getCapacity());
                        txtc.setText(message);

                        ShelterDatabase sdb = WelcomeActivity.getSdb();
                        sdb.shelterDao().updateShelters(shelter);
                        WelcomeActivity.setSdb(sdb);
                    } else if (user.getReservationLocation().equals("")) {
                        Toast.makeText(getBaseContext(), "you don't have a reservation", Toast.LENGTH_SHORT).show();
                    }

                    user.setReservationNumber(0);
                    user.setReservationLocation("");

                    UserDatabase db = WelcomeActivity.getDb();
                    db.userDao().updateUsers(user);
                    WelcomeActivity.setDb(db);

                } catch (NullPointerException e) {
                    txtc.setText("You done messed up now");
                }
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //update databases to reflect changes
                sdb.shelterDao().updateShelters(shelter);
                db.userDao().updateUsers(user);

                Intent intent = new Intent(getApplicationContext(), ListActivity.class);

                //add bundle
                Bundle extras = new Bundle();
                extras.putString("gender", filterGender );
                extras.putString("age", filterAge);
                extras.putString("name", filterName);
                extras.putString("username", username);

                // add bundle to intent
                intent.putExtras(extras);

                startActivity(intent);
            }
        });

    }
}