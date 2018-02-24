package com.example.gebruiker.fabtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Renske on 22/02/2018.
 * test github 2 trying to commit everything instead of just java
 */

public class EntryDatabase extends SQLiteOpenHelper {

    // Database fields
    public static final String _ID = "_id";
    public static final String TABLE_NAME = "entries";
    public static final String ENTRY_CONTENT = "subject";
    public static final String ENTRY_TITLE = "title";
    public static final String ENTRY_MOOD = "mood";
    public static final String ENTRY_TIMESTAMP = "timestamp";

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ENTRY_TITLE + " TEXT NOT NULL, " +
            ENTRY_MOOD + " TEXT NOT NULL, " +
            ENTRY_CONTENT + " TEXT NOT NULL, " +
            ENTRY_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP" + ");";

    private static EntryDatabase instance;

    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static EntryDatabase getInstance(Context context) {

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

        cv.put(ENTRY_TITLE, "Hoi wat leuk!");
        cv.put(ENTRY_CONTENT, "lekker geluncht");
        cv.put(ENTRY_MOOD, "happy");

        sqLiteDatabase.insert(TABLE_NAME, null, cv);

    }

    @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        Log.d("EntryDatabase", "called upgrade");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void upgradeDB() {
        SQLiteDatabase database = instance.getReadableDatabase();
        onUpgrade(database, 1, 31);
        Log.d("EntryDatabase", Integer.toString(database.getVersion()));
    }

    public Cursor getAllEntries() {

        SQLiteDatabase database = instance.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);
        Log.d("test", cursor.toString());
        return cursor;
    }
}