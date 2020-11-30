package com.movies.library.service;

import java.util.List;

import com.movies.library.model.Movies;

public interface MoviesService {
	
	Movies findById(long id);
    List<Movies> findAllMovies();
	void deleteMovieById(long id); 
	void updateMovie(Movies movies);
}
