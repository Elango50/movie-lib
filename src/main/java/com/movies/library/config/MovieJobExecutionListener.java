package com.movies.library.config;

import java.util.List;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.movies.library.mapper.MoviesMapper;
import com.movies.library.model.Movies;

@Component
public class MovieJobExecutionListener implements JobExecutionListener {

	@Autowired
	@Qualifier("movieJdbcTemplate")
	private JdbcTemplate template;
	  
	@Override
	public void afterJob(JobExecution jobExecution) {
		
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			List<Movies> result = (List<Movies>) template.query("SELECT * FROM MOVIES", new MoviesMapper());
			System.out.println("Number of Records:" + result.size());
		}
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Executing job id " + jobExecution.getId());
	}

}
