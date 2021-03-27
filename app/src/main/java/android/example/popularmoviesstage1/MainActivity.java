package android.example.popularmoviesstage1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.example.popularmoviesstage1.model.Movie;
import android.example.popularmoviesstage1.utils.JsonUtils;
import android.example.popularmoviesstage1.utils.NetworkUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.RecyclerViewAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private ProgressBar mLoadingIndicator;
    private TextView mErrorMessageDisplay;
    private final String MOVIES_ENDPOINT_TOP_RATED = "top_rated";
    private final String MOVIES_ENDPOINT_POPULAR = "popular";
    private final String MOVIE_INTENT_EXTRA = "Movie";
    private Movie[] mMoviesData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);
        mRecyclerView = findViewById(R.id.posters_recycler_view);
        mErrorMessageDisplay = findViewById(R.id.tv_error_message_display);
        int numberOfColumns = 2;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, numberOfColumns, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        loadMoviesData();
    }

    @Override
    public void onListItemClick(int position) {
        Class destinationClass = MovieDetail.class;
        Intent movieDetailIntent = new Intent(this, destinationClass);
        Movie movie = mMoviesData[position];
        movieDetailIntent.putExtra(MOVIE_INTENT_EXTRA, movie);
        startActivity(movieDetailIntent);
    }

    private void loadMoviesData(String filterSelected) {
        showMoviesData();
        new FetchMoviesTask().execute(filterSelected);
    }

    private void loadMoviesData() {
        showMoviesData();
        new FetchMoviesTask().execute(MOVIES_ENDPOINT_POPULAR);
    }

    private void showMoviesData() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    public class FetchMoviesTask extends AsyncTask<String, Void, Movie[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected Movie[] doInBackground(String...params) {
            if (params.length == 0) {
                return null;
            }
            String filterUsed = params[0];
            URL moviesRequestUrl = NetworkUtils.buildUrl(filterUsed);
            try {
                String moviesHttpResponse = NetworkUtils.getResponseFromHttpUrl(moviesRequestUrl);
                JSONObject moviesJsonObject = new JSONObject(moviesHttpResponse);
                return JsonUtils.getArrayWithMovies(moviesJsonObject);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Movie[] moviesData) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
                if (moviesData != null) {
                    mMoviesData = moviesData;
                    showMoviesData();
                    mRecyclerViewAdapter.setMoviesData(moviesData);
                } else {
                    showErrorMessage();
                }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_sort_most_popular).setChecked(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);
        int id = item.getItemId();
        switch(id) {
            case R.id.action_sort_most_popular:
                loadMoviesData(MOVIES_ENDPOINT_POPULAR);
                return true;
            case R.id.action_sort_top_rated:
                loadMoviesData(MOVIES_ENDPOINT_TOP_RATED);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}