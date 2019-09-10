package com.example.gebruiker.fabtest;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.ResourceCursorAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/*
    Adapter which takes the cursor with all entries and renders mood,
    timestamp and title. Shows an image corresponding to mood as well.
*/
public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, int layout, Cursor cursor) {
        super(context, layout, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvTimestamp = view.findViewById(R.id.tvTimestamp);
        TextView tvMood = view.findViewById(R.id.tvMood);
        ImageView ivMood = view.findViewById(R.id.imageView);

        String title = cursor.getString(cursor.getColumnIndex(EntryDatabase.ENTRY_TITLE));
        String timestamp = cursor.getString(cursor.getColumnIndex(EntryDatabase.ENTRY_TIMESTAMP));
        String mood = cursor.getString(cursor.getColumnIndex(EntryDatabase.ENTRY_MOOD));
        int favourite = cursor.getInt(cursor.getColumnIndex(EntryDatabase.ENTRY_FAVOURITE));

        switch(mood) {
            case "sad":
                ivMood.setImageResource(R.drawable.sad);
                break;
            case "meh":
                ivMood.setImageResource(R.drawable.meh);
                break;
            case "good":
                ivMood.setImageResource(R.drawable.good);
                break;
            case "great":
                ivMood.setImageResource(R.drawable.great);
                break;

        }

        tvTitle.setText(title);
        tvTimestamp.setText(timestamp);
        tvMood.setText(mood);
    }
}
