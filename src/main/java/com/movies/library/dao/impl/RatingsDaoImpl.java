package com.movies.library.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.movies.library.dao.RatingDao;
import com.movies.library.mapper.RatingMapper;
import com.movies.library.model.Rating;

@Component
public class RatingsDaoImpl implements RatingDao {
	
	@Autowired
	@Qualifier("ratingJdbcTemplate")
    private JdbcTemplate ratingTemplate;

	@Override 
    public List<Rating> getAllmoviesRating() {
        return (List<Rating>) ratingTemplate.query("SELECT * FROM RATINGS", new RatingMapper());
    }

	@Override
	public Rating getMovieRatings(long id) {
		String sql = "SELECT * FROM RATINGS WHERE id = ?";
		Rating rating = (Rating) ratingTemplate.queryForObject(sql, new RatingMapper(), new Object[]{id});
		return rating;
	}

}
