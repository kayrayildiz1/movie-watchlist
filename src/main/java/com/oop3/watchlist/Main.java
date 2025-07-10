package com.oop3.watchlist;

import io.javalin.Javalin;
import com.oop3.watchlist.service.MovieService;
import com.oop3.watchlist.model.Movie;
import com.oop3.watchlist.db.MovieDAO;

public class Main {
    public static void main(String[] args) {

        // Initialize the MovieService with MovieDAO
        MovieService movieService = new MovieService(new MovieDAO());

        // Start the Javalin server on port 7070
        Javalin app = Javalin.create(config -> {
            // Server configuration can go here
        }).start(7070);

        // Health check endpoint
        app.get("/", ctx -> ctx.result("Movie Watchlist Backend is running."));

        // Endpoint to fetch a movie by title, store it in DB, and return full data
        app.get("/movies", ctx -> {
            String title = ctx.queryParam("title");
            if (title == null || title.isEmpty()) {
                ctx.status(400).result("Missing movie title.");
                return;
            }
            try {
                Movie movie = movieService.fetchAndStoreMovie(title);
                ctx.json(movie);
            } catch (Exception e) {
                ctx.status(500).result("Server error: " + e.getMessage());
            }
        });

        // Endpoint to get paginated watchlist from DB
        app.get("/watchlist", ctx -> {
            int page = Integer.parseInt(ctx.queryParam("page") != null ? ctx.queryParam("page") : "1");
            int size = Integer.parseInt(ctx.queryParam("size") != null ? ctx.queryParam("size") : "10");
            int offset = (page - 1) * size;
            ctx.json(MovieDAO.getAll(size, offset));
        });

        // Endpoint to update watched status by ID
        app.put("/watchlist/{id}/watched", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            boolean watched = Boolean.parseBoolean(ctx.body());
            MovieDAO.updateWatched(id, watched);
            ctx.status(204); // No Content
        });

        // Endpoint to update movie rating by ID
        app.put("/watchlist/{id}/rating", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            int rating = Integer.parseInt(ctx.body());
            MovieDAO.updateRating(id, rating);
            ctx.status(204); // No Content
        });

        // Endpoint to delete a movie by ID
        app.delete("/watchlist/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            MovieDAO.delete(id);
            ctx.status(204); // No Content
        });
    }
}
