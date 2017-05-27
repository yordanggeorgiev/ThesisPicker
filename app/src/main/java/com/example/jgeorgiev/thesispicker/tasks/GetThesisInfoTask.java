package com.example.jgeorgiev.thesispicker.tasks;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.jgeorgiev.thesispicker.R;
import com.example.jgeorgiev.thesispicker.ThesisPickerActivity;
import com.example.jgeorgiev.thesispicker.database.DatabaseUtils;
import com.example.jgeorgiev.thesispicker.fragments.ThesisInfoFragment;
import com.example.jgeorgiev.thesispicker.models.Thesis;

/**
 * Async task to get thesis info from db
 * Created by jgeorgiev on 5/24/17.
 */

public class GetThesisInfoTask extends AsyncTask<Void, Void, Thesis> {

    private ThesisPickerActivity activity;
    private SQLiteDatabase database;
    private Thesis thesis;
    private ProgressDialog pd;


    public GetThesisInfoTask(ThesisPickerActivity activity, SQLiteDatabase database, Thesis thesis) {
        if (database == null) {
            throw new AssertionError("Database is required.");
        }
        this.activity = activity;
        this.database = database;
        this.thesis = thesis;
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
    protected Thesis doInBackground(Void... objects) {
        return database.isOpen() ? DatabaseUtils.getThesisInfo(database, thesis) : null;
    }

    @Override
    protected void onPostExecute(Thesis thesis) {
        pd.dismiss();
        if (thesis == null) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setCancelable(false);
            builder.setTitle(R.string.nonexistant_info);
            builder.setMessage(R.string.nonexistant_thesis_info_message);
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    builder.show();
                }
            });
        } else {
            ThesisInfoFragment thif = ThesisInfoFragment.init(thesis);
            thif.show(activity.getFragmentManager(), thif.getClass().getSimpleName());
        }
        super.onPostExecute(thesis);
    }
}

