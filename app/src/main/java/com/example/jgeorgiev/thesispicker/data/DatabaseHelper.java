package com.example.jgeorgiev.thesispicker.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jgeorgiev on 4/23/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_TABLE_STUDENTS =
            "CREATE TABLE IF NOT EXISTS " + DatabaseContract.StudentsTable.TABLE_NAME + " (" +
                    DatabaseContract.StudentsTable.COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY, " +
                    DatabaseContract.StudentsTable.COLUMN_NAME + " TEXT, " +
                    DatabaseContract.StudentsTable.COLUMN_FACULTY_NUMBER + " TEXT, " +
                    DatabaseContract.StudentsTable.COLUMN_PASSWORD + " TEXT, " +
                    DatabaseContract.StudentsTable.COLUMN_THESIS + " INTEGER, " +
                    DatabaseContract.StudentsTable.COLUMN_IS_FINALIZED + " INTEGER, FOREIGN KEY " +
                    DatabaseContract.StudentsTable.COLUMN_THESIS + " REFERENCES " +
                    DatabaseContract.ThesesTable.TABLE_NAME + " (" +
                    DatabaseContract.ThesesTable.COLUMN_THESIS_ID + "))";

    private static final String SQL_CREATE_TABLE_TEACHERS =
            "CREATE TABLE IF NOT EXISTS " + DatabaseContract.TeachersTable.TABLE_NAME + " (" +
                    DatabaseContract.TeachersTable.COLUMN_TEACHER_ID + " INTEGER PRIMARY KEY, " +
                    DatabaseContract.TeachersTable.COLUMN_NAME + " TEXT, " +
                    DatabaseContract.TeachersTable.COLUMN_EMAIL + " TEXT, " +
                    DatabaseContract.TeachersTable.COLUMN_PHONE + " TEXT)";

    private static final String SQL_CREATE_TABLE_THESES =
            "CREATE TABLE IF NOT EXISTS " + DatabaseContract.ThesesTable.TABLE_NAME + " (" +
                    DatabaseContract.ThesesTable.COLUMN_THESIS_ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.ThesesTable.COLUMN_TITLE + " TEXT, " +
                    DatabaseContract.ThesesTable.COLUMN_DETAILS + " TEXT, " +
                    DatabaseContract.ThesesTable.COLUMN_LEAD + " INTEGER, " +
                    DatabaseContract.ThesesTable.COLUMN_IS_PICKED + " INTEGER, FOREIGN KEY " +
                    DatabaseContract.ThesesTable.COLUMN_LEAD + " REFERENCES " +
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
