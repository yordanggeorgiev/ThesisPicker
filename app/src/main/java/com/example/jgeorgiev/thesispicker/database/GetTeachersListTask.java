package com.example.jgeorgiev.thesispicker.database;

import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.jgeorgiev.thesispicker.R;
import com.example.jgeorgiev.thesispicker.ThesisPickerActivity;
import com.example.jgeorgiev.thesispicker.fragments.TeacherListFragment;
import com.example.jgeorgiev.thesispicker.models.Teacher;

import java.util.List;

/**
 * Created by jgeorgiev on 5/24/17.
 */

public class GetTeachersListTask extends AsyncTask<Void, Void, List<Teacher>> {

    private ThesisPickerActivity activity;
    private SQLiteDatabase database;
    private ProgressDialog pd;


    public GetTeachersListTask(ThesisPickerActivity activity, SQLiteDatabase database) {
        if (database == null) {
            throw new AssertionError("Database is required.");
        }
        this.activity = activity;
        this.database = database;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(activity);
        pd.setMessage(activity.getString(R.string.wait_message));
        pd.setCancelable(false);
        pd.setIndeterminate(true);
        pd.show();
    }

    @Override
    protected List<Teacher> doInBackground(Void... objects) {
        return database.isOpen() ? DatabaseUtils.getAllTeachers(database) : null;
    }

    @Override
    protected void onPostExecute(List<Teacher> teachers) {
        pd.dismiss();

        ThesisPickerActivity.setTeachersList(teachers);
        activity.getFragmentHelper().addFragment(new TeacherListFragment(), true);

        super.onPostExecute(teachers);
    }
}

