package com.oop3.watchlist.service;

import com.google.gson.JsonObject;
import com.oop3.watchlist.api.OmdbClient;
import com.oop3.watchlist.api.TmdbClient;
import com.oop3.watchlist.model.Movie;
import com.oop3.watchlist.db.MovieDAO; 


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MovieService {

    public static Movie fetchAndStoreMovie(String title) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<JsonObject> omdbFuture = executor.submit(() -> OmdbClient.fetchMovie(title));
        Future<Integer> tmdbIdFuture = executor.submit(() -> TmdbClient.getMovieId(title));

        JsonObject omdbData = omdbFuture.get();
        int tmdbId = tmdbIdFuture.get();

        executor.shutdown();

        Movie movie = new Movie();
        movie.title = omdbData.get("Title").getAsString();
        movie.year = omdbData.get("Year").getAsString();
        movie.director = omdbData.get("Director").getAsString();
        movie.genre = omdbData.get("Genre").getAsString();

        movie.similarMovies = TmdbClient.getSimilarMovies(tmdbId);

        String[] imagePaths = TmdbClient.getImagePaths(tmdbId);
        String[] localPaths = new String[imagePaths.length];
        for (int i = 0; i < imagePaths.length; i++) {
            String localPath = "images/" + title.replace(" ", "_") + "_" + i + ".jpg";
            TmdbClient.downloadImage(imagePaths[i], localPath);
            localPaths[i] = localPath;
        }
        movie.imagePaths = localPaths;

        movie.watched = false;
        movie.rating = 0;

        MovieDAO.save(movie);

        return movie;
    }
}
