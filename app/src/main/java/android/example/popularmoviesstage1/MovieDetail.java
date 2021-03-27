package android.example.popularmoviesstage1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.example.popularmoviesstage1.model.Movie;
import android.example.popularmoviesstage1.utils.DateParsingUtils;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetail extends AppCompatActivity {

    private final String MOVIE_INTENT_EXTRA = "Movie";
    private TextView mMovieTitleTextView;
    private ImageView mPosterImageView;
    private TextView mOverviewTextView;
    private TextView mUserRatingTextView;
    private TextView mReleaseDateTextView;
    private Movie mMovie;
    private final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185/";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        mMovieTitleTextView = findViewById(R.id.movie_title_tv);
        mPosterImageView = findViewById(R.id.poster_iv);
        mOverviewTextView = findViewById(R.id.overview_tv);
        mUserRatingTextView = findViewById(R.id.user_rating_tv);
        mReleaseDateTextView = findViewById(R.id.release_date_tv);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(MOVIE_INTENT_EXTRA)) {
                mMovie = intentThatStartedThisActivity.getParcelableExtra(MOVIE_INTENT_EXTRA);
                mMovieTitleTextView.setText(mMovie.getTitle());
                mOverviewTextView.setText(mMovie.getOverview());
                mUserRatingTextView.setText(mMovie.getVoteAverage());
                mReleaseDateTextView.setText(DateParsingUtils.formatDateString(mMovie.getReleaseDate()));
                String imageURL = IMAGE_BASE_URL + mMovie.getImage();
                Picasso.get().load(imageURL).into(mPosterImageView);
            }
        }

    }
}