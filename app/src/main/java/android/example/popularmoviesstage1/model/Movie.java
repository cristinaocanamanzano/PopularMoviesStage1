package android.example.popularmoviesstage1.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private final String title;
    private final String image;
    private final String overview;
    private final String voteAverage;
    private final String releaseDate;

    public Movie(String title, String image, String overview, String voteAverage, String releaseDate) {
        this.title = title;
        this.image = image;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
    }

    protected Movie(Parcel in) {
        title = in.readString();
        image = in.readString();
        overview = in.readString();
        voteAverage = in.readString();
        releaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() { return title; }
    public String getImage() { return image; }
    public String getOverview() { return overview; }
    public String getVoteAverage() { return voteAverage; }
    public String getReleaseDate() { return releaseDate; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(overview);
        dest.writeString(voteAverage);
        dest.writeString(releaseDate);
    }
}
