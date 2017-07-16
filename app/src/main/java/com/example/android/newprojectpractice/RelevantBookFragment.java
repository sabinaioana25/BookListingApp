package com.example.android.newprojectpractice;


import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
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

public class RelevantBookFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<List<Book>> {

    private static final String LOG_TAG = RelevantBookFragment.class.getName();
    private static final String GB_REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes?q=roman+empire&orderBy=relevance";
    private static final int BOOK_LOADER_ID = 1;
    public BookAdapter bookAdapter;

    public RelevantBookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(LOG_TAG, "Test PopBooksFrag onCreate() is called");
        View rootView = inflater.inflate(R.layout.popular_book_fragment, container, false);

        BookAdapter bookAdapter = new BookAdapter(getActivity(), new ArrayList<Book>());
        GridView listGrid = (GridView) rootView.findViewById(R.id.grid_view_list);
        listGrid.setAdapter(bookAdapter);

        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connMgr.getActiveNetworkInfo();

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(BOOK_LOADER_ID, null, this);

        return rootView;
    }

    @Override
    public android.content.Loader<List<Book>> onCreateLoader(int id, Bundle bundle) {
        Log.i(LOG_TAG, "Test PopularBookFragment() is called");
        return new BookLoader(getActivity(), GB_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<Book>> loader, List<Book> books) {
        Log.i(LOG_TAG, "Test PopularBookFragment() is called");
        bookAdapter.clear();
        if (books != null && books.isEmpty()) {
            bookAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<Book>> loader) {
        Log.i(LOG_TAG, "Test EarthActivity onLoaderReset() is called");
        bookAdapter.clear();
    }
}
