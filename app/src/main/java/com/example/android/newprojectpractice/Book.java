package com.example.android.newprojectpractice;

import android.graphics.Bitmap;

/**
 * Created by Sabina on 7/9/2017.
 */

public class Book {

    private String title;
    private String author;
    private String publisher;
    private String buyBookLink;
    private String webReaderLink;
    private String textSnippet;
    private Bitmap smallImage;
    private Bitmap largeImage;
    private int pageCount;
    private String publishedDate;
    private int averageRating;
    private String previewLink;
    private int ratingsCount;
    private String description;

    public Book(Bitmap smallImage, Bitmap largeImage, String title, int pageCount, String author, String publishedDate, int averageRating, String previewLink,
                String textSnippet, int ratingsCount, String description, String publisher, String webReaderLink, String buyBookLink) {
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
        this.smallImage = smallImage;
        this.largeImage = largeImage;
        this.publisher = publisher;
        this.buyBookLink = buyBookLink;
        this.webReaderLink = webReaderLink;
        this.textSnippet = textSnippet;
        this.publishedDate = publishedDate;
        this.averageRating = averageRating;
        this.previewLink = previewLink;
        this.ratingsCount = ratingsCount;
        this.description = description;
    }

    public String getTitle() { return title; }

    public String getAuthor() {
        return author;
    }

    public Bitmap getSmallImage() { return smallImage; }

    public Bitmap getLargeImage() { return largeImage; }

    public String getPublisher() { return publisher; }
//
    public String getBuyBookLink() { return buyBookLink; }
//
    public String getWebReaderLink() { return webReaderLink; }
//
    public String getTextSnippet() { return textSnippet; }

    public int getPageCount() {
        return pageCount;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public int getAverageRating() {
        return averageRating;
    }

    public String getPreviewLink() { return previewLink; }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public String getDescription() { return description; }
}

