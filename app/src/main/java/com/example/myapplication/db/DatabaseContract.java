package com.example.myapplication.db;

import android.provider.BaseColumns;

public class DatabaseContract {

    static final class NoteColumns implements BaseColumns {
        static String TABLE_NOTE = "note";
        static String TITLE = "title";
        static String DESCRIPTION = "description";
        static String DATE = "date";
    }
}
