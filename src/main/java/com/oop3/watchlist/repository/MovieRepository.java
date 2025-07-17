package com.oop3.watchlist.repository;

import com.oop3.watchlist.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Spring automatically provides methods like:
    // findAll(), findById(), save(), deleteById(), existsById(), etc.
}

