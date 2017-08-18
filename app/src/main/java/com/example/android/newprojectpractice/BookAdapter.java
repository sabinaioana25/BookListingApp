package com.example.android.newprojectpractice;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Sabina on 7/9/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(Activity context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {

        View bookView = convertView;

        if (bookView == null) {
            bookView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item_detail, parent, false);
        }

        final Book bookDetail = getItem(position);
        final String averageRated = averageRatingVar(bookDetail.getAverageRating());
        final String rateCount = ratingsCountVar(bookDetail.getRatingsCount());

        ImageView image = (ImageView) bookView.findViewById(R.id.book_image);
        image.setImageBitmap(bookDetail.getSmallImage());

        bookView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToBookSingleActivity = new Intent(getContext(), BookSingleActivity.class);
                goToBookSingleActivity.putExtra("title", bookDetail.getTitle());
                goToBookSingleActivity.putExtra("author", bookDetail.getAuthor());
                goToBookSingleActivity.putExtra("publisher", bookDetail.getPublisher());
                goToBookSingleActivity.putExtra("infoLink", bookDetail.getBuyBookLink());
                goToBookSingleActivity.putExtra("webReaderLink", bookDetail.getWebReaderLink());
                goToBookSingleActivity.putExtra("textSnippet", bookDetail.getTextSnippet());
                goToBookSingleActivity.putExtra("pageCount", bookDetail.getPageCount());
                goToBookSingleActivity.putExtra("image", bookDetail.getLargeImage());
                goToBookSingleActivity.putExtra("publishedDate", bookDetail.getPublishedDate());
                goToBookSingleActivity.putExtra("averageRating",averageRated);
                goToBookSingleActivity.putExtra("ratingsCount", rateCount);
                goToBookSingleActivity.putExtra("description", bookDetail.getDescription());
                goToBookSingleActivity.putExtra("previewLink", bookDetail.getPreviewLink());
                getContext().startActivity(goToBookSingleActivity);
            }
        });
        return bookView;
    }

    private String averageRatingVar(int averageRating) {
        DecimalFormat rating = new DecimalFormat("0");
        return rating.format(averageRating);
    }

    private String ratingsCountVar (int ratingsCount) {
        DecimalFormat ratingCount = new DecimalFormat("0");
        return ratingCount.format(ratingsCount);
    }
}
