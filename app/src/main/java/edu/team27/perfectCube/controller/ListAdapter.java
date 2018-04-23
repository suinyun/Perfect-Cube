package edu.team27.perfectCube.controller;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.team27.perfectCube.R;

class ListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> nameList;

    public ListAdapter(Activity context, List<String> nameList) {
        super(context, R.layout.namelist_layout, nameList);
        this.context = context;
        this.nameList = (ArrayList<String>) nameList;
    }

    @NonNull
    @Override
    //uncomment to run detail view
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        // pass in the detail view
        View listViewItem = inflater.inflate(R.layout.namelist_layout, null, true);

        TextView listText = listViewItem.findViewById(R.id.name_list_layout);

        String name = nameList.get(position);
        listText.setText(name);

        return listViewItem;
    }
}
