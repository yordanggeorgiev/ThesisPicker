package com.example.jgeorgiev.thesispicker.tasks;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.jgeorgiev.thesispicker.R;
import com.example.jgeorgiev.thesispicker.ThesisPickerActivity;
import com.example.jgeorgiev.thesispicker.database.DatabaseUtils;
import com.example.jgeorgiev.thesispicker.fragments.TeacherInfoFragment;
import com.example.jgeorgiev.thesispicker.models.Teacher;

/**
 * Async task to get teacher info from db
 * Created by jgeorgiev on 5/24/17.
 */

public class GetTeacherInfoTask extends AsyncTask<Void, Void, Teacher> {

    private ThesisPickerActivity activity;
    private SQLiteDatabase database;
    private String teacher;
    private ProgressDialog pd;


    public GetTeacherInfoTask(ThesisPickerActivity activity, SQLiteDatabase database, String teacher) {
        if (database == null) {
            throw new AssertionError("Database is required.");
        }
        this.activity = activity;
        this.database = database;
        this.teacher = teacher;
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
    protected Teacher doInBackground(Void... objects) {
        return database.isOpen() ? DatabaseUtils.getTeacherInfo(database, teacher) : null;
    }

    @Override
    protected void onPostExecute(Teacher teacher) {
        pd.dismiss();
        if (teacher == null) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setCancelable(false);
            builder.setTitle(R.string.nonexistant_info);
            builder.setMessage(R.string.nonexistant_teacher_info_message);
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
            TeacherInfoFragment tif = TeacherInfoFragment.init(teacher);
            tif.show(activity.getFragmentManager(), tif.getClass().getSimpleName());
        }
        super.onPostExecute(teacher);
    }
}

