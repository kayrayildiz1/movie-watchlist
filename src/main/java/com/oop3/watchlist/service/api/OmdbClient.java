package com.oop3.watchlist.service.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * API client for OMDb API.
 */

@Service
public class OmdbClient {

    private static final String API_KEY = "32dcb0cc";
    private final OkHttpClient client = new OkHttpClient();

    /**
     * Fetches OMDb data for a movie by title.
     * @param title title of the movie
     * @return movie data as JsonObject
     */

    public JsonObject fetchMovie(String title) {
        String url = "http://www.omdbapi.com/?t=" + title.replace(" ", "%20") + "&apikey=" + API_KEY;

        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.body() == null) {
                System.err.println("OMDb response body is null.");
                return null;
            }

            String json = response.body().string();
            JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

            if (!response.isSuccessful()) {
                System.err.println("OMDb HTTP error: " + response.code());
                return null;
            }

            if (obj.has("Response") && obj.get("Response").getAsString().equalsIgnoreCase("False")) {
                String errorMessage = obj.has("Error") ? obj.get("Error").getAsString() : "Unknown OMDb error";
                System.err.println("OMDb API error: " + errorMessage);
                return null;
            }

            return obj;

        } catch (IOException e) {
            System.err.println("Failed to fetch from OMDb: " + e.getMessage());
            return null;
        }
    }
}
