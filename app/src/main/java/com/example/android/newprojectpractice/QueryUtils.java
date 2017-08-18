package com.example.android.newprojectpractice;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sabina on 7/12/2017.
 */

public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();


    private QueryUtils() {
    }

    public static List<Book> fetchBookData(String requestUrl) {
        Log.i(LOG_TAG, "Test fetchBookData() is called");
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<Book> books = extractFeatureFromJson(jsonResponse);
        return books;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }

        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the book JSON result.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return jsonResponse;
        }
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static Bitmap bmp = null;
    private static Bitmap bmp2 = null;
    private static String title;
    private static String buyBookLink;
    private static String webReadLink;
    private static String bookTextSnippet;
    private static String authorName;
    private static JSONArray author;
    private static String bookPublisher;
    private static String publishDate;
    private static int pageCount;
    private static String smallImageLink;
    private static String largeImageLink;
    private static int averageRating;
    private static String previewLink;
    private static int ratingsCount;
    private static String description;

//    private static double averageRating;

    public static List<Book> extractFeatureFromJson(String bookJSON) {
        if (TextUtils.isEmpty(bookJSON)) {
            return null;
        }
        List<Book> books;
        books = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(bookJSON);
            JSONArray bookArray = baseJsonResponse.getJSONArray("items");

            for (int i = 0; i < bookArray.length(); i++) {
                JSONObject currentBook = bookArray.getJSONObject(i);
                JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");
                JSONObject accessInfo = currentBook.getJSONObject("accessInfo");
                JSONObject searchInfo = currentBook.getJSONObject("searchInfo");

                title = volumeInfo.getString("title");

                if (searchInfo.has("textSnippet")) {
                    bookTextSnippet = searchInfo.getString("textSnippet");
                } else {
                    break;
                }

                if (volumeInfo.has("authors")) {
                    author = volumeInfo.getJSONArray("authors");
                    authorName = author.getString(0);
                }

                if (volumeInfo.has("publisher")) {
                    bookPublisher = volumeInfo.getString("publisher");
                }

                if (volumeInfo.has("description")) {
                    description = volumeInfo.getString("description");
                }

                if (volumeInfo.has("publishedDate")) {
                    publishDate = volumeInfo.getString("publishedDate");
                }

                if (volumeInfo.has("previewLink")) {
                    previewLink = volumeInfo.getString("previewLink");
                }

                if (accessInfo.has("webReaderLink")) {
                    webReadLink = accessInfo.getString("webReaderLink");
                }

                if (volumeInfo.has("infoLink")) {
                    buyBookLink = volumeInfo.getString("infoLink");
                }

                if (volumeInfo.has("averageRating")) {
                    averageRating = volumeInfo.getInt("averageRating");
                }

                if (volumeInfo.has("pageCount")) {
                    pageCount = volumeInfo.getInt("pageCount");
                }

                if (volumeInfo.has("ratingsCount")) {
                    ratingsCount = volumeInfo.getInt("ratingsCount");
                }
                if (volumeInfo.has("imageLinks")) {
                    JSONObject image = volumeInfo.getJSONObject("imageLinks");
                    smallImageLink = image.getString("smallThumbnail");
                    largeImageLink = image.getString("thumbnail");
                    try {
                        URL url = new URL(smallImageLink);
                        URL url2 = new URL(largeImageLink);
                        bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        bmp2 = BitmapFactory.decodeStream(url2.openConnection().getInputStream());
                    } catch (MalformedURLException e) {

                    } catch (IOException e) {
                    }
                } else {
                    bmp = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.no_image_available_s_large);
                    bmp2 = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.no_image_available_s_large);
                }

                Book book = new Book(bmp, bmp2, title, pageCount, authorName, publishDate, averageRating, previewLink,
                        bookTextSnippet, ratingsCount, description, bookPublisher, webReadLink, buyBookLink);
                books.add(book);
            }
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the book JSON result", e);
        }
        return books;
    }
}
