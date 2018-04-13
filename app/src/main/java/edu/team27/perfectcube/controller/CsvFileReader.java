package edu.team27.perfectcube.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.io.InputStreamReader;

import edu.team27.perfectcube.model.ShelterInfo;

public class CsvFileReader {

    InputStream inputstream;

    public CsvFileReader(InputStream inputstream) {
        this.inputstream = inputstream;
    }


    public List read() {

        //Create a new list of student to be filled by CSV file data;
        List<ShelterInfo> shelters = new ArrayList<>();

        //Create the file reader
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputstream));

        try {

            String line = fileReader.readLine(); //first line
            line = fileReader.readLine(); //second line

            //Read the file line by line starting from the second line\
            while (line != null) {

                //Get all tokens available in line

                String secondline = line;
                String[] tokens = line.split(",");
                String[] address = secondline.split("\"");
                if (tokens.length > 0) {

                    //Create a new student object and fill his  data
                    ShelterInfo shelter = new ShelterInfo(tokens[1], tokens[2],
                            tokens[3], Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]), address[1], //(tokens[6] + "\n" + tokens[7] + tokens[8]),
                            tokens[tokens.length - 1]);
                    shelters.add(shelter);
                }
                line = fileReader.readLine();
            }
        }
        catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                inputstream.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
        return shelters;
    }
}

