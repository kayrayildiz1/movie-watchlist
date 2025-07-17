package com.oop3.watchlist.controller;

import com.oop3.watchlist.model.Movie;
import com.oop3.watchlist.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for movie endpoints.
 */
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired 
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
/**
     * Gets all movies.
     */
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
/**
     * Adds a new movie.
     */

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }
  /**
     * Deletes a movie.
     */
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}

