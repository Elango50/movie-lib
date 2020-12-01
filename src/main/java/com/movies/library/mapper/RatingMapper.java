package com.movies.library.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.movies.library.model.Rating;

public class RatingMapper implements RowMapper<Rating> {

	@Override
	public Rating mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Rating(rs.getLong("id"), rs.getString("name"), rs.getInt("year"), rs.getInt("rating"));
	}

}
