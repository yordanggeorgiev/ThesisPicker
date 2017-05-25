package com.example.jgeorgiev.thesispicker.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jgeorgiev.thesispicker.R;
import com.example.jgeorgiev.thesispicker.models.Thesis;

import java.util.List;

/**
 * Created by ygeorgiev on 25-May-17.
 */

public class ThesisListAdapter extends ArrayAdapter<Thesis> {

    public ThesisListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ThesisListAdapter(Context context, int resource, List<Thesis> theses) {
        super(context, resource, theses);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.thesis_list_entries, null);
        }

        Thesis t = getItem(position);

        if (t != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.thesis_title);
            TextView tt2 = (TextView) v.findViewById(R.id.thesis_lead);

            if (tt1 != null) {
                tt1.setText(t.getTitle());
            }

            if (tt2 != null) {
                tt2.setText(t.getLead());
            }
        }

        return v;
    }

}
