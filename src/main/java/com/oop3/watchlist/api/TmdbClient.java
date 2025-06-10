package com.oop3.watchlist.api;

public class TmdbClient {

    // Return mock TMDB movie ID
    public static int getMovieId(String title) {
        // Simulate always returning movie ID 12345
        return 12345;
    }

    // Return mock similar movie titles
    public static String[] getSimilarMovies(int movieId) {
        return new String[] {
            "Mock Similar Movie 1",
            "Mock Similar Movie 2",
            "Mock Similar Movie 3"
        };
    }

    // Return mock image file paths
    public static String[] getImagePaths(int movieId) {
        return new String[] {
            "/mock/image1.jpg",
            "/mock/image2.jpg",
            "/mock/image3.jpg"
        };
    }

    // Simulate downloading image (does nothing)
    public static void downloadImage(String tmdbPath, String saveAs) {
        System.out.println("Simulated image download: " + tmdbPath + " -> " + saveAs);
    }
}

