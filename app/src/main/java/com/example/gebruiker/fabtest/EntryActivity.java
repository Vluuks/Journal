package com.example.gebruiker.fabtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

/*
    Allows the user to create a journal entry with title, contents and a mood.
*/
public class EntryActivity extends AppCompatActivity {

    String mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        if(getIntent().getBooleanExtra("EDIT_MODE", false)) {
            Log.d("TEST", "onCreate: should be recreated");

            // Update UI
            Entry entry = (Entry) getIntent().getSerializableExtra("ENTRY_TO_EDIT");
            ((EditText) findViewById(R.id.etTitle)).setText(entry.getTitle());
            ((EditText) findViewById(R.id.etContent)).setText(entry.getContent());

            restoreMood(entry.getMood());
        }

    }

    /*
        Resets the selected mood if we are restoring from an existing entry.
     */
    public void restoreMood(String mood) {

        int id;
        switch(mood) {
            case "sad" :
                id = R.id.mood1;
                break;
            case "meh" :
                id = R.id.mood2;
                break;
            case "good" :
                id = R.id.mood3;
                break;
            case "great" :
                id = R.id.mood4;
                break;
            default :
                id = R.id.mood4;
        }

        ((ImageButton)findViewById(id)).setBackground(getResources().getDrawable(R.drawable.button_mood_selected));
    }

    public void setMood(View view) {

        ImageButton imageButton = (ImageButton) view;
        int id = imageButton.getId();

        resetButtonColors(id);
        imageButton.setBackground(getResources().getDrawable(R.drawable.button_mood_selected));

        switch(id) {
            case R.id.mood1:
                mood = "sad";
                break;
            case R.id.mood2:
                mood = "meh";
                break;
            case R.id.mood3:
                mood = "good";
                break;
            case R.id.mood4:
                mood = "great";
                break;
        }
    }

    /* Method that resets all buttons to original color except the one that was clicked. */
    public void resetButtonColors(int id) {

        LinearLayout layout = (LinearLayout) findViewById(R.id.moodLayout);

        for (int i = 0; i < layout.getChildCount(); i++) {
            ImageButton b = (ImageButton) layout.getChildAt(i);

            if (b.getId() != id) {
                b.setBackground(getResources().getDrawable(R.drawable.button_mood));
            }
        }
    }

    public boolean validateEntry(String title, String content, String mood) {

        if(TextUtils.isEmpty(title) || TextUtils.isEmpty(content) || TextUtils.isEmpty(mood)) {
            Toast toast = Toast.makeText(this, "Please fill in all fields and select a mood", Toast.LENGTH_SHORT);
            toast.show();

            return false;
        }
        return true;
    }

    public void submitEntry(View view) {

        String title = ((EditText)findViewById(R.id.etTitle)).getText().toString();
        String content = ((EditText)findViewById(R.id.etContent)).getText().toString();

        if (validateEntry(title, content, mood)) {
            Entry entry = new Entry(title, content, mood);
            EntryDatabase database = EntryDatabase.getInstance(this);
            database.insert(entry);

            finish();
        }
    }
}
