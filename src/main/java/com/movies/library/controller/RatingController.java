package com.movies.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movies.library.model.Rating;
import com.movies.library.service.RatingService;

@RestController
public class RatingController {

	@Autowired
	RatingService ratingService;
	
	@RequestMapping(value = "/rating/", method = RequestMethod.GET)
    public ResponseEntity<List<Rating>> listAllMoviesRating() {
        List<Rating> moviesRatingList = ratingService.findAllMoviesRating();
        if (moviesRatingList.isEmpty()) {
            return new ResponseEntity<List<Rating>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Rating>>(moviesRatingList, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/rating/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rating> getMovie(@PathVariable("id") long id) {
        System.out.println("Fetching Movie Rating with id " + id);
        Rating rating = ratingService.findRatingById(id);
        if (rating == null) {
            System.out.println("Movies Rating with id " + id + " not found");
            return new ResponseEntity<Rating>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Rating>(rating, HttpStatus.OK);
    }
}
