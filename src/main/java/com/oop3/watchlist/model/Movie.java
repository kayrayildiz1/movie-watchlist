package com.oop3.watchlist.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String year;
    private String director;
    private String genre;
    private boolean watched;
    private int rating;

    @ElementCollection
    private List<String> similarMovies;

    @ElementCollection
    private List<String> imagePaths;

    // Constructors
    public Movie() {}

    public Movie(Long id, String title, String year, String director, String genre, boolean watched, int rating, List<String> similarMovies, List<String> imagePaths) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.genre = genre;
        this.watched = watched;
        this.rating = rating;
        this.similarMovies = similarMovies;
        this.imagePaths = imagePaths;
    }

    // Getter and Setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<String> getSimilarMovies() {
        return similarMovies;
    }

    public void setSimilarMovies(List<String> similarMovies) {
        this.similarMovies = similarMovies;
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
    }
}
