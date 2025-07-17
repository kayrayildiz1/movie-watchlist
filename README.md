## 🎬 Movie Watchlist Application

This is a Java-based Spring Boot backend application for managing a personal movie watchlist. It integrates with external APIs (OMDb and TMDB) to fetch movie data and supports basic CRUD operations.

## 📌 Features

- Add new movies with metadata fetched from OMDb
- View all saved movies
- Delete movies by ID
- Check if a movie exists by ID
- Store movie images and similar movies (optional fields)
- External API integration with OMDb and TMDB
- Clean architecture using MVC pattern
- Unit tests with JUnit and Mockito

## 🛠️ Technologies Used

- Java 21
- Spring Boot
- Maven
- H2 (in-memory database for testing)
- OMDb API
- TMDB API
- JUnit & Mockito (for testing)
- Gson (for JSON parsing)
- OkHttp (for HTTP requests)

## 📦 Project Structure
movie-watchlist/
├── src/
│ ├── main/
│ │ ├── java/com/oop3/watchlist/
│ │ │ ├── controller/
│ │ │ ├── model/
│ │ │ ├── repository/
│ │ │ ├── service/
│ │ │ └── service/api/
│ ├── test/
│ │ └── java/com/oop3/watchlist/
├── pom.xml
└── README.md


## 🚀 Running the Application

1. Make sure you have Java 21 and Maven installed.
2. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/movie-watchlist.git
   cd movie-watchlist


Run the app:
mvn spring-boot:run

Or build and run:
mvn clean package
java -jar target/movie-watchlist-1.0.0.jar