package com.example.jgeorgiev.thesispicker.database;

import android.provider.BaseColumns;

/**
 * DatabaseContract for the SQLiteDatabase
 * Created by jgeorgiev on 4/23/17.
 */
class DatabaseContract {

    private DatabaseContract() {
    }

    static final String SQL_SELECT_RANDOM_TEACHER = "SELECT " + TeachersTable.COLUMN_TEACHER_ID + " FROM " + DatabaseContract.TeachersTable.TABLE_NAME +
            " WHERE " + TeachersTable.COLUMN_NAME + " != ? " + " ORDER BY RANDOM() LIMIT 1";

    static final class StudentsTable implements BaseColumns {
        static final String TABLE_NAME = "students";
        static final String COLUMN_STUDENT_ID = "student_id";
        static final String COLUMN_NAME = "student_name";
        static final String COLUMN_FACULTY_NUMBER = "faculty_number";
        static final String COLUMN_SPECIALTY = "specialty";
        static final String COLUMN_ADMINISTRATIVE_GROUP = "administrative_group";
        static final String COLUMN_IS_BACHELOR = "is_bachelor";
        static final String COLUMN_EGN = "egn";
        static final String COLUMN_THESIS = "thesis";
        static final String COLUMN_REVIEWER = "reviewer";
    }

    static final class TeachersTable implements BaseColumns {
        static final String TABLE_NAME = "teachers";
        static final String COLUMN_TEACHER_ID = "teacher_id";
        static final String COLUMN_EMAIL = "email";
        static final String COLUMN_PHONE = "phone";
        static final String COLUMN_NAME = "teacher_name";
    }

    static final class ThesesTable implements BaseColumns {
        static final String TABLE_NAME = "theses";
        static final String COLUMN_THESIS_ID = "thesis_id";
        static final String COLUMN_TITLE = "title";
        static final String COLUMN_DETAILS = "details";
        static final String COLUMN_LEAD = "lead";
        static final String COLUMN_IS_PICKED = "is_picked";
    }
}

