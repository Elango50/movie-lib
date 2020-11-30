package com.movies.library.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movies.library.dao.MoviesDao;
import com.movies.library.model.Movies;
import com.movies.library.service.MoviesService;

@Service("moviesService")
@Transactional
public class MoviesServiceImpl implements MoviesService {
	
	@Autowired
	MoviesDao moviesDao;

	@Override
	public Movies findById(long id) {
		// TODO Exception handling
		return moviesDao.getMovie(id);
	}

	@Override
	public List<Movies> findAllMovies() {
		// TODO Exception handling
		return moviesDao.getAllmovies();
	}

	@Override
	public void deleteMovieById(long id) {
		moviesDao.deleteMovieById(id);
	}

	@Override
	public void updateMovie(Movies movie) {
		moviesDao.updateMovie(movie);
	}
	
}
