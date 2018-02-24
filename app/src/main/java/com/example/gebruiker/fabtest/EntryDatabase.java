package com.example.gebruiker.fabtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/*
    Helper class for database that stores all entries, with their mood,
    timestamp, title and content.
*/
public class EntryDatabase extends SQLiteOpenHelper {

    // Database fields
    public static final String ENTRY_ID = "_id";
    public static final String TABLE_NAME = "entries";
    public static final String ENTRY_CONTENT = "subject";
    public static final String ENTRY_TITLE = "title";
    public static final String ENTRY_MOOD = "mood";
    public static final String ENTRY_TIMESTAMP = "entrytimestamp";

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ENTRY_TITLE + " TEXT NOT NULL, " +
            ENTRY_MOOD + " TEXT NOT NULL, " +
            ENTRY_CONTENT + " TEXT NOT NULL, " +
            ENTRY_TIMESTAMP + " DATETIME DEFAULT (DATETIME(CURRENT_TIMESTAMP, 'LOCALTIME'))" + ");";

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
        cv.put(ENTRY_MOOD, "great");

        sqLiteDatabase.insert(TABLE_NAME, null, cv);

        cv.put(ENTRY_TITLE, "Hoi wat leuk!");
        cv.put(ENTRY_CONTENT, "dit is een test entry");
        cv.put(ENTRY_MOOD, "good");

        sqLiteDatabase.insert(TABLE_NAME, null, cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void upgradeDB() {
        SQLiteDatabase database = instance.getReadableDatabase();
        onUpgrade(database, database.getVersion(), database.getVersion() + 1);
    }

    public Cursor getAllEntries() {

        SQLiteDatabase database = instance.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + ENTRY_ID + " DESC";
        Cursor cursor = database.rawQuery(query, null);
        Log.d("test", cursor.toString());
        return cursor;
    }

    public void insert(Entry entry) {

        ContentValues cv = new ContentValues();
        cv.put(ENTRY_TITLE, entry.getTitle());
        cv.put(ENTRY_CONTENT, entry.getContent());
        cv.put(ENTRY_MOOD, entry.getMood());

        SQLiteDatabase database = instance.getWritableDatabase();
        database.insert(TABLE_NAME, null, cv);
    }

    public void delete(int id) {

        SQLiteDatabase database = instance.getWritableDatabase();
        String whereClause = ENTRY_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(id) };
        database.delete(TABLE_NAME, whereClause, whereArgs);
    }
}