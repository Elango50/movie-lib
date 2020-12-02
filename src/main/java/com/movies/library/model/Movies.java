package com.movies.library.model;

public class Movies {
	
	private long id;
	private String name;
	private int year;
	private String director;
	
	public Movies() {}
	
	public Movies(long id, String name, int year, String director) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.director = director;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getDirector() {
		return director;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
