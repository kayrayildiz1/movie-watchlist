package com.oop3.watchlist.service;

import com.oop3.watchlist.api.OmdbClient;
import com.oop3.watchlist.api.TmdbClient;
import com.oop3.watchlist.db.MovieDAO;
import com.oop3.watchlist.model.Movie;
import com.google.gson.JsonObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * The MovieService class handles core logic for fetching movie data
 * from external APIs (OMDb and TMDB), downloading related images, and
 * storing movie information into the database.
 */
public class MovieService {

    private final MovieDAO movieDAO;

    /**
     * Constructs a MovieService with the provided MovieDAO dependency.
     *
     * @param movieDAO The data access object used for storing movie data.
     */
    public MovieService(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    /**
     * Fetches movie information using OMDb and TMDB APIs, downloads image files,
     * and stores the result in the database.
     *
     * @param title The title of the movie to fetch.
     * @return A fully populated Movie object.
     * @throws Exception If any API call or file operation fails.
     */
    public Movie fetchAndStoreMovie(String title) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Fetch OMDb and TMDB data in parallel
        Future<JsonObject> omdbFuture = executor.submit(() -> OmdbClient.fetchMovie(title));
        Future<Integer> tmdbIdFuture = executor.submit(() -> TmdbClient.getMovieId(title));

        JsonObject omdbData = omdbFuture.get();
        int tmdbId = tmdbIdFuture.get();

        executor.shutdown();

        // Build Movie object from OMDb data
        Movie movie = new Movie();
        movie.setTitle(omdbData.get("Title").getAsString());
        movie.setYear(omdbData.get("Year").getAsString());
        movie.setDirector(omdbData.get("Director").getAsString());
        movie.setGenre(omdbData.get("Genre").getAsString());
        movie.setWatched(false);
        movie.setRating(0);

        // Get similar movies and image paths from TMDB
        movie.setSimilarMovies(TmdbClient.getSimilarMovies(tmdbId));
        String[] imagePaths = TmdbClient.getImagePaths(tmdbId);

        // Download images locally and store paths
        String[] localPaths = new String[imagePaths.length];
        for (int i = 0; i < imagePaths.length; i++) {
            String localPath = "images/" + title.replace(" ", "_") + "_" + i + ".jpg";
            TmdbClient.downloadImage(imagePaths[i], localPath);
            localPaths[i] = localPath;
        }
        movie.setImagePaths(localPaths);

        // Save the movie to the database
        movieDAO.save(movie);

        return movie;
    }
}
