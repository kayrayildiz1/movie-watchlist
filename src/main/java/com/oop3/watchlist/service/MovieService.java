package com.oop3.watchlist.service;

import com.google.gson.JsonObject;
import com.oop3.watchlist.model.Movie;
import com.oop3.watchlist.repository.MovieRepository;
import com.oop3.watchlist.service.api.OmdbClient;
import com.oop3.watchlist.service.api.TmdbClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final OmdbClient omdbClient;
    private final TmdbClient tmdbClient;

    @Autowired
    public MovieService(MovieRepository movieRepository, OmdbClient omdbClient, TmdbClient tmdbClient) {
        this.movieRepository = movieRepository;
        this.omdbClient = omdbClient;
        this.tmdbClient = tmdbClient;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie addMovie(Movie movie) {
        JsonObject omdbData = omdbClient.fetchMovie(movie.getTitle());

        if (omdbData != null) {
            if (omdbData.has("Director")) {
                movie.setDirector(omdbData.get("Director").getAsString());
            }
            if (omdbData.has("Year")) {
                movie.setYear(omdbData.get("Year").getAsString());
            }
            if (omdbData.has("Genre")) {
                movie.setGenre(omdbData.get("Genre").getAsString());
            }
        }

        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public boolean movieExists(Long id) {
        return movieRepository.existsById(id);
    }
}



