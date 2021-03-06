package com.example.jgeorgiev.thesispicker.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.jgeorgiev.thesispicker.R;
import com.example.jgeorgiev.thesispicker.ThesisPickerActivity;
import com.example.jgeorgiev.thesispicker.interfaces.Stackable;
import com.example.jgeorgiev.thesispicker.models.Thesis;

/**
 * Fragment for student info
 * Created by ygeorgiev on 25-May-17.
 */
public class StudentInfoFragment extends Fragment implements Stackable {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_info, container, false);

        TextView studentNames = (TextView) view.findViewById(R.id.student_names);
        TextView facultyNumber = (TextView) view.findViewById(R.id.fak_number);
        TextView specialty = (TextView) view.findViewById(R.id.specialty);
        TextView adminGroup = (TextView) view.findViewById(R.id.admin_group);
        TextView isBachelor = (TextView) view.findViewById(R.id.is_bachelor);
        TextView thesis = (TextView) view.findViewById(R.id.thesis);
        TextView thesisLead = (TextView) view.findViewById(R.id.lead);
        TextView reviewer = (TextView) view.findViewById(R.id.reviewer);

        if (ThesisPickerActivity.getStudent() != null) {
            studentNames.setText(ThesisPickerActivity.getStudent().getName());
            facultyNumber.setText(String.valueOf(ThesisPickerActivity.getStudent().getFacultyNumber()));
            specialty.setText(ThesisPickerActivity.getStudent().getSpecialty());
            adminGroup.setText(String.valueOf(ThesisPickerActivity.getStudent().getAdminGroup()));
            if (ThesisPickerActivity.getStudent().isBachelor()) {
                isBachelor.setText(R.string.student_bachelor);
            } else {
                isBachelor.setText(R.string.student_master);
            }
            Thesis studentThesis = ThesisPickerActivity.getStudent().getThesis();
            if (studentThesis != null) {
                thesis.setText(ThesisPickerActivity.getStudent().getThesis().getTitle());
                thesisLead.setText(ThesisPickerActivity.getStudent().getThesis().getLead());
                reviewer.setText(ThesisPickerActivity.getStudent().getReviewer());
            }
        }

        return view;
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
