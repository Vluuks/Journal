package com.example.gebruiker.fabtest;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.ResourceCursorAdapter;
import android.view.View;

/**
 * Created by Renske on 23/02/2018.
 */

public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, int layout, Cursor c) {
        super(context, layout, c);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}
