package com.oop3.watchlist.model;

/**
 * Movie model class representing a movie in the watchlist.
 */
public class Movie {
    private int id;
    private String title;
    private String year;
    private String director;
    private String genre;
    private boolean watched;
    private int rating;
    private String[] similarMovies;   // ✅ NEW
    private String[] imagePaths;      // ✅ NEW

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isWatched() {
        return watched;
    }

    public int getRating() {
        return rating;
    }

    public String[] getSimilarMovies() {
        return similarMovies;
    }

    public String[] getImagePaths() {
        return imagePaths;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setSimilarMovies(String[] similarMovies) {
        this.similarMovies = similarMovies;
    }

    public void setImagePaths(String[] imagePaths) {
        this.imagePaths = imagePaths;
    }
}

