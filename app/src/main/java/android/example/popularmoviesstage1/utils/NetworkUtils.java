package android.example.popularmoviesstage1.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class NetworkUtils {

    private static final String MOVIES_BASE_URL = "http://api.themoviedb.org/3/movie/FILTER_USED_PLACEHOLDER";
    private static final String FILTER_USED_PLACEHOLDER = "FILTER_USED_PLACEHOLDER";
    private static final String API_KEY = "PLACE_API_KEY_HERE";
    final static String API_KEY_PARAM = "api_key";

    public static URL buildUrl(String filterUsedQuery) {
        String moviesBaseUrlWithFilter = MOVIES_BASE_URL.replace(FILTER_USED_PLACEHOLDER, filterUsedQuery);
        Uri builtUri = Uri.parse(moviesBaseUrlWithFilter).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
