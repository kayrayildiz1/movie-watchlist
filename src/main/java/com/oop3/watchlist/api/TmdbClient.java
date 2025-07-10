package com.oop3.watchlist.api;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Simulated TMDB client with realistic structure and error handling.
 */
public class TmdbClient {

    /**
     * Returns a mock TMDB movie ID for a given title.
     * @param title movie title
     * @return mock ID or -1 on error
     */
    public static int getMovieId(String title) {
        try {
            if (title == null || title.isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }
            // Simulate successful lookup
            return 12345;
        } catch (Exception e) {
            System.err.println("TMDB error (getMovieId): " + e.getMessage());
            return -1;
        }
    }

    /**
     * Returns a mock list of similar movies for a given TMDB ID.
     * @param movieId TMDB movie ID
     * @return array of similar titles
     */
    public static String[] getSimilarMovies(int movieId) {
        try {
            if (movieId <= 0) throw new IllegalArgumentException("Invalid movie ID");
            return new String[] {
                "Mock Similar Movie 1",
                "Mock Similar Movie 2",
                "Mock Similar Movie 3"
            };
        } catch (Exception e) {
            System.err.println("TMDB error (getSimilarMovies): " + e.getMessage());
            return new String[0];
        }
    }

    /**
     * Returns a mock list of image paths for a movie.
     * @param movieId TMDB movie ID
     * @return array of image paths
     */
    public static String[] getImagePaths(int movieId) {
        try {
            if (movieId <= 0) throw new IllegalArgumentException("Invalid movie ID");
            return new String[] {
                "/mock/image1.jpg",
                "/mock/image2.jpg",
                "/mock/image3.jpg"
            };
        } catch (Exception e) {
            System.err.println("TMDB error (getImagePaths): " + e.getMessage());
            return new String[0];
        }
    }

    /**
     * Simulates downloading an image and saving it locally.
     * @param tmdbPath the image path on TMDB
     * @param saveAs where to save the image locally
     */
    public static void downloadImage(String tmdbPath, String saveAs) {
        try {
            if (tmdbPath == null || saveAs == null) {
                throw new IllegalArgumentException("Path cannot be null");
            }

            // This is mock: no actual download.
            System.out.println("[Mock Download] " + tmdbPath + " -> " + saveAs);
        } catch (Exception e) {
            System.err.println("TMDB error (downloadImage): " + e.getMessage());
        }
    }
}
