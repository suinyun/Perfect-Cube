package edu.team27.perfectcube.recyclerview;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.team27.perfectcube.R;
import edu.team27.perfectcube.model.ShelterInfo;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<ShelterInfo> shelters;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView firstLine;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            firstLine = (TextView) v.findViewById(R.id.firstLine);
        }
    }

    public void insert(int position, ShelterInfo item) {
        shelters.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        shelters.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public Adapter(List<ShelterInfo> myDataset) {
        shelters = myDataset;
    }


    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.row_layout,
                parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = shelters.get(position).toString();
        holder.firstLine.setText(name);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return shelters.size();
    }

    public List<ShelterInfo> getShelters() {
        return this.shelters;
    }

    public void setShelters(List<ShelterInfo> list) {
        shelters.clear();
        shelters.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}