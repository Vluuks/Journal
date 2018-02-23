package com.example.gebruiker.fabtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

    }

    /* Sets the mood that will be entered into the database. */
    public void setMood(View view) {

        ImageView imageButton = (ImageView) view;
        switch(imageButton.getId()) {

            case R.id.mood1:

                break;
            case R.id.mood2:

                break;
            case R.id.mood3:

                break;
            case R.id.mood4:

                break;
        }
    }
}
