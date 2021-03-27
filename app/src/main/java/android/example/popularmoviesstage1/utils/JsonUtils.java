package android.example.popularmoviesstage1.utils;

import android.example.popularmoviesstage1.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {
    private static final String RESULTS = "results";
    private static final String ORIGINAL_TITLE = "original_title";
    private static final String POSTER_PATH = "poster_path";
    private static final String OVERVIEW = "overview";
    private static final String VOTE_AVERAGE = "vote_average";
    private static final String RELEASE_DATE = "release_date";

    public static Movie[] getArrayWithMovies(JSONObject moviesData) {
        Movie[] moviesArray = null;
        try {
            JSONArray resultsArray = moviesData.getJSONArray(RESULTS);
            moviesArray = new Movie[resultsArray.length()];
            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject jsonObject = resultsArray.getJSONObject(i);
                String title = jsonObject.optString(ORIGINAL_TITLE);
                String posterUrl = jsonObject.optString(POSTER_PATH);
                String overview = jsonObject.optString(OVERVIEW);
                String voteAverage = jsonObject.optString(VOTE_AVERAGE);
                String releaseDate = jsonObject.optString(RELEASE_DATE);
                Movie movie = new Movie(title, posterUrl, overview, voteAverage, releaseDate);
                moviesArray[i] = movie;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return moviesArray;
    }
}
