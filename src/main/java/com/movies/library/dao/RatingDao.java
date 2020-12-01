package com.movies.library.dao;

import java.util.List;

import com.movies.library.model.Rating;

public interface RatingDao {
	
	List<Rating> getAllmoviesRating();
	Rating getMovieRatings(long id);

}
