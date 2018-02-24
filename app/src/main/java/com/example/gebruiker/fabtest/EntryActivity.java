package com.example.gebruiker.fabtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EntryActivity extends AppCompatActivity {

    String mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

    }

    /* Sets the mood that will be entered into the database. */
    public void setMood(View view) {

        Log.d("EntryActivity", "in onClick");

        ImageView imageButton = (ImageView) view;
        switch(imageButton.getId()) {
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
                mood = "fantastic";
                break;
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
