package com.example.myapplication.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    public static final String AUTHORITY = "con.example.myapplication";
    private static final String SCHEME = "content";

    static final class NoteColumns implements BaseColumns {
        static String TABLE_NOTE = "note";
        static String TITLE = "title";
        static String DESCRIPTION = "description";
        static String DATE = "date";

        static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NOTE)
                .build();
    }

    static String getColumnString(Cursor cursor, String columnName){
        return cursor.getString(cursor.getColumnIndexOrThrow(columnName));
    }

    static int getColumnInt(Cursor cursor, String columnName){
        return cursor.getInt(cursor.getColumnIndexOrThrow(columnName));
    }

    static long getColumnLong(Cursor cursor, String columnName){
        return cursor.getLong(cursor.getColumnIndexOrThrow(columnName));
    }
}
