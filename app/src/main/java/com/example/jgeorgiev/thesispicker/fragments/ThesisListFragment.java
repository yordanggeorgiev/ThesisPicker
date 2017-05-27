package com.example.jgeorgiev.thesispicker.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jgeorgiev.thesispicker.R;
import com.example.jgeorgiev.thesispicker.ThesisPickerActivity;
import com.example.jgeorgiev.thesispicker.adapters.ThesisListAdapter;
import com.example.jgeorgiev.thesispicker.interfaces.Stackable;
import com.example.jgeorgiev.thesispicker.models.Thesis;
import com.example.jgeorgiev.thesispicker.tasks.GetThesisInfoTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment for thesis list
 * Created by ygeorgiev on 26-May-17.
 */
public class ThesisListFragment extends Fragment implements Stackable {

    List<Thesis> thesisList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thesis_list, container, false);

        if (ThesisPickerActivity.getThesisList() != null) {
            thesisList = ThesisPickerActivity.getThesisList();
        }

        ThesisListAdapter thesisListAdapter = new ThesisListAdapter(getActivity(), R.layout.fragment_thesis_list, thesisList);
        ListView lv = (ListView) view.findViewById(R.id.thesis_list);
        lv.setAdapter(thesisListAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                Thesis thesis = (Thesis) adapter.getItemAtPosition(position);
                new GetThesisInfoTask((ThesisPickerActivity) getActivity(), ThesisPickerActivity.getDatabase(), thesis).execute();
            }
        });

        return view;
    }

    @Override
    public void setupActionBar() {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }
        ((ThesisPickerActivity) getActivity()).createMaterialToolbar(true, getActivity().getString(R.string.menu_theses));
        ((ThesisPickerActivity) getActivity()).getDrawerHelper().lockDrawer();
    }

    @Override
    public void setupInitialState() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getActivity().getCurrentFocus() != null && getActivity().getCurrentFocus().getWindowToken() != null) {
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
