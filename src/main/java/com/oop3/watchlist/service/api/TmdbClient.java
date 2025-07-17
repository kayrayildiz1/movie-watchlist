package com.oop3.watchlist.service.api;

import org.springframework.stereotype.Service;

/**
 * API client for TMDb.
 */
@Service
public class TmdbClient {

    public int getMovieId(String title) {
        try {
            if (title == null || title.isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }
            return 12345;
        } catch (Exception e) {
            System.err.println("TMDB error (getMovieId): " + e.getMessage());
            return -1;
        }
    }

    public String[] getSimilarMovies(int movieId) {
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

    public String[] getImagePaths(int movieId) {
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

    public void downloadImage(String tmdbPath, String saveAs) {
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
