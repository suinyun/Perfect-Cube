package edu.team27.perfectCube.controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.team27.perfectCube.R;
import edu.team27.perfectCube.model.LoginData;
import edu.team27.perfectCube.model.ShelterDatabase;
import edu.team27.perfectCube.model.ShelterInfo;
import edu.team27.perfectCube.model.User;
import edu.team27.perfectCube.model.UserDatabase;

import static edu.team27.perfectCube.controller.LoginActivity.sc;

/**
 * does stuff for item display once clicked in list
 * */
public class SingleListItem extends Activity {

    private EditText bedCount;
    private User user;

    private String filterGender;
    private String filterAge;
    private String filterName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.single_list_item_view);

        final ShelterInfo shelter = ListActivity.selected;

        TextView txtSN = findViewById(R.id.shelter_name);
        TextView txtG = findViewById(R.id.gender);
        final TextView txtC = findViewById(R.id.capacity);
        TextView txtA = findViewById(R.id.address);
        TextView txtPN = findViewById(R.id.phone_number);
        Button reservationButton = findViewById(R.id.reservationButton);
        Button cancelButton = findViewById(R.id.cancelButton);
        Button backButton = findViewById(R.id.backButton);
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
        filterGender = sc.getFilterGender();
        filterAge = sc.getFilterAge();
        filterName = sc.getFilterName();
        final String username = bundle.getString("username");

        user = LoginData.getUser(username);

        // displaying selected product name
        txtSN.setText(name);
        String vc = "Vacancies: " + capacity;
        txtC.setText(vc);
        txtG.setText(gender);
        txtA.setText(address);
        txtPN.setText(phone);

        final ShelterDatabase sdb = WelcomeActivity.getSdb();
        final UserDatabase db = WelcomeActivity.getDb();


        reservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int bedCountNum;
                Editable bc = bedCount.getText();
                String rl = user.getReservationLocation();

                try {
                    bedCountNum = Integer.parseInt(bc.toString());
                } catch (Exception e) {
                    bedCount.setError("not an int");
                    Toast.makeText(getBaseContext(), "not an int", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (bedCountNum < 0) {
                    bedCount.setError("less than 0");
                    Toast.makeText(getBaseContext(), "less than 0", Toast.LENGTH_SHORT).show();
                } else if (bedCountNum == 0) {
                    Toast.makeText(getBaseContext(), "Please enter a number greater than 0",
                            Toast.LENGTH_SHORT).show();
                } else if (bedCountNum > Integer.parseInt(capacity)) {
                    bedCount.setError("greater than capacity");
                    Toast.makeText(getBaseContext(), "greater than capacity",
                            Toast.LENGTH_SHORT).show();
                } else if (!"".equals(rl)) {
                    String msg = "you already have a reservation at\n"
                            + user.getReservationLocation();
                    bedCount.setError(msg);
                    Toast.makeText(getBaseContext(), "You must cancel your current reservation",
                            Toast.LENGTH_SHORT).show();
                } else {
                    //3 things: make sure that user cant reserve the same shelter twice
                    // you want to decrease the amount of beds
                    // change the textView of the updated capacity

                    // updating user info
                    user.setReservationNumber(bedCountNum);
                    user.setReservationLocation(shelter.getShelterName());

                    //update shelter info
                    String n = shelter.getShelterName();
                    if ("Eden Village".equals(n)) {
                        //32 for families, 80 singles

                        String capNum = shelter.getCapacity();
                        String[] capSplit = capNum.split(" ");
                        DialogFragment newFragment = new EdenVillageDialog();
                        newFragment.show(newFragment.getFragmentManager(), "ev");
                        Dialog d = newFragment.getDialog();

                        StringBuilder front = new StringBuilder("");
                        StringBuilder back = new StringBuilder("");
                        //need to figure out how to get data from the button clicked
                        int change = 0;
                        if ("f".equals("")) {
                            change = Integer.parseInt(capSplit[0]);
                            for (int i = 1; i < capSplit.length; i++) {
                                back.append(" ");
                                back.append(capSplit[i]);
                            }
                        } else if ("i".equals("")) {
                            change = Integer.parseInt(capSplit[3]);
                            for (int i = 0; i < 3; i++) {
                                front.append(capSplit[i]);
                                front.append(" ");
                            }
                            back.append(capSplit[4]);
                        }
                        change = change - bedCountNum;
                        String stringChange = String.valueOf(change);
                        front.append(stringChange);
                        front.append(back);

                    } else if ("Our House".equals(n) || "Hope Atlanta".equals(n)) {
                        //76 family rooms
                        //22 apartments
                        String capNum = shelter.getCapacity();
                        String[] capSplit = capNum.split("\\s");
                        capNum = capSplit[0];
                        int cap = Integer.parseInt(capNum);
                        StringBuilder newCap = new StringBuilder(String.valueOf(cap - bedCountNum));
                        for (int i = 1; i < capSplit.length; i++) {
                            newCap.append(" ");
                            newCap.append(capSplit[i]);
                        }
                        shelter.setCapacity(newCap.toString());
                    } else {
                        int cap = Integer.parseInt(shelter.getCapacity());
                        String newCap = String.valueOf(cap - bedCountNum);
                        shelter.setCapacity(newCap);
                    }


                    //update display
                    String message = "Vacancies: " + String.valueOf(shelter.getCapacity());
                    txtC.setText(message);

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


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //     try {
                    String rl = user.getReservationLocation();
                    boolean a = rl.equals(shelter.getShelterName());
                    if (a) {
                        shelter.setCapacity(Integer.toString(
                                Integer.parseInt(shelter.getCapacity())
                                + user.getReservationNumber()));
                        String message = "Vacancies: " + String.valueOf(shelter.getCapacity());
                        txtC.setText(message);

                        ShelterDatabase sdb = WelcomeActivity.getSdb();
                        sdb.shelterDao().updateShelters(shelter);
                        WelcomeActivity.setSdb(sdb);
                    } else if ("".equals(rl)) {
                        Toast.makeText(getBaseContext(), "you don't have a reservation",
                                Toast.LENGTH_SHORT).show();
                    }

                    user.setReservationNumber(0);
                    user.setReservationLocation("");

                    UserDatabase db = WelcomeActivity.getDb();
                    db.userDao().updateUsers(user);
                    WelcomeActivity.setDb(db);
                //}

//                } catch (NullPointerException e) {
//                    String oops = "You done messed up now"
//                    txtC.setText(oops);
//                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
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
