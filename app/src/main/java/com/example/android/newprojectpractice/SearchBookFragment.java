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
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchBookFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<List<Book>> {

    private String QUERY;
    public static final String GB_REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes?maxResults=40&orderBy=newest&q=";
    private static final String LOG_TAG = SearchBookFragment.class.getName();
    private static final int BOOK_LOADER_ID = 1;
    public BookAdapter bookAdapter;
    private SearchView searchView;
    private TextView emptyStateView;
    private ProgressBar progressBar;

    public SearchBookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_search_book, container, false);
        final GridView listGrid = (GridView) rootView.findViewById(R.id.search_grid_view_list);

        searchView = (SearchView) rootView.findViewById(R.id.search_view);

        bookAdapter = new BookAdapter(getActivity(), new ArrayList<Book>());
        listGrid.setAdapter(bookAdapter);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);

        emptyStateView = (TextView) rootView.findViewById(R.id.empty_view);
        listGrid.setEmptyView(emptyStateView);

        if (isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);

        } else {
            progressBar.setVisibility(View.GONE);
            emptyStateView.setText("There is not internet connection");
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                if (isConnected()) {
                    listGrid.setVisibility(View.INVISIBLE);
                    emptyStateView.setVisibility(View.GONE);

                    QUERY = searchView.getQuery().toString();
                    QUERY = QUERY.replace(" ", "+");
                    Log.e(LOG_TAG, QUERY);
                    progressBar.setVisibility(View.VISIBLE);
                    getLoaderManager().restartLoader(BOOK_LOADER_ID, null, SearchBookFragment.this);
                    searchView.clearFocus();
                } else {
                    listGrid.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.GONE);
                    emptyStateView.setVisibility(View.INVISIBLE);
                    emptyStateView.setText("No Internet Connection");
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return rootView;
    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle bundle) {
        String requestUrl = "";
        if (QUERY != null && QUERY != "") {
            requestUrl = GB_REQUEST_URL + QUERY;
        } else {
            String defaultQuery = "android";
            requestUrl = GB_REQUEST_URL + defaultQuery;
        }
        return new BookLoader(getActivity(), requestUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        bookAdapter.clear();
        emptyStateView.setText("No books found");
        progressBar.setVisibility(View.GONE);
        if (books != null && !books.isEmpty()) {
            bookAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        bookAdapter.clear();
    }
}
