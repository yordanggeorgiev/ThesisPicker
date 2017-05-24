package com.example.jgeorgiev.thesispicker.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jgeorgiev.thesispicker.database.DatabaseHelper;
import com.example.jgeorgiev.thesispicker.database.GetStudentInfoTask;
import com.example.jgeorgiev.thesispicker.interfaces.Stackable;
import com.example.jgeorgiev.thesispicker.R;
import com.example.jgeorgiev.thesispicker.ThesisPickerActivity;

/**
 * Created by jgeorgiev on 5/21/17.
 */

public class LoginFragment extends Fragment implements Stackable, View.OnClickListener {

    private EditText fieldEgn;
    private EditText fieldFacNumber;
    private String egn;
    private String facNumber;
    private TextInputLayout egnInputLayout;
    private TextInputLayout facNumberInputLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        egnInputLayout = (TextInputLayout) view.findViewById(R.id.egn_input_layout);
        facNumberInputLayout = (TextInputLayout) view.findViewById(R.id.fac_number_input_layout);

        fieldEgn = (EditText) view.findViewById(R.id.login_egn);
        fieldFacNumber = (EditText) view.findViewById(R.id.login_fac_num);

        final Button loginBtn = (Button) view.findViewById(R.id.login);
        loginBtn.setOnClickListener(this);

        fieldFacNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    isFacNumberValid();
                }
            }
        });

        fieldFacNumber.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    onClick(loginBtn);
                    return true;
                }

                return false;
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
        ((ThesisPickerActivity) getActivity()).createMaterialToolbar(false, getActivity().getString(R.string.login));
        ((ThesisPickerActivity) getActivity()).getDrawerHelper().lockDrawer();
    }

    @Override
    public void setupInitialState() {
        fieldEgn.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(fieldEgn, InputMethodManager.SHOW_IMPLICIT);
        fieldEgn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                isEgnValid();
            }
        });
    }

    private boolean isEgnValid() {
        egn = fieldEgn.getText().toString().trim();
        if (TextUtils.isEmpty(egn)) {
            setError(egnInputLayout, R.string.error_field_required);
            return false;
        }

        egnInputLayout.setError(null);

        return true;
    }

    private boolean isFacNumberValid() {
        facNumber = fieldFacNumber.getText().toString().trim();
        if (TextUtils.isEmpty(facNumber)) {
            setError(facNumberInputLayout, R.string.error_field_required);
            return false;
        }

        facNumberInputLayout.setError(null);

        return true;
    }

    public void setError(TextInputLayout input, int error) {
        input.setError(getString(error));
    }

    @Override
    public void onClick(View view) {
        if (!isEgnValid()) {
            fieldEgn.requestFocus();
            return;
        }

        if (!isFacNumberValid()) {
            fieldFacNumber.requestFocus();
            return;
        }

        new GetStudentInfoTask(((ThesisPickerActivity) getActivity()), ThesisPickerActivity.getDatabase(), egn, facNumber).execute();

        if (ThesisPickerActivity.getStudent() != null){
            ((ThesisPickerActivity) getActivity()).getFragmentHelper().addFragment(new StudentInfoFragment(), true);
        }
    }
}

