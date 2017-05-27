package com.example.jgeorgiev.thesispicker.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jgeorgiev.thesispicker.R;
import com.example.jgeorgiev.thesispicker.ThesisPickerActivity;
import com.example.jgeorgiev.thesispicker.models.Thesis;
import com.example.jgeorgiev.thesispicker.tasks.UpdateStudentTask;
import com.example.jgeorgiev.thesispicker.tasks.UpdateThesisTask;

/**
 * Fragment for thesis info
 * Created by ygeorgiev on 26-May-17.
 */

public class ThesisInfoFragment extends DialogFragment {

    private Thesis thesis;

    public ThesisInfoFragment() {
        // Empty constructor
    }

    public static ThesisInfoFragment init(Thesis thesis) {
        ThesisInfoFragment f = new ThesisInfoFragment();
        f.thesis = thesis;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thesis_info, container);

        TextView thesisTitle = (TextView) view.findViewById(R.id.thesis_title);
        TextView thesisDetails = (TextView) view.findViewById(R.id.thesis_details);
        TextView thesisLead = (TextView) view.findViewById(R.id.thesis_lead);

        if (thesis != null) {
            thesisTitle.setText(thesis.getTitle());
            thesisDetails.setText(thesis.getDetails());
            thesisLead.setText(thesis.getLead());
        }

        Button chooseThesis = (Button) view.findViewById(R.id.btn_choose);
        Button cantChooseThesis = (Button) view.findViewById(R.id.btn_cant_choose);

        if (ThesisPickerActivity.getStudent().getThesis() == null && !thesis.isPicked()) {
            chooseThesis.setVisibility(View.VISIBLE);
            cantChooseThesis.setVisibility(View.GONE);
        } else {
            chooseThesis.setVisibility(View.GONE);
            cantChooseThesis.setVisibility(View.VISIBLE);
        }


        chooseThesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UpdateThesisTask(ThesisPickerActivity.getDatabase(), thesis).execute();
                new UpdateStudentTask(ThesisPickerActivity.getDatabase(), ThesisPickerActivity.getStudent(), thesis).execute();
                Toast.makeText(getActivity(), R.string.thesis_picked, Toast.LENGTH_LONG).show();
                dismiss();
            }
        });

        cantChooseThesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), R.string.thesis_already_picked, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}

