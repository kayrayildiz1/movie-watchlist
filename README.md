# 🎬 Movie Watchlist

This is a Java-based backend application for managing a personal movie watchlist. It was developed as part of the OOP3 course assignment.

---

## 🔧 Features

- 🎥 Search movies by title using the **OMDb API**
- 🧠 Simulate TMDb API to:
  - Retrieve similar movie suggestions
  - Get image paths
- 💾 Store movie details (title, year, director, genre, watched status, rating) in a local **SQLite** database
- 📸 Simulate image downloads
- 🧹 Full CRUD operations on movies
- 📑 RESTful API with endpoints for:
  - Adding a movie
  - Listing watchlist (with pagination)
  - Updating watched status
  - Updating rating
  - Deleting a movie

---

## ⚙️ Technologies

- Java 17
- Maven
- Javalin (Web Framework)
- OkHttp (HTTP Client)
- Gson (JSON parser)
- SQLite (Database)
- JUnit 5 (Testing)

---

## 🚀 How to Run

### ✅ Prerequisites:
- Java 17 or higher installed
- Maven installed

### ▶️ Steps:
```bash
git clone https://gitlab.com/your_username/oop3-assignment-723073.git
cd movie-watchlist
mvn compile
mvn exec:java
Then go to:
http://localhost:7070

🧪 How to Test

mvn test
Unit tests are located in src/test/java

API Endpoints

Method	Endpoint	Description
GET	/	Health check
GET	/movies?title={title}	Fetch and store movie by title
GET	/watchlist?page=1&size=10	Get paginated movie list
PUT	/watchlist/{id}/watched	Update watched status (true/false)
PUT	/watchlist/{id}/rating	Update rating (0-10)
DELETE	/watchlist/{id}	Delete movie by ID

├── api/              # OMDb and TMDb client (real & mock)
├── db/               # SQLite access layer (DAO)
├── model/            # Movie entity
├── service/          # Business logic
├── Main.java         # Starts the server
└── test/             # JUnit test cases

📌 Notes

TMDb features (similar movies and images) are mocked.
OMDb requests are real and require an API key.
Images are not actually downloaded—only simulated.

