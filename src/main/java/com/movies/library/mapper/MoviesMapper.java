package com.movies.library.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.movies.library.model.Movies;

public class MoviesMapper implements RowMapper<Movies> {

	@Override
	public Movies mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Movies(rs.getLong("id"), rs.getString("name"), rs.getInt("year"), rs.getString("director"));
	}

}
