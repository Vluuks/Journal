package com.example.gebruiker.fabtest;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.ResourceCursorAdapter;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Renske on 23/02/2018.
 */

public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, int layout, Cursor cursor) {
        super(context, layout, cursor);
    }


//    Use Cursor.getInt(columnIndex) to retrieve the value of one column as an integer.
//    Use Cursor.getColumnIndex(name) to get the column index for a column named name.
//    Call view.findViewById() to get references to the controls in the row layout.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvTimestamp = view.findViewById(R.id.tvTimestamp);
        TextView tvMood = view.findViewById(R.id.tvMood);

        String title = cursor.getString(cursor.getColumnIndex(EntryDatabase.ENTRY_TITLE));
        String timestamp = cursor.getString(cursor.getColumnIndex(EntryDatabase.ENTRY_TIMESTAMP));
        String mood = cursor.getString(cursor.getColumnIndex(EntryDatabase.ENTRY_MOOD));

        tvTitle.setText(title);
        tvTimestamp.setText(timestamp);
        tvMood.setText(mood);
    }
}
