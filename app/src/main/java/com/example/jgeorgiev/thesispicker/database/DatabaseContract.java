package com.example.jgeorgiev.thesispicker.database;

import android.provider.BaseColumns;

/**
 * Created by jgeorgiev on 4/23/17.
 */
public class DatabaseContract {

    private DatabaseContract() {
    }

    public static final class StudentsTable implements BaseColumns {
        public static final String TABLE_NAME = "students";
        public static final String COLUMN_STUDENT_ID = "student_id";
        public static final String COLUMN_NAME = "student_name";
        public static final String COLUMN_FACULTY_NUMBER = "faculty_number";
        public static final String COLUMN_SPECIALTY = "specialty";
        public static final String COLUMN_ADMINISTRATIVE_GROUP = "administrative_group";
        public static final String COLUMN_IS_BACHELOR = "is_bachelor";
        public static final String COLUMN_EGN = "egn";
        public static final String COLUMN_THESIS = "thesis";
        public static final String COLUMN_REVIEWER = "reviewer";
    }

    public static final class TeachersTable implements BaseColumns {
        public static final String TABLE_NAME = "teachers";
        public static final String COLUMN_TEACHER_ID = "teacher_id";
        public static final String COLUMN_NAME = "teacher_name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PHONE = "phone";
    }

    public static final class ThesesTable implements BaseColumns {
        public static final String TABLE_NAME = "theses";
        public static final String COLUMN_THESIS_ID = "thesis_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DETAILS = "details";
        public static final String COLUMN_LEAD = "lead";
        public static final String COLUMN_IS_PICKED = "is_picked";
    }
}

