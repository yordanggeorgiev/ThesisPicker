package com.example.jgeorgiev.thesispicker.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.jgeorgiev.thesispicker.Interfaces.Stackable;
import com.example.jgeorgiev.thesispicker.R;
import com.example.jgeorgiev.thesispicker.ThesisPickerActivity;

public class StudentInfoFragment extends Fragment implements Stackable{

    private TextView student_names;
    private TextView faculty_number;
    private TextView thesis;
    private TextView thesis_lead;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_info, container, false);

        student_names = (TextView) view.findViewById(R.id.student_names);
        faculty_number = (TextView) view.findViewById(R.id.fak_number);
        thesis = (TextView) view.findViewById(R.id.thesis);
        thesis_lead = (TextView) view.findViewById(R.id.lead);

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

    @Override
    public void setupActionBar() {
        ((ThesisPickerActivity) getActivity()).createMaterialToolbar(true, getActivity().getString(R.string.menu_student_profile));
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
