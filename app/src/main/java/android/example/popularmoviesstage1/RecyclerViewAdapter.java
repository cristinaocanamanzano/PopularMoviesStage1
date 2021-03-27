package android.example.popularmoviesstage1;

import android.content.Context;
import android.example.popularmoviesstage1.model.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.PosterViewHolder> {

    static Movie[] mMoviesData;
    final LayoutInflater inflater;
    private final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185/";
    private final RecyclerViewAdapterOnClickHandler mClickHandler;

    public interface RecyclerViewAdapterOnClickHandler {
        void onListItemClick(int clickedItemIndex);
    }

    public RecyclerViewAdapter(Context context, RecyclerViewAdapterOnClickHandler clickHandler) {
        this.inflater = LayoutInflater.from(context);
        this.mClickHandler = clickHandler;
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutIdForListItem = R.layout.poster_item;
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new PosterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        Movie movie = mMoviesData[position];
        String imagePath = movie.getImage();
        String movieTitle = movie.getTitle();
        String imageURL = IMAGE_BASE_URL + imagePath;
        Picasso.get().load(imageURL).into(holder.mPosterImageView);
        holder.mPosterImageView.setContentDescription(movieTitle);
    }

    @Override
    public int getItemCount() {
        if (null == mMoviesData) return 0;
        return mMoviesData.length;
    }

    public class PosterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final ImageView mPosterImageView;
        public PosterViewHolder(View itemView) {
            super(itemView);
            mPosterImageView = itemView.findViewById(R.id.poster_iv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.onListItemClick(adapterPosition);
        }
    }

    public void setMoviesData(Movie[] moviesData) {
        mMoviesData = moviesData;
        notifyDataSetChanged();
    }
}
