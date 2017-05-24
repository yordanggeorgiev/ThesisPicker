package com.example.jgeorgiev.thesispicker.database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jgeorgiev.thesispicker.models.Student;
import com.example.jgeorgiev.thesispicker.models.Teacher;
import com.example.jgeorgiev.thesispicker.models.Thesis;
import com.example.jgeorgiev.thesispicker.utils.SampleData;

/**
 * Created by jgeorgiev on 4/23/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_TABLE_STUDENTS =
            "CREATE TABLE IF NOT EXISTS " + DatabaseContract.StudentsTable.TABLE_NAME + " (" +
                    DatabaseContract.StudentsTable.COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY, " +
                    DatabaseContract.StudentsTable.COLUMN_NAME + " TEXT NOT NULL, " +
                    DatabaseContract.StudentsTable.COLUMN_FACULTY_NUMBER + " INTEGER UNIQUE NOT NULL, " +
                    DatabaseContract.StudentsTable.COLUMN_SPECIALTY + " TEXT NOT NULL, " +
                    DatabaseContract.StudentsTable.COLUMN_ADMINISTRATIVE_GROUP + " INTEGER NOT NULL, " +
                    DatabaseContract.StudentsTable.COLUMN_IS_BACHELOR + " INTEGER NOT NULL, " +
                    DatabaseContract.StudentsTable.COLUMN_EGN + " INTEGER UNIQUE NOT NULL, " +
                    DatabaseContract.StudentsTable.COLUMN_THESIS + " INTEGER, " +
                    DatabaseContract.StudentsTable.COLUMN_REVIEWER + " INTEGER, FOREIGN KEY (" +
                    DatabaseContract.StudentsTable.COLUMN_THESIS + ") REFERENCES " +
                    DatabaseContract.ThesesTable.TABLE_NAME + " (" +
                    DatabaseContract.ThesesTable.COLUMN_THESIS_ID + "), FOREIGN KEY (" +
                    DatabaseContract.StudentsTable.COLUMN_REVIEWER + ") REFERENCES " +
                    DatabaseContract.TeachersTable.TABLE_NAME + " (" +
                    DatabaseContract.TeachersTable.COLUMN_TEACHER_ID + "))";

    private static final String SQL_CREATE_TABLE_TEACHERS =
            "CREATE TABLE IF NOT EXISTS " + DatabaseContract.TeachersTable.TABLE_NAME + " (" +
                    DatabaseContract.TeachersTable.COLUMN_TEACHER_ID + " INTEGER PRIMARY KEY, " +
                    DatabaseContract.TeachersTable.COLUMN_NAME + " TEXT NOT NULL, " +
                    DatabaseContract.TeachersTable.COLUMN_EMAIL + " TEXT, " +
                    DatabaseContract.TeachersTable.COLUMN_PHONE + " TEXT)";

    private static final String SQL_CREATE_TABLE_THESES =
            "CREATE TABLE IF NOT EXISTS " + DatabaseContract.ThesesTable.TABLE_NAME + " (" +
                    DatabaseContract.ThesesTable.COLUMN_THESIS_ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.ThesesTable.COLUMN_TITLE + " TEXT NOT NULL, " +
                    DatabaseContract.ThesesTable.COLUMN_DETAILS + " TEXT, " +
                    DatabaseContract.ThesesTable.COLUMN_LEAD + " INTEGER NOT NULL, " +
                    DatabaseContract.ThesesTable.COLUMN_IS_PICKED + " INTEGER NOT NULL, FOREIGN KEY (" +
                    DatabaseContract.ThesesTable.COLUMN_LEAD + ") REFERENCES " +
                    DatabaseContract.TeachersTable.TABLE_NAME + " (" +
                    DatabaseContract.TeachersTable.COLUMN_TEACHER_ID + "))";

    private static final String SQL_DELETE_TABLE_STUDENTS =
            "DROP TABLE IF EXISTS " + DatabaseContract.StudentsTable.TABLE_NAME;

    private static final String SQL_DELETE_TABLE_TEACHERS =
            "DROP TABLE IF EXISTS " + DatabaseContract.TeachersTable.TABLE_NAME;

    private static final String SQL_DELETE_TABLE_THESES =
            "DROP TABLE IF EXISTS " + DatabaseContract.ThesesTable.TABLE_NAME;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ThesisPicker.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_TEACHERS);
        db.execSQL(SQL_CREATE_TABLE_THESES);
        db.execSQL(SQL_CREATE_TABLE_STUDENTS);

        SampleData.InsertSampleData(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE_STUDENTS);
        db.execSQL(SQL_DELETE_TABLE_THESES);
        db.execSQL(SQL_DELETE_TABLE_TEACHERS);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}