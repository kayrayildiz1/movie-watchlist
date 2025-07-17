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

/**
 * Service class for handling movie-related operations.
 * Integrates with OMDb and TMDb APIs to enrich movie data.
 */

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
    
    /**
     * Returns a list of all movies in the database.
     *
     * @return list of movies
     */


    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
      /**
     * Adds a new movie and enriches it with OMDb metadata.
     *
     * @param movie the movie to add
     * @return the saved movie
     */

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
    /**
     * Deletes a movie by its ID.
     *
     * @param id the ID of the movie to delete
     */

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
        /**
     * Finds a movie by its ID.
     *
     * @param id the ID of the movie
     * @return the movie if found, otherwise Optional.empty()
     */


    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }
        /**
     * Checks if a movie exists by its ID.
     *
     * @param id the ID to check
     * @return true if the movie exists, false otherwise
     */


    public boolean movieExists(Long id) {
        return movieRepository.existsById(id);
    }
}



