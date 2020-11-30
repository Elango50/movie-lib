package com.movies.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movies.library.model.Movies;
import com.movies.library.service.MoviesService;

@RestController
public class MoviesController {

	@Autowired
	MoviesService moviesService;
	
	@RequestMapping(value = "/movies/", method = RequestMethod.GET)
    public ResponseEntity<List<Movies>> listAllMovies() {
        List<Movies> moviesList = moviesService.findAllMovies();
        if(moviesList.isEmpty()){
            return new ResponseEntity<List<Movies>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Movies>>(moviesList, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/movies/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movies> getMovie(@PathVariable("id") long id) {
        System.out.println("Fetching Movie with id " + id);
        Movies movies = moviesService.findById(id);
        if (movies == null) {
            System.out.println("Movies with id " + id + " not found");
            return new ResponseEntity<Movies>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Movies>(movies, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Movies> deleteMovie(@PathVariable("id") long id) {
		
        System.out.println("Fetching & Deleting Movie with id " + id);
 
        Movies movie = moviesService.findById(id);
        if (movie == null) {
            System.out.println("Movie to delete. Movie with id " + id + " not found");
            return new ResponseEntity<Movies>(HttpStatus.NOT_FOUND);
        }
 
        moviesService.deleteMovieById(id);
        return new ResponseEntity<Movies>(HttpStatus.NO_CONTENT);
    }
	
	@RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Movies> updateMovie(@PathVariable("id") long id, @RequestBody Movies movie) {
        
		System.out.println("Updating Movies " + id);
         
        Movies currentmovie = moviesService.findById(id);
         
        if (currentmovie == null) {
            System.out.println("Movie with id " + id + " not found");
            return new ResponseEntity<Movies>(HttpStatus.NOT_FOUND);
        }
 
        currentmovie.setName(movie.getName());
        currentmovie.setYear(movie.getYear());
        currentmovie.setDirector(movie.getDirector());
         
        moviesService.updateMovie(currentmovie);
        return new ResponseEntity<Movies>(currentmovie, HttpStatus.OK);
    }
}
