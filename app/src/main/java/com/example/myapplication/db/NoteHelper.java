package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.entity.Note;
import static android.provider.BaseColumns._ID;
import static com.example.myapplication.db.DatabaseContract.NoteColumns.DATE;
import static com.example.myapplication.db.DatabaseContract.NoteColumns.DESCRIPTION;
import static com.example.myapplication.db.DatabaseContract.NoteColumns.TABLE_NOTE;
import static com.example.myapplication.db.DatabaseContract.NoteColumns.TITLE;

import java.util.ArrayList;

public class NoteHelper {
    private static final String DATABASE_TABLE = TABLE_NOTE;
    private static DatabaseHelper databaseHelper;
    private static NoteHelper INSTANCE;

    private static SQLiteDatabase database;

    private NoteHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static NoteHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NoteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();

        if (database.isOpen()) {
            database.close();
        }
    }

    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null
                , null
                , null
                , null
                , null
                , _ID + " ASC",
                null);
        cursor.moveToFirst();
        Note note;
        if (cursor.getCount() > 0) {
            do {
                note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID)));
                note.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.TITLE)));
                note.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION)));
                note.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.DATE)));

                arrayList.add(note);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertNote(Note note) {
        ContentValues args = new ContentValues();
        args.put(TITLE, note.getTitle());
        args.put(DESCRIPTION, note.getDescription());
        args.put(DATE, note.getDate());
        return database.insert(DATABASE_TABLE, null, args);
    }

    public int updateNote(Note note) {
        ContentValues args = new ContentValues();
        args.put(TITLE, note.getTitle());
        args.put(DESCRIPTION, note.getDescription());
        args.put(DATE, note.getDate());
        return database.update(DATABASE_TABLE, args, _ID + "= '" + note.getId() + "'", null);
    }

    public int deleteNote(int id) {
        return database.delete(TABLE_NOTE, _ID + " = '" + id + "'", null);
    }

}
