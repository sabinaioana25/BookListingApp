package com.example.android.newprojectpractice;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Sabina on 7/12/2017.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private String url;
    public BookLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        if(url == null) {
            return null;
        }

        List<Book> books = QueryUtils.fetchBookData(url);
        return books;
    }
}
