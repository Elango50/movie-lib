package com.movies.library.task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MovieTask  implements Tasklet {
	
	private JdbcTemplate jdbcTemplate;

	public MovieTask(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		
		  System.out.println("Create Movie table start..");
		  jdbcTemplate.execute("DROP TABLE IF EXISTS movies");
		  jdbcTemplate.execute("CREATE TABLE movies (id INT PRIMARY KEY,name VARCHAR(250) NOT NULL,year INT NOT NULL,director VARCHAR(250) DEFAULT NULL)");
	      System.out.println("Create Movie table done..");
	      return RepeatStatus.FINISHED;
	}

}
