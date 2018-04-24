package edu.team27.perfectCube.controller;


import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.io.InputStreamReader;

import edu.team27.perfectCube.model.ShelterInfo;

class CsvFileReader {

    private final InputStream inputstream;

    public CsvFileReader(InputStream inputstream) {
        this.inputstream = inputstream;
    }


    public List read() {

        //Create a new list of student to be filled by CSV file data;
        List<ShelterInfo> shelters = new ArrayList<>();

        //Create the file reader
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputstream));

        try {

            String fLine = fileReader.readLine(); //first line
            String line = fileReader.readLine(); //second line

            //Read the file line by line starting from the second line\
            while (line != null) {

                //Get all tokens available in line
Log.d("APP", "Got line: " + line );
                String secondLine = line;
                String[] tokens = line.split(",");
                String[] address = secondLine.split("\"");
                if (tokens.length > 0) {

                    //Create a new student object and fill his  data
                    ShelterInfo shelter = new ShelterInfo(tokens[1], tokens[2],
                            tokens[3], Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]),
                            address[1], tokens[tokens.length - 1]);
                    shelters.add(shelter);
                }
                line = fileReader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputstream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return shelters;
    }
}

