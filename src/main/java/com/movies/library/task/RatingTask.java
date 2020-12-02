package com.movies.library.task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;

public class RatingTask  implements Tasklet {

	private JdbcTemplate jdbcTemplate;

	public RatingTask(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		  System.out.println("Create Rating table start..");
		  jdbcTemplate.execute("DROP TABLE IF EXISTS ratings");
		  jdbcTemplate.execute("CREATE TABLE ratings (id INT PRIMARY KEY,name VARCHAR(250) NOT NULL,year INT NOT NULL,rating INT DEFAULT 0)");
	      System.out.println("Create Rating table done..");
	      return RepeatStatus.FINISHED;
	}

}
