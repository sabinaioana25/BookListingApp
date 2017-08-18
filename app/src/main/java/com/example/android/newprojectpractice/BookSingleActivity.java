package com.example.android.newprojectpractice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Sabina on 7/21/2017.
 */

public class BookSingleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_book_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Bundle extras = getIntent().getExtras();

        TextView titleView = (TextView) findViewById(R.id.single_book_title);
        titleView.setText(extras.getString("title"));

        ImageView imageView = (ImageView) findViewById(R.id.single_book_imageView);
        Bitmap image = extras.getParcelable("image");
        imageView.setImageBitmap(image);

        TextView authorView = (TextView) findViewById(R.id.single_book_author);
        authorView.setText(extras.getString("author"));

        TextView textPageCount = (TextView) findViewById(R.id.single_book_page_count);
        textPageCount.setText(extras.getInt("pageCount") + " pages");

        TextView ratingView = (TextView) findViewById(R.id.single_book_rating_average);
        ratingView.setText(extras.getString("averageRating"));

        TextView textRatingsCount = (TextView) findViewById(R.id.single_book_rate_count);
        textRatingsCount.setText(extras.getInt("ratingsCount") + " people rated this");

        TextView textPublisher = (TextView) findViewById(R.id.single_book_publisher);
        textPublisher.setText(extras.getString("publisher") + ", ");

        TextView textPublishedDate = (TextView) findViewById(R.id.single_book_published_date);
        textPublishedDate.setText(extras.getString("publishedDate"));

        final TextView descriptionView = (TextView) findViewById(R.id.single_book_description);
        descriptionView.setText(extras.getString("description"));

        TextView textSnippetView = (TextView) findViewById(R.id.single_book_snippet);
        textSnippetView.setText(extras.getString("textSnippet"));

        final TextView readMore = (TextView) findViewById(R.id.single_book_see_description);

        readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (descriptionView.getVisibility() == View.GONE) {
                    descriptionView.setVisibility(View.VISIBLE);
                    readMore.setText("Read Less");
                } else {
                    descriptionView.setVisibility(View.GONE);
                    readMore.setText("Read More");
                    readMore.setVisibility(View.VISIBLE);
                }
            }
        });

        final Button readOnlineButton = (Button) findViewById(R.id.single_book_button_read);
        final String buttonRead;
        buttonRead = extras.getString("webReaderLink");
        readOnlineButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent readOnlineIntent = new Intent(Intent.ACTION_VIEW);
                readOnlineIntent.setData(Uri.parse(buttonRead));
                startActivity(readOnlineIntent);
            }
        });

        final Button buyBookButton = (Button) findViewById(R.id.single_book_button_buy);
        final String buttonBuy;
        buttonBuy = extras.getString("infoLink");
        buyBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buyIntent = new Intent(Intent.ACTION_VIEW);
                buyIntent.setData(Uri.parse(buttonBuy));
                startActivity(buyIntent);
            }
        });

        final Button navButton = (Button) findViewById(R.id.single_book_button_preview);
        final String buttonNav;
        buttonNav = extras.getString("previewLink");
        navButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent navIntent = new Intent(Intent.ACTION_VIEW);
                navIntent.setData(Uri.parse(buttonNav));
                startActivity(navIntent);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
