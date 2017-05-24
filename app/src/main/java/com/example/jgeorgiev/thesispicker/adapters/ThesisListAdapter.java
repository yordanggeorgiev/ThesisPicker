package com.example.jgeorgiev.thesispicker.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.jgeorgiev.thesispicker.R;
import com.example.jgeorgiev.thesispicker.database.DatabaseContract;

/**
 * Created by jgeorgiev on 5/21/17.
 */

public class ThesisListAdapter extends CursorAdapter{

    public ThesisListAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        if(cursor.getPosition()%2==1) {
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        }
        else {
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.gray));
        }

        TextView thesisTitleTV = (TextView) view.findViewById(R.id.thesis_title);
        TextView thesisLeadTV = (TextView) view.findViewById(R.id.thesis_lead);

        String thesisTitle = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.ThesesTable.COLUMN_TITLE));
        Integer thesisLead = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.ThesesTable.COLUMN_LEAD));

        thesisTitleTV.setText(thesisTitle);
        thesisLeadTV.setText(String.valueOf(thesisLead));

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.thesis_list_entries, parent, false);
    }
}
