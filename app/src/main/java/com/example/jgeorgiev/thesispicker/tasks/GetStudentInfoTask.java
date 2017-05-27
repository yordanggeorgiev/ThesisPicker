package com.example.jgeorgiev.thesispicker.tasks;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.jgeorgiev.thesispicker.R;
import com.example.jgeorgiev.thesispicker.ThesisPickerActivity;
import com.example.jgeorgiev.thesispicker.database.DatabaseUtils;
import com.example.jgeorgiev.thesispicker.fragments.StudentInfoFragment;
import com.example.jgeorgiev.thesispicker.models.Student;

/**
 * Async task to get student info from db
 * Created by jgeorgiev on 5/24/17.
 */

public class GetStudentInfoTask extends AsyncTask<Void, Void, Student> {

    private ThesisPickerActivity activity;
    private SQLiteDatabase database;
    private String egn;
    private String facNumber;
    private ProgressDialog pd;


    public GetStudentInfoTask(ThesisPickerActivity activity, SQLiteDatabase database, String egn, String facNumber) {
        if (database == null) {
            throw new AssertionError("Database is required.");
        }
        this.activity = activity;
        this.database = database;
        this.egn = egn;
        this.facNumber = facNumber;
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
    protected Student doInBackground(Void... objects) {
        return database.isOpen() ? DatabaseUtils.getUserData(database, egn, facNumber) : null;
    }

    @Override
    protected void onPostExecute(Student student) {
        pd.dismiss();
        if (student == null) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setCancelable(false);
            builder.setTitle(R.string.nonexistant_user);
            builder.setMessage(R.string.nonexistant_user_message);
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
            ThesisPickerActivity.setStudent(student);
            activity.getFragmentHelper().addFragment(new StudentInfoFragment(), true);
        }
        super.onPostExecute(student);
    }
}

