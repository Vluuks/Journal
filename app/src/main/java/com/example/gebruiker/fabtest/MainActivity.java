package com.example.gebruiker.fabtest;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/*
    Activity that contains a list of entries and allows users to create new
    entries through the floating action button.
 */
public class MainActivity extends AppCompatActivity {

    EntryAdapter adapter;
    EntryDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = EntryDatabase.getInstance(this);
        Cursor cursor = database.getAllEntries();

        adapter = new EntryAdapter(this, R.layout.row_entry, cursor);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new EntryClickListener());
        listView.setOnItemLongClickListener(new EntryLongClickListener());
    }

    public void addEntry(View view) {
        Intent intent = new Intent(MainActivity.this, EntryActivity.class);
        intent.putExtra("EDIT_MODE", false);
        startActivity(intent);
    }

    public void updateListView() {
        Cursor cursor = database.getAllEntries();
        adapter.swapCursor(cursor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateListView();
    }

    private class EntryClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);

            int id = cursor.getInt(cursor.getColumnIndex(EntryDatabase.ENTRY_ID));
            int favourite = cursor.getInt(cursor.getColumnIndex(EntryDatabase.ENTRY_FAVOURITE));
            String title = cursor.getString(cursor.getColumnIndex(EntryDatabase.ENTRY_TITLE));
            String content = cursor.getString(cursor.getColumnIndex(EntryDatabase.ENTRY_CONTENT));
            String timestamp = cursor.getString(cursor.getColumnIndex(EntryDatabase.ENTRY_TIMESTAMP));
            String mood = cursor.getString(cursor.getColumnIndex(EntryDatabase.ENTRY_MOOD));


            Entry entry = new Entry(id, title, content, mood, timestamp, favourite);

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("entry", entry);
            startActivity(intent);
        }
    }

    private class EntryLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
            int id = cursor.getInt(cursor.getColumnIndex(EntryDatabase.ENTRY_ID));

            database.delete(id);
            updateListView();

            return true;
        }
    }
}
