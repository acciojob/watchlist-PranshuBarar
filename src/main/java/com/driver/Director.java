package com.driver;

//Director.java Class - String name, int numberOfMovies, double imdbRating,
// no-args constructor, all-args constructor and getters-setters

public class Director {
    private String name;
    private int numberOfMovies;
    private double imdbRating;

    public Director() {

    }

    public Director(String name, int numberOfMovies, double imdbRating) {
        this.name = name;
        this.numberOfMovies = numberOfMovies;
        this.imdbRating = imdbRating;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfMovies() {
        return numberOfMovies;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfMovies(int numberOfMovies) {
        this.numberOfMovies = numberOfMovies;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }
}
