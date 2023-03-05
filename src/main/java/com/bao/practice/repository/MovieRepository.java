package com.bao.practice.repository;

import com.bao.practice.dao.MovieDao;
import com.bao.practice.dto.Movie;
import com.bao.practice.mapper.RowMovieMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepository implements MovieDao {

    private final JdbcTemplate jdbcTemplate;

    public MovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> selectMovies() {
        String sql = """
                SELECT id, name, release_date from movie limit 100;
                """;
        return jdbcTemplate.query(sql, new RowMovieMapper());
    }

    @Override
    public int insertMovie(Movie movie) {
        String sql = """
                INSERT into movie(name, release_date) 
                VALUES (?, ?);
                               
                """;
        return jdbcTemplate.update(sql, movie.name(), movie.releaseDate());
    }

    @Override
    public int deleteMovie(int id) {
        return 0;
    }

    @Override
    public Optional<Movie> selectMovieById(int id) {
        return Optional.empty();
    }
}
