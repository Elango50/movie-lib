package com.movies.library.processor;

import org.springframework.batch.item.ItemProcessor;

import com.movies.library.model.Rating;

public class RatingItemProcessor implements ItemProcessor<Rating, Rating> {

	@Override
	public Rating process(Rating arg0) throws Exception {
		return arg0;
	}

}
