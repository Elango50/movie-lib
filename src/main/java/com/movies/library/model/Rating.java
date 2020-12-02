package com.movies.library.model;

public class Rating {
	
	private long id;
	private String name;
	private int year;
	private int rating;
	
	public Rating() {}
	
	public Rating(long id, String name, int year, int rating) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.rating = rating;
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
	
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
