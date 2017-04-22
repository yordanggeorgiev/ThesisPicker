package com.example.jgeorgiev.thesispicker.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jgeorgiev.thesispicker.R;

public class StudentInfoFragment extends Fragment {

    private TextView student_names;
    private TextView faculty_number;
    private TextView thesis;
    private TextView thesis_lead;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_info_fragment, container);

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
}
