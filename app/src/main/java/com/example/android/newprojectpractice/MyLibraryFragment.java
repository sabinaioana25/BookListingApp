package com.example.android.newprojectpractice;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyLibraryFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<List<Book>> {

    private static final String LOG_TAG = MyLibraryFragment.class.getName();
    public static final String GB_REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes?maxResults=40&orderBy=relevance&q=pizza";
    private static final int BOOK_LOADER_ID = 1;
    public BookAdapter bookAdapter;
    private TextView emptyStateView;
    private ProgressBar progressBar;

    public MyLibraryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_my_library, container, false);
        final GridView listGrid = (GridView) rootView.findViewById(R.id.library_grid_view_list);
        bookAdapter = new BookAdapter(getActivity(), new ArrayList<Book>());
        listGrid.setAdapter(bookAdapter);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        emptyStateView = (TextView) rootView.findViewById(R.id.empty_view);
        listGrid.setEmptyView(emptyStateView);

        if (isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);

        } else {
            progressBar.setVisibility(View.GONE);
            emptyStateView.setText("There is not internet connection");
        }

//        getLoaderManager().initLoader(BOOK_LOADER_ID, null, this).forceLoad();
        return rootView;
    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        Log.e(LOG_TAG, "Test PopularBookFragment() is called");
        return new BookLoader(getActivity(), GB_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        Log.e(LOG_TAG, "Test PopularBookFragment() is called");
        emptyStateView.setText("No books found");
        progressBar.setVisibility(View.GONE);

        if (books != null && !books.isEmpty()) {
            bookAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        Log.e(LOG_TAG, "Test Activity onLoaderReset() is called");
        bookAdapter.clear();
    }
}
