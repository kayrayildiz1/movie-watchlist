# Movie Watchlist 📽️

This is a Java-based backend application for managing a personal movie watchlist. The project was developed as part of the OOP3 course assignment.

## 🔧 Features

- Search movies by title using OMDb and TMDB APIs
- Fetch and store movie details (title, year, director, genre)
- Download and save movie images
- Show similar movies
- Store movies in SQLite database
- RESTful API:
  - Add a movie
  - List watchlist (with pagination)
  - Update watched status
  - Update movie rating
  - Delete a movie

## 🚀 How to Run

### Prerequisites:
- Java 17+
- Maven

### Steps:
```bash
git clone https://gitlab.com/your_username/oop3-assignment-723073.git
cd movie-watchlist
mvn compile
mvn exec:java
