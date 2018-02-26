package com.example.gebruiker.fabtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/*
    Allows the user to view the details of a journal entry, including content.
*/
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Entry entry = (Entry) intent.getSerializableExtra("entry");

        if(entry != null) {
            updateUI(entry);
        }
        else {
            finish();
        }
    }

    public void updateUI(Entry entry) {
        ((TextView)findViewById(R.id.tvDetailTitle)).setText(entry.getTitle());
        ((TextView)findViewById(R.id.tvDetailTimestamp)).setText(entry.getTimestamp());
        ((TextView)findViewById(R.id.tvDetailMood)).setText(entry.getMood());
        ((TextView)findViewById(R.id.tvDetailContent)).setText(entry.getContent());
    }

    public void addToFavourites(View view) {

        CheckBox c = (CheckBox) view;

        if (c.isChecked()) {
            // add to favs
        }
        else {
            // remove from favs
        }
    }
}
