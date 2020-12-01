package com.movies.library.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movies.library.dao.RatingDao;
import com.movies.library.model.Rating;
import com.movies.library.service.RatingService;

@Service("ratingService")
@Transactional
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	RatingDao ratingDao;

	@Override
	public Rating findRatingById(long id) {
		// TODO Exception handling
		return ratingDao.getMovieRatings(id);
	}

	@Override
	public List<Rating> findAllMoviesRating() {
		// TODO Exception handling
		return ratingDao.getAllmoviesRating();
	}
}
