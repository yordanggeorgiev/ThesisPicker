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

import com.example.jgeorgiev.thesispicker.Interfaces.Stackable;
import com.example.jgeorgiev.thesispicker.R;
import com.example.jgeorgiev.thesispicker.ThesisPickerActivity;

/**
 * Created by jgeorgiev on 5/21/17.
 */

public class LoginFragment extends Fragment implements Stackable, View.OnClickListener {

    private EditText fieldUsername;
    private EditText fieldPassword;
    private String username;
    private String password;
    private TextInputLayout usernameInputLayout;
    private TextInputLayout passwordInputLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        usernameInputLayout = (TextInputLayout) view.findViewById(R.id.usernameInputLayout);
        passwordInputLayout = (TextInputLayout) view.findViewById(R.id.passwordInputLayout);

        fieldUsername = (EditText) view.findViewById(R.id.login_egn);
        fieldPassword = (EditText) view.findViewById(R.id.login_password);

        final Button loginBtn = (Button) view.findViewById(R.id.login);
        loginBtn.setOnClickListener(this);

        fieldPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    isPasswordValid();
                }
            }
        });

        fieldPassword.setOnEditorActionListener(new EditText.OnEditorActionListener() {
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
        fieldUsername.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(fieldUsername, InputMethodManager.SHOW_IMPLICIT);
        fieldUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                isUsernameValid();
            }
        });
    }

    private boolean isUsernameValid() {
        username = fieldUsername.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            setError(usernameInputLayout, R.string.error_field_required);
            return false;
        }

        usernameInputLayout.setError(null);

        return true;
    }

    private boolean isPasswordValid() {
        password = fieldPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            setError(passwordInputLayout, R.string.error_field_required);
            return false;
        }

        passwordInputLayout.setError(null);

        return true;
    }

    public void setError(TextInputLayout input, int error) {
        input.setError(getString(error));
    }

    @Override
    public void onClick(View view) {
        if (!isUsernameValid()) {
            fieldUsername.requestFocus();
            return;
        }

        if (!isPasswordValid()) {
            fieldPassword.requestFocus();
            return;
        }

        ((ThesisPickerActivity) getActivity()).getFragmentHelper().addFragment(new StudentInfoFragment(), true);

        /*ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage(getActivity().getString(R.string.login_progress));
        pd.setCancelable(false);
        pd.setIndeterminate(true);
        pd.show();*/
    }
}

