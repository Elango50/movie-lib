package com.movies.library.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;


@Configuration
public class DataSourceConfig {

	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource.movie")
	public DataSourceProperties movieDataSourceProperties() {
	    return new DataSourceProperties();
	}

	@Bean(name = "movieDB")
	@Primary
	@ConfigurationProperties("spring.datasource.movie.configuration")
	public HikariDataSource movieDataSource() {
	    return movieDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Bean(name = "movieJdbcTemplate")
	@Primary
	public JdbcTemplate jdbcTemplate(@Qualifier("movieDB") DataSource movieDB) {
		return new JdbcTemplate(movieDB);
	}

	@Bean
	@ConfigurationProperties("spring.datasource.rating")
	public DataSourceProperties ratingDataSourceProperties() {
	    return new DataSourceProperties();
	}

	@Bean(name = "ratingDB")
	@ConfigurationProperties("spring.datasource.rating.configuration")
	public HikariDataSource ratingDataSource() {
	    return ratingDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Bean(name = "ratingJdbcTemplate")
	public JdbcTemplate postgresJdbcTemplate(@Qualifier("ratingDB") DataSource ratingDB) {
		return new JdbcTemplate(ratingDB);
	}
}
