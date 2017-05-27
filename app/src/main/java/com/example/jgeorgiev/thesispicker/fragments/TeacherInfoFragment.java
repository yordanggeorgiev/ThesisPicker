package com.example.jgeorgiev.thesispicker.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jgeorgiev.thesispicker.R;
import com.example.jgeorgiev.thesispicker.models.Teacher;

/**
 * Fragment for teacher info
 * Created by ygeorgiev on 26-May-17.
 */

public class TeacherInfoFragment extends DialogFragment {

    private Teacher teacher;

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

        TextView teacherNames = (TextView) view.findViewById(R.id.teacher_names);
        TextView teacherEmail = (TextView) view.findViewById(R.id.teacher_email);
        TextView teacherPhone = (TextView) view.findViewById(R.id.teacher_number);

        if (teacher != null) {
            teacherNames.setText(teacher.getName());
            teacherEmail.setText(teacher.getEmail());
            teacherPhone.setText(teacher.getPhone());
        }

        return view;
    }
}

