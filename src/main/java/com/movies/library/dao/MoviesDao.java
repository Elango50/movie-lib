package com.movies.library.dao;

import java.util.List;

import com.movies.library.model.Movies;

public interface MoviesDao {

	List<Movies> getAllmovies();
	Movies getMovie(long id);
	void deleteMovieById(long id);
	void updateMovie(Movies movie);

}
