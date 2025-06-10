package com.oop3.watchlist;


import com.oop3.watchlist.model.Movie;
import com.oop3.watchlist.service.MovieService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {

    @Test
    void testFetchAndStoreMovieReturnsMovie() throws Exception {
        String title = "Inception";
        Movie movie = MovieService.fetchAndStoreMovie(title);

        assertNotNull(movie);
        assertEquals(title, movie.title);
        assertFalse(movie.watched);
        assertEquals(0, movie.rating);
        assertNotNull(movie.similarMovies);
        assertNotNull(movie.imagePaths);
    }
}