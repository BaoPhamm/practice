package com.bao.practice.mapper;

import com.bao.practice.dto.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class RowMovieMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Movie(
                rs.getInt("id"),
                rs.getString("name"),
                List.of(),
                LocalDate.parse(rs.getString("release_date"))
        );
    }
}
