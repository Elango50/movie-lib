package com.movies.library.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.movies.library.dao.MoviesDao;
import com.movies.library.mapper.MoviesMapper;
import com.movies.library.model.Movies;

@Component
public class MoviesDaoImpl implements MoviesDao {
	
    @Autowired
	@Qualifier("movieJdbcTemplate")
	private JdbcTemplate template;
    
    public List<Movies> getAllmovies() {
        return (List<Movies>) template.query("SELECT * FROM MOVIES", new MoviesMapper());
    }

	@Override
	public Movies getMovie(long id) {
		String SQL = "SELECT * FROM MOVIES WHERE id = ?";
		Movies movie = (Movies) template.queryForObject(SQL, new MoviesMapper(), new Object[]{id});
		return movie;
	}

	@Override
	public void deleteMovieById(long id) {
		String sql = "Delete FROM MOVIES WHERE id = ?";
		template.update(sql, id);
	}

	@Override
	public void updateMovie(Movies movie) {
		String sql = "Update MOVIES SET name = ? , year = ? , director = ? WHERE id = ?";
		template.update(sql, new MoviesMapper(), movie.getName(), movie.getYear(), movie.getDirector(), movie.getId());
	}
}
