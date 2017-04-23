package com.example.jgeorgiev.thesispicker.data;

import android.provider.BaseColumns;

/**
 * Created by jgeorgiev on 4/23/17.
 */
class DatabaseContract {

    private DatabaseContract() {
    }

    static final class StudentsTable implements BaseColumns {
        static final String TABLE_NAME = "students";
        static final String COLUMN_STUDENT_ID = "student_id";
        static final String COLUMN_NAME = "name";
        static final String COLUMN_FACULTY_NUMBER = "faculty_number";
        static final String COLUMN_PASSWORD = "password";
        static final String COLUMN_THESIS = "thesis";
        static final String COLUMN_IS_FINALIZED = "is_finalized";
    }

    static final class TeachersTable implements BaseColumns {
        static final String TABLE_NAME = "teachers";
        static final String COLUMN_TEACHER_ID = "teacher_id";
        static final String COLUMN_NAME = "name";
        static final String COLUMN_EMAIL = "email";
        static final String COLUMN_PHONE = "phone";
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
