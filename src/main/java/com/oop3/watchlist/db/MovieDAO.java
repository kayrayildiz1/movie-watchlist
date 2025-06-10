package com.oop3.watchlist.db;

import com.oop3.watchlist.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    private static final String DB_URL = "jdbc:sqlite:watchlist.db";

    static {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String createTable = "CREATE TABLE IF NOT EXISTS movies (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "title TEXT NOT NULL," +
                    "year TEXT," +
                    "director TEXT," +
                    "genre TEXT," +
                    "watched BOOLEAN DEFAULT FALSE," +
                    "rating INTEGER DEFAULT 0" +
                    ")";
            conn.createStatement().execute(createTable);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
    }

    public static void save(Movie movie) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO movies (title, year, director, genre, watched, rating) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, movie.title);
            stmt.setString(2, movie.year);
            stmt.setString(3, movie.director);
            stmt.setString(4, movie.genre);
            stmt.setBoolean(5, movie.watched);
            stmt.setInt(6, movie.rating);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save movie", e);
        }
    }

    public static List<Movie> getAll(int limit, int offset) {
        List<Movie> movies = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT * FROM movies LIMIT ? OFFSET ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Movie movie = new Movie();
                movie.id = rs.getInt("id");
                movie.title = rs.getString("title");
                movie.year = rs.getString("year");
                movie.director = rs.getString("director");
                movie.genre = rs.getString("genre");
                movie.watched = rs.getBoolean("watched");
                movie.rating = rs.getInt("rating");
                movies.add(movie);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch movies", e);
        }
        return movies;
    }

    public static void updateWatched(int id, boolean watched) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "UPDATE movies SET watched = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, watched);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update watched", e);
        }
    }

    public static void updateRating(int id, int rating) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "UPDATE movies SET rating = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, rating);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update rating", e);
        }
    }

    public static void delete(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "DELETE FROM movies WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete movie", e);
        }
    }
}

