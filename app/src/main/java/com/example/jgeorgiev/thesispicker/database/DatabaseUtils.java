package com.example.jgeorgiev.thesispicker.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jgeorgiev.thesispicker.ThesisPickerActivity;
import com.example.jgeorgiev.thesispicker.database.DatabaseContract;
import com.example.jgeorgiev.thesispicker.models.Student;
import com.example.jgeorgiev.thesispicker.models.Thesis;

/**
 * Created by jgeorgiev on 5/24/17.
 */

public class DatabaseUtils {

    private DatabaseUtils() {
    }

    public static Student getUserData(SQLiteDatabase db, String egn, String fac_number) {

        Student student = new Student();

        String[] columns = {
                DatabaseContract.StudentsTable.COLUMN_NAME,
                DatabaseContract.StudentsTable.COLUMN_FACULTY_NUMBER,
                DatabaseContract.StudentsTable.COLUMN_SPECIALTY,
                DatabaseContract.StudentsTable.COLUMN_ADMINISTRATIVE_GROUP,
                DatabaseContract.StudentsTable.COLUMN_IS_BACHELOR,
                DatabaseContract.StudentsTable.COLUMN_THESIS,
                DatabaseContract.StudentsTable.COLUMN_REVIEWER
        };

        String selection = DatabaseContract.StudentsTable.COLUMN_EGN + " = ?" + " AND " + DatabaseContract.StudentsTable.COLUMN_FACULTY_NUMBER + " = ?";

        String[] selectionArgs = {egn, fac_number};

        Cursor cursor = db.query(DatabaseContract.StudentsTable.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor.getCount() > 0) {
            student.setName(cursor.getString(cursor.getColumnIndex(DatabaseContract.StudentsTable.COLUMN_NAME)));
            student.setFacultyNumber(cursor.getInt(cursor.getColumnIndex(DatabaseContract.StudentsTable.COLUMN_FACULTY_NUMBER)));
            student.setSpecialty(cursor.getString(cursor.getColumnIndex(DatabaseContract.StudentsTable.COLUMN_SPECIALTY)));
            student.setAdminGroup(cursor.getInt(cursor.getColumnIndex(DatabaseContract.StudentsTable.COLUMN_ADMINISTRATIVE_GROUP)));
            if (cursor.getInt(cursor.getColumnIndex(DatabaseContract.StudentsTable.COLUMN_IS_BACHELOR)) == 1) {
                student.setBachelor(true);
            } else {
                student.setBachelor(false);
            }

            Thesis thesis = getPickedThesis(db, cursor.getInt(cursor.getColumnIndex(DatabaseContract.StudentsTable.COLUMN_THESIS)));
            if (thesis != null) {
                student.setThesis(thesis.getTitle());
            }
            student.setReviewer(getTeacherName(db, cursor.getInt(cursor.getColumnIndex(DatabaseContract.StudentsTable.COLUMN_REVIEWER))));

            cursor.close();
            db.close();

            return student;
        } else {
            cursor.close();
            db.close();

            return null;
        }
    }

    public static Thesis getPickedThesis(SQLiteDatabase db, int thesisId) {
        Thesis pickedThesis = new Thesis();

        String[] columns = {
                DatabaseContract.ThesesTable.COLUMN_TITLE,
                DatabaseContract.ThesesTable.COLUMN_LEAD
        };

        String selection = DatabaseContract.ThesesTable.COLUMN_THESIS_ID + " = ?";

        String[] selectionArgs = {Integer.toString(thesisId)};

        Cursor cursor = db.query(DatabaseContract.ThesesTable.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor.getCount() > 0) {
            pickedThesis.setTitle(cursor.getString(cursor.getColumnIndex(DatabaseContract.ThesesTable.COLUMN_TITLE)));
            pickedThesis.setLead(getTeacherName(db, cursor.getInt(cursor.getColumnIndex(DatabaseContract.ThesesTable.COLUMN_LEAD))));

            cursor.close();
            db.close();

            return pickedThesis;
        } else {
            cursor.close();
            db.close();

            return null;
        }
    }

    public static String getTeacherName(SQLiteDatabase db, int teacherId) {

        String[] columns = {
                DatabaseContract.TeachersTable.COLUMN_NAME
        };

        String selection = DatabaseContract.TeachersTable.COLUMN_TEACHER_ID + " = ?";

        String[] selectionArgs = {Integer.toString(teacherId)};

        Cursor cursor = db.query(DatabaseContract.TeachersTable.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor.getCount() > 0) {
            String teacherName = cursor.getString(cursor.getColumnIndex(DatabaseContract.TeachersTable.COLUMN_NAME));

            cursor.close();
            db.close();

            return teacherName;
        } else {
            cursor.close();
            db.close();

            return null;
        }
    }
}
