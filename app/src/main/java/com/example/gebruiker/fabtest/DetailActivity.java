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

    Entry entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        entry = (Entry) intent.getSerializableExtra("entry");

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
        ((CheckBox)findViewById((R.id.favouriteCheck))).setChecked(entry.isFavourite() == 1);
    }

    public void addToFavourites(View view) {

        CheckBox c = (CheckBox) view;
        int isFav = 0;
        if(c.isChecked()) {
            isFav = 1;
        }
        EntryDatabase.getInstance(getApplicationContext()).toggleFavourite(entry.getId(),  isFav);


    }

    // TODO USE ENUMS
}