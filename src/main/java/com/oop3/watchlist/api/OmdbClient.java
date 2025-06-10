package com.oop3.watchlist.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OmdbClient {
    private static final String API_KEY = "32dcb0cc";
    private static final OkHttpClient client = new OkHttpClient();

    public static JsonObject fetchMovie(String title) {
        String url = "http://www.omdbapi.com/?t=" + title.replace(" ", "%20") + "&apikey=" + API_KEY;

        try {
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();

            String json = response.body().string();
            JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

            if (!response.isSuccessful()) {
                throw new RuntimeException("OMDb HTTP error: " + response.code());
            }

            if (obj.has("Response") && obj.get("Response").getAsString().equalsIgnoreCase("False")) {
                String errorMessage = obj.has("Error") ? obj.get("Error").getAsString() : "Unknown error";
                throw new RuntimeException("OMDb API error: " + errorMessage);
            }

            return obj;
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch movie from OMDb: " + e.getMessage(), e);
        }
    }
}
