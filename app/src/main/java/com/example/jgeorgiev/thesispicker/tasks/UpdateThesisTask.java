package com.example.jgeorgiev.thesispicker.tasks;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.jgeorgiev.thesispicker.database.DatabaseUtils;
import com.example.jgeorgiev.thesispicker.models.Thesis;

/**
 * Async task to update thesis info in db
 * Created by jgeorgiev on 5/24/17.
 */

public class UpdateThesisTask extends AsyncTask<Void, Void, Void> {

    private SQLiteDatabase database;
    private Thesis thesis;


    public UpdateThesisTask(SQLiteDatabase database, Thesis thesis) {
        if (database == null) {
            throw new AssertionError("Database is required.");
        }
        this.database = database;
        this.thesis = thesis;
    }

    @Override
    protected Void doInBackground(Void... objects) {
        if (database.isOpen()) {
            DatabaseUtils.updateThesisData(database, thesis);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        thesis.setPicked(true);
        super.onPostExecute(v);
    }
}

