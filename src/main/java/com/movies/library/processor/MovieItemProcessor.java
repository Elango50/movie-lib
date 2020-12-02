package com.movies.library.processor;

import org.springframework.batch.item.ItemProcessor;

import com.movies.library.model.Movies;

public class MovieItemProcessor implements ItemProcessor<Movies, Movies> {

	@Override
	public Movies process(Movies arg0) throws Exception {
		return arg0;
	}

}
