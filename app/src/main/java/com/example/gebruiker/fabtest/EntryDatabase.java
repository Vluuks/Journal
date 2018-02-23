package com.example.gebruiker.fabtest;

/**
 * Created by Gebruiker on 23-2-2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Renske on 22/02/2018.
 * test github 2 trying to commit everything instead of just java
 */

public class EntryDatabase extends SQLiteOpenHelper {

    // Database fields
    private static final String _ID = "_id";
    private static final String TABLE_NAME = "entries";
    private static final String ENTRY_CONTENT = "subject";
    private static final String ENTRY_TITLE = "title";
    private static final String ENTRY_MOOD = "mood";
    private static final String ENTRY_TIMESTAMP = "timestamp";

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ENTRY_TITLE + " TEXT NOT NULL, " +
            ENTRY_CONTENT + " TEXT NOT NULL, " +
            ENTRY_MOOD + "TEXT, " +
            ENTRY_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP" + ");";

    private static EntryDatabase instance;

    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static EntryDatabase getInstance(Context context){

        if(instance == null) {
            instance = new EntryDatabase(context, "com.example.renske.journalapp.EntryDatabase", null, 1);
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);

        ContentValues cv = new ContentValues();
        cv.put(ENTRY_TITLE, "Tim is lief");
        cv.put(ENTRY_CONTENT, "super leuk dit allemaal woehoe");
        cv.put(ENTRY_MOOD, "happy");
        sqLiteDatabase.insert(TABLE_NAME, null, cv);
    }

    @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public Cursor getAllEntries() {
        SQLiteDatabase database = instance.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }
}