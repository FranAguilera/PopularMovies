package com.berickson.popularmovies.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private String id;
    private String title;
    private String releaseDate;
    private String posterURL;
    private String voteAverage;
    private String plotSynopsis;

    private Movie() {}

    private Movie(Parcel in) {
        id = in.readString();
        title = in.readString();
        releaseDate = in.readString();
        posterURL = in.readString();
        voteAverage = in.readString();
        plotSynopsis = in.readString();
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

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public String getVoteAverage() {
        return voteAverage + "/10";
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(releaseDate);
        dest.writeString(posterURL);
        dest.writeString(voteAverage);
        dest.writeString(plotSynopsis);
    }

    public static class Builder {

        private String id;
        private String title;
        private String releaseDate;
        private String posterURL;
        private String voteAverage;
        private String plotSynopsis;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Builder setPosterUrl(String posterUrl) {
            this.posterURL = posterUrl;
            return this;
        }

        public Builder setVoteAverage(String voteAverage) {
            this.voteAverage = voteAverage;
            return this;
        }

        public Builder setPlotSynopsis(String plotSynopsis) {
            this.plotSynopsis = plotSynopsis;
            return this;
        }

        public Movie build() {
            Movie movie = new Movie();
            movie.id = this.id;
            movie.title = this.title;
            movie.releaseDate = this.releaseDate;
            movie.posterURL = this.posterURL;
            movie.voteAverage = this.voteAverage;
            movie.plotSynopsis = this.plotSynopsis;
            return movie;
        }
    }
}
