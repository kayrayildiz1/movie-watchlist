package com.oop3.watchlist;

import com.oop3.watchlist.model.Movie;
import com.oop3.watchlist.repository.MovieRepository;
import com.oop3.watchlist.service.MovieService;
import com.oop3.watchlist.service.api.OmdbClient;
import com.oop3.watchlist.service.api.TmdbClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    private MovieRepository movieRepository;
    private OmdbClient omdbClient;
    private TmdbClient tmdbClient;
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        movieRepository = mock(MovieRepository.class);
        omdbClient = mock(OmdbClient.class);
        tmdbClient = mock(TmdbClient.class);
        movieService = new MovieService(movieRepository, omdbClient, tmdbClient);
    }
}






