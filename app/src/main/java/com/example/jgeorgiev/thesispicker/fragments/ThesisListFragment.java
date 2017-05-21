/*
package com.example.jgeorgiev.thesispicker.fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jgeorgiev.thesispicker.R;
import com.example.jgeorgiev.thesispicker.data.DatabaseContract;
import com.example.jgeorgiev.thesispicker.data.DatabaseHelper;

public class ThesisListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thesis_list, container, false);

        Cursor cursor = getActivity().getContentResolver().query(DatabaseContract.ThesesTable,
                new String[] {DatabaseContract.ThesesTable._ID, DatabaseContract.ThesesTable.COLUMN_TITLE, DatabaseContract.ThesesTable.COLUMN_LEAD}, null, null, null);
        startManagingCursor(cursor);

        String[] columns = new String[] { DatabaseContract.ThesesTable.COLUMN_TITLE, DatabaseContract.ThesesTable.COLUMN_LEAD };
        int[] to = new int[] { R.id.thesis_name, R.id.thesis_lead };

        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(getActivity(), R.layout.thesis_list_entries, cursor, columns, to);

        getActivity().setListAdapter(mAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}*/
