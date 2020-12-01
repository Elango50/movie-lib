package com.movies.library.service;

import java.util.List;

import com.movies.library.model.Rating;

public interface RatingService {
	Rating findRatingById(long id);
    List<Rating> findAllMoviesRating();
}
