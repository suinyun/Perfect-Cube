package edu.team27.perfectcube.controller;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.team27.perfectcube.R;

/**
 * Created by emmad on 3/7/2018.
 */

public class ListAdapter extends ArrayAdapter<String> {

    private Activity context;
    private List<String> namelist;

    public ListAdapter(Activity context, List<String> namelist) {
        super(context, R.layout.namelist_layout, namelist);
        this.context = context;
        this.namelist = namelist;
    }

    @NonNull
    @Override
    //uncomment to run detail view
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        // pass in the detail view
        View listViewItem = inflater.inflate(R.layout.namelist_layout, null, true);

        TextView listText = (TextView) listViewItem.findViewById(R.id.namelist_layout);

        String name = namelist.get(position);
        listText.setText(name);

        return listViewItem;
    }
}
