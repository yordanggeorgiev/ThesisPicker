package com.example.jgeorgiev.thesispicker.database;

import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.jgeorgiev.thesispicker.R;
import com.example.jgeorgiev.thesispicker.ThesisPickerActivity;
import com.example.jgeorgiev.thesispicker.fragments.StudentInfoFragment;
import com.example.jgeorgiev.thesispicker.fragments.ThesisListFragment;
import com.example.jgeorgiev.thesispicker.models.Thesis;

import java.util.List;

/**
 * Created by jgeorgiev on 5/24/17.
 */

public class GetThesisListTask extends AsyncTask<Void, Void, List<Thesis>> {

    private ThesisPickerActivity activity;
    private SQLiteDatabase database;
    private ProgressDialog pd;


    public GetThesisListTask(ThesisPickerActivity activity, SQLiteDatabase database) {
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
    protected List<Thesis> doInBackground(Void... objects) {
        return database.isOpen() ? DatabaseUtils.getAllTheses(database) : null;
    }

    @Override
    protected void onPostExecute(List<Thesis> theses) {
        pd.dismiss();

        ThesisPickerActivity.setThesisList(theses);
        activity.getFragmentHelper().addFragment(new ThesisListFragment(), true);

        super.onPostExecute(theses);
    }
}

