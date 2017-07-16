package com.example.android.newprojectpractice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<List<Book>> {

    private static final String LOG_TAG = RelevantBookFragment.class.getName();
    private static final String GB_REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes?q=roman+empire&orderBy=relevance";
    private static final int BOOK_LOADER_ID = 1;
    public BookAdapter bookAdapter;


    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i(LOG_TAG, "Test PopBooksFrag onCreate() is called");

        View rootView = inflater.inflate(R.layout.popular_book_fragment, container, false);
        GridView listGrid = (GridView) rootView.findViewById(R.id.grid_view_list);
        BookAdapter bookAdapter = new BookAdapter(getActivity(), new ArrayList<Book>());
        listGrid.setAdapter(bookAdapter);

        getLoaderManager().initLoader(BOOK_LOADER_ID, null, this);
        return rootView;
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        Log.i(LOG_TAG, "Test PopularBookFragment() is called");
        return new Book2Loader(getActivity(), GB_REQUEST_URL);
//        return new BookLoader(getActivity(), GB_REQUEST_URL);
    }


    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        Log.i(LOG_TAG, "Test PopularBookFragment() is called");
        bookAdapter.clear();
        if (books != null && books.isEmpty()) {
            bookAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        Log.i(LOG_TAG, "Test EarthActivity onLoaderReset() is called");
        bookAdapter.clear();
    }
}
