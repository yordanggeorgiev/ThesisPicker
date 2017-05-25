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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jgeorgiev.thesispicker.R;
import com.example.jgeorgiev.thesispicker.ThesisPickerActivity;
import com.example.jgeorgiev.thesispicker.interfaces.Stackable;
import com.example.jgeorgiev.thesispicker.models.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherListFragment extends Fragment implements Stackable {

    List<String> teachersNames = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thesis_list, container, false);

        if (ThesisPickerActivity.getTeachersList() != null) {
            List<Teacher> teachersList = ThesisPickerActivity.getTeachersList();
            for (Teacher teacher : teachersList) {
                teachersNames.add(teacher.getName());
            }
        }

        ArrayAdapter<String> teachersNamesAdapter = new ArrayAdapter<>(getActivity(), R.layout.teacher_list_entries, R.id.teacher_name, teachersNames);
        ListView lv = (ListView) view.findViewById(R.id.thesis_list);
        lv.setAdapter(teachersNamesAdapter);

        return view;
    }

    @Override
    public void setupActionBar() {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }
        ((ThesisPickerActivity) getActivity()).createMaterialToolbar(false, getActivity().getString(R.string.menu_teachers));
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