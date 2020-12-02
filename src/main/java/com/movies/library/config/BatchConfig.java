package com.movies.library.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.movies.library.model.Movies;
import com.movies.library.model.Rating;
import com.movies.library.processor.MovieItemProcessor;
import com.movies.library.task.MovieTask;
import com.movies.library.task.RatingTask;

@Configuration
public class BatchConfig {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	@Qualifier("movieJdbcTemplate")
	private JdbcTemplate movieJdbcTemplate;
	 
	@Autowired
	@Qualifier("ratingJdbcTemplate")
	private JdbcTemplate ratingJdbcTemplate;
	
	@Bean
    public Step createMovieTask() {
        return stepBuilderFactory.get("createMovieTask")
                .tasklet(new MovieTask(movieJdbcTemplate))
                .build();
    }
	
	@Bean
    public Step ratingTask(){
        return stepBuilderFactory.get("ratingTask")
                .tasklet(new RatingTask(ratingJdbcTemplate))
                .build();
    }
	
	@Bean
    public Job movieLibJob(MovieJobExecutionListener listener, Step step1) {
        return jobBuilderFactory.get("demoJob")
                .incrementer(new RunIdIncrementer())
                .start(createMovieTask())
                .next(ratingTask())
                .next(step1)
                .build();
    }
	
	@Bean
	public FlatFileItemReader<Movies> reader() {
		return new FlatFileItemReaderBuilder<Movies>()
		  .name("movieItemReader")		
		  .resource(new ClassPathResource("movies.csv"))
		  .delimited()
		  .names(new String[]{ "id", "name", "year", "director" })
		  .fieldSetMapper(new BeanWrapperFieldSetMapper<Movies>() {{
			   setTargetType(Movies.class);
		  }})
		  .linesToSkip(1)
		  .build();
	}
	
	@Bean
	public FlatFileItemReader<Rating> ratingReader() {
		return new FlatFileItemReaderBuilder<Rating>()
		  .name("ratingItemReader")		
		  .resource(new ClassPathResource("rating.csv"))
		  .delimited()
		  .names(new String[]{ "id", "name", "year", "director" })
		  .fieldSetMapper(new BeanWrapperFieldSetMapper<Rating>() {{
			   setTargetType(Rating.class);
		  }})
		  .linesToSkip(1)
		  .build();
	}
	
	@Bean
	public JdbcBatchItemWriter<Movies> writer(@Qualifier("movieDB") DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Movies>()
		   .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Movies>())
		   .sql("INSERT INTO movies (id, name, year, director) VALUES (:id, :name, :year, :director)")
		   .dataSource(dataSource)
		   .build();
	}
	
	@Bean
	public ItemProcessor<Movies, Movies> processor() {
		return new MovieItemProcessor();
	}
	
	@Bean
	public Step step1(ItemReader<Movies> reader, ItemWriter<Movies> writer,
			ItemProcessor<Movies, Movies> processor) {
		 return stepBuilderFactory
		   .get("step1")
		   .<Movies, Movies> chunk(5)
		   .reader(reader)
		   .processor(processor)
		   .writer(writer)
		   .build();
	}
}
