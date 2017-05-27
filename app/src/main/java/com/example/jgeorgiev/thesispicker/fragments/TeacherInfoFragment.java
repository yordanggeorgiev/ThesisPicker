package com.example.jgeorgiev.thesispicker.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.jgeorgiev.thesispicker.R;
import com.example.jgeorgiev.thesispicker.models.Teacher;

/**
 * Created by ygeorgiev on 26-May-17.
 */

public class TeacherInfoFragment extends DialogFragment {

    private Teacher teacher;

    private TextView teacherNames;
    private TextView teacherEmail;
    private TextView teacherPhone;

    public TeacherInfoFragment() {
        // Empty constructor
    }

    public static TeacherInfoFragment init(Teacher teacher) {
        TeacherInfoFragment f = new TeacherInfoFragment();
        f.teacher = teacher;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_info, container);

        if (getDialog().getWindow() != null) {
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }

        teacherNames = (TextView) view.findViewById(R.id.teacher_names);
        teacherEmail = (TextView) view.findViewById(R.id.teacher_email);
        teacherPhone = (TextView) view.findViewById(R.id.teacher_number);

        if (teacher != null) {
            teacherNames.setText(teacher.getName());
            teacherEmail.setText(teacher.getEmail());
            teacherPhone.setText(teacher.getPhone());
        }

        view.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }
}




