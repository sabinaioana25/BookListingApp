package com.example.android.newprojectpractice;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sabina on 7/9/2017.
 */

public class BookAdapter  extends ArrayAdapter<Book> {
    public BookAdapter(Activity context, ArrayList<Book> books) {
        super(context, 0, books);
    }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View bookView = convertView;

            if (bookView == null) {
                bookView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item_detail, parent, false);
            }

            Book bookDetail = getItem(position);

            TextView title = (TextView) bookView.findViewById(R.id.book_title);
            title.setText(bookDetail.getTitle());

//            TextView author = (TextView) bookView.findViewById(R.id.book_author);
//            author.setText(bookDetail.getAuthor());

//            ImageView image = (ImageView) bookView.findViewById(R.id.book_image);

            return bookView;
    }
}
