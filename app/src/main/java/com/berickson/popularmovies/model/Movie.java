package com.berickson.popularmovies.model;


public class Movie {

    private String id;
    private String title;
    private String releaseDate;
    private String posterURL;
    private String voteAverage;
    private String plotSynopsis;

    private Movie() {}

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
        return voteAverage;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
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
