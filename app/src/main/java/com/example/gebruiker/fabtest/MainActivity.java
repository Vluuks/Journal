package com.example.gebruiker.fabtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

/* Activity that contains a list of entries and allows users to create new
   entries through
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = findViewById(R.id.listView);
    }

    public void addEntry(View view) {
        Intent intent = new Intent(MainActivity.this, EntryActivity.class);
        startActivity(intent);
    }
}
