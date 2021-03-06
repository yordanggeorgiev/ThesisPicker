package com.example.jgeorgiev.thesispicker.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jgeorgiev.thesispicker.models.Student;
import com.example.jgeorgiev.thesispicker.models.Teacher;
import com.example.jgeorgiev.thesispicker.models.Thesis;

import java.util.ArrayList;
import java.util.List;

/**
 * DatabaseUtils containing queries to the SQLiteDatabase
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

        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
            student.setName(cursor.getString(cursor.getColumnIndex(DatabaseContract.StudentsTable.COLUMN_NAME)));
            student.setFacultyNumber(cursor.getInt(cursor.getColumnIndex(DatabaseContract.StudentsTable.COLUMN_FACULTY_NUMBER)));
            student.setSpecialty(cursor.getString(cursor.getColumnIndex(DatabaseContract.StudentsTable.COLUMN_SPECIALTY)));
            student.setAdminGroup(cursor.getInt(cursor.getColumnIndex(DatabaseContract.StudentsTable.COLUMN_ADMINISTRATIVE_GROUP)));
            if (cursor.getInt(cursor.getColumnIndex(DatabaseContract.StudentsTable.COLUMN_IS_BACHELOR)) == 1) {
                student.setBachelor(true);
            } else {
                student.setBachelor(false);
            }
            student.setThesis(getPickedThesis(db, cursor.getInt(cursor.getColumnIndex(DatabaseContract.StudentsTable.COLUMN_THESIS))));
            student.setReviewer(getTeacherName(db, cursor.getInt(cursor.getColumnIndex(DatabaseContract.StudentsTable.COLUMN_REVIEWER))));

            cursor.close();

            return student;
        } else {
            cursor.close();

            return null;
        }
    }

    public static List<Thesis> getAllTheses(SQLiteDatabase db) {

        List<Thesis> thesisList = new ArrayList<>();

        String[] columns = {
                DatabaseContract.ThesesTable.COLUMN_TITLE,
                DatabaseContract.ThesesTable.COLUMN_DETAILS,
                DatabaseContract.ThesesTable.COLUMN_LEAD,
                DatabaseContract.ThesesTable.COLUMN_IS_PICKED
        };

        String sortOrder = DatabaseContract.ThesesTable.COLUMN_LEAD + " ASC";

        Cursor cursor = db.query(DatabaseContract.ThesesTable.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if (cursor.moveToFirst()) {
            do {
                Thesis thesis = new Thesis();
                thesis.setTitle(cursor.getString(cursor.getColumnIndex(DatabaseContract.ThesesTable.COLUMN_TITLE)));
                thesis.setDetails(cursor.getString(cursor.getColumnIndex(DatabaseContract.ThesesTable.COLUMN_DETAILS)));
                thesis.setLead(getTeacherName(db, cursor.getInt(cursor.getColumnIndex(DatabaseContract.ThesesTable.COLUMN_LEAD))));
                if (cursor.getInt(cursor.getColumnIndex(DatabaseContract.ThesesTable.COLUMN_IS_PICKED)) == 1) {
                    thesis.setPicked(true);
                } else {
                    thesis.setPicked(false);
                }

                thesisList.add(thesis);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return thesisList;
    }

    public static List<Teacher> getAllTeachers(SQLiteDatabase db) {

        List<Teacher> teachersList = new ArrayList<>();

        String[] columns = {
                DatabaseContract.TeachersTable.COLUMN_NAME,
                DatabaseContract.TeachersTable.COLUMN_EMAIL,
                DatabaseContract.TeachersTable.COLUMN_PHONE
        };

        String sortOrder = DatabaseContract.TeachersTable.COLUMN_NAME + " ASC";

        Cursor cursor = db.query(DatabaseContract.TeachersTable.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if (cursor.moveToFirst()) {
            do {
                Teacher teacher = new Teacher();
                teacher.setName(cursor.getString(cursor.getColumnIndex(DatabaseContract.TeachersTable.COLUMN_NAME)));
                teacher.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseContract.TeachersTable.COLUMN_EMAIL)));
                teacher.setPhone(cursor.getString(cursor.getColumnIndex(DatabaseContract.TeachersTable.COLUMN_PHONE)));

                teachersList.add(teacher);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return teachersList;
    }

    public static String updateStudentData(SQLiteDatabase db, int facNumber, Thesis thesis) {
        int randomTeacherId = getTeacherId(db, thesis.getLead());

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.StudentsTable.COLUMN_THESIS, getThesisId(db, thesis));
        values.put(DatabaseContract.StudentsTable.COLUMN_REVIEWER, randomTeacherId);

        db.update(DatabaseContract.StudentsTable.TABLE_NAME, values, DatabaseContract.StudentsTable.COLUMN_FACULTY_NUMBER + " = ?",
                new String[]{String.valueOf(facNumber)});

        return getTeacherName(db, randomTeacherId);
    }

    public static void updateThesisData(SQLiteDatabase db, Thesis thesis) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ThesesTable.COLUMN_IS_PICKED, 1);

        db.update(DatabaseContract.ThesesTable.TABLE_NAME, values, DatabaseContract.ThesesTable.COLUMN_TITLE + " = ?",
                new String[]{String.valueOf(thesis.getTitle())});
    }

    public static Teacher getTeacherInfo(SQLiteDatabase db, String teacher) {

        String[] columns = {
                DatabaseContract.TeachersTable.COLUMN_NAME,
                DatabaseContract.TeachersTable.COLUMN_EMAIL,
                DatabaseContract.TeachersTable.COLUMN_PHONE
        };

        String selection = DatabaseContract.TeachersTable.COLUMN_NAME + " = ?";

        String[] selectionArgs = {teacher};

        Cursor cursor = db.query(DatabaseContract.TeachersTable.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
            Teacher resultTeacher = new Teacher();
            resultTeacher.setName(cursor.getString(cursor.getColumnIndex(DatabaseContract.TeachersTable.COLUMN_NAME)));
            resultTeacher.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseContract.TeachersTable.COLUMN_EMAIL)));
            resultTeacher.setPhone(cursor.getString(cursor.getColumnIndex(DatabaseContract.TeachersTable.COLUMN_PHONE)));

            cursor.close();

            return resultTeacher;
        } else {
            cursor.close();
            return null;
        }
    }

    public static Thesis getThesisInfo(SQLiteDatabase db, Thesis thesis) {

        String[] columns = {
                DatabaseContract.ThesesTable.COLUMN_TITLE,
                DatabaseContract.ThesesTable.COLUMN_DETAILS,
                DatabaseContract.ThesesTable.COLUMN_LEAD,
                DatabaseContract.ThesesTable.COLUMN_IS_PICKED
        };

        String selection = DatabaseContract.ThesesTable.COLUMN_TITLE + " = ?";

        String[] selectionArgs = {thesis.getTitle()};

        Cursor cursor = db.query(DatabaseContract.ThesesTable.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
            Thesis resultThesis = new Thesis();
            resultThesis.setTitle(cursor.getString(cursor.getColumnIndex(DatabaseContract.ThesesTable.COLUMN_TITLE)));
            resultThesis.setDetails(cursor.getString(cursor.getColumnIndex(DatabaseContract.ThesesTable.COLUMN_DETAILS)));
            resultThesis.setLead(getTeacherName(db, cursor.getInt(cursor.getColumnIndex(DatabaseContract.ThesesTable.COLUMN_LEAD))));
            if (cursor.getInt(cursor.getColumnIndex(DatabaseContract.ThesesTable.COLUMN_IS_PICKED)) == 1) {
                resultThesis.setPicked(true);
            } else {
                resultThesis.setPicked(false);
            }

            cursor.close();

            return resultThesis;
        } else {
            cursor.close();
            return null;
        }
    }


    private static Thesis getPickedThesis(SQLiteDatabase db, int thesisId) {
        Thesis pickedThesis = new Thesis();

        String[] columns = {
                DatabaseContract.ThesesTable.COLUMN_TITLE,
                DatabaseContract.ThesesTable.COLUMN_DETAILS,
                DatabaseContract.ThesesTable.COLUMN_LEAD,
                DatabaseContract.ThesesTable.COLUMN_IS_PICKED
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

        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
            pickedThesis.setTitle(cursor.getString(cursor.getColumnIndex(DatabaseContract.ThesesTable.COLUMN_TITLE)));
            pickedThesis.setDetails(cursor.getString(cursor.getColumnIndex(DatabaseContract.ThesesTable.COLUMN_DETAILS)));
            pickedThesis.setLead(getTeacherName(db, cursor.getInt(cursor.getColumnIndex(DatabaseContract.ThesesTable.COLUMN_LEAD))));
            if (cursor.getInt(cursor.getColumnIndex(DatabaseContract.ThesesTable.COLUMN_DETAILS)) == 1) {
                pickedThesis.setPicked(true);
            } else {
                pickedThesis.setPicked(false);
            }
            cursor.close();

            return pickedThesis;
        } else {
            cursor.close();

            return null;
        }
    }

    private static String getTeacherName(SQLiteDatabase db, int teacherId) {

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

        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
            String teacherName = cursor.getString(cursor.getColumnIndex(DatabaseContract.TeachersTable.COLUMN_NAME));

            cursor.close();

            return teacherName;
        } else {
            cursor.close();

            return null;
        }
    }

    private static int getThesisId(SQLiteDatabase db, Thesis thesis) {

        String[] columns = {
                DatabaseContract.ThesesTable.COLUMN_THESIS_ID
        };

        String selection = DatabaseContract.ThesesTable.COLUMN_TITLE + " = ?";

        String[] selectionArgs = {thesis.getTitle()};

        Cursor cursor = db.query(DatabaseContract.ThesesTable.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
            int thesisId = cursor.getInt(cursor.getColumnIndex(DatabaseContract.ThesesTable.COLUMN_THESIS_ID));
            cursor.close();

            return thesisId;
        } else {
            cursor.close();

            return 0;
        }
    }

    private static int getTeacherId(SQLiteDatabase db, String teacher) {

        Cursor cursor = db.rawQuery(DatabaseContract.SQL_SELECT_RANDOM_TEACHER, new String[]{teacher});

        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
            int teacherId = cursor.getInt(cursor.getColumnIndex(DatabaseContract.TeachersTable.COLUMN_TEACHER_ID));
            cursor.close();

            return teacherId;
        } else {
            cursor.close();

            return 0;
        }
    }
}
