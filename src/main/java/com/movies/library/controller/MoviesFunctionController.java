package com.movies.library.controller;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import com.movies.library.model.Movies;
import com.movies.library.service.MoviesService;

public class MoviesFunctionController {

	@Autowired
	MoviesService moviesService;
	
	@Bean
    public Consumer<Movies> findAllConsumerMovies() {
		return input -> moviesService.findAllMovies();
    }
}
