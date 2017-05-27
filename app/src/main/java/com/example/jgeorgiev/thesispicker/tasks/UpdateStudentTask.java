package com.example.jgeorgiev.thesispicker.tasks;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.jgeorgiev.thesispicker.ThesisPickerActivity;
import com.example.jgeorgiev.thesispicker.database.DatabaseUtils;
import com.example.jgeorgiev.thesispicker.models.Student;
import com.example.jgeorgiev.thesispicker.models.Thesis;

/**
 * Async task to update student info in db
 * Created by jgeorgiev on 5/24/17.
 */

public class UpdateStudentTask extends AsyncTask<Void, Void, String> {

    private SQLiteDatabase database;
    private Student student;
    private Thesis thesis;


    public UpdateStudentTask(SQLiteDatabase database, Student student, Thesis thesis) {
        if (database == null) {
            throw new AssertionError("Database is required.");
        }
        this.database = database;
        this.student = student;
        this.thesis = thesis;
    }

    @Override
    protected String doInBackground(Void... objects) {
        return database.isOpen() ? DatabaseUtils.updateStudentData(database, student.getFacultyNumber(), thesis) : null;
    }

    @Override
    protected void onPostExecute(String teacher) {
        ThesisPickerActivity.getStudent().setThesis(thesis);
        ThesisPickerActivity.getStudent().setReviewer(teacher);
        super.onPostExecute(teacher);
    }
}

