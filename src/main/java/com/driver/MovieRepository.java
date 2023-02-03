package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {


    Map<String, Movie> dbMovie = new HashMap<>();
    Map<String, Director> dbDirector = new HashMap<>();
    HashMap<String, List<String>> movieDirectorDb = new HashMap<>();


    public void addMovie(Movie movie) {
        String name = movie.getName();
        dbMovie.put(name, movie);
    }

    public void addDirector(Director director) {
        String name = director.getName();
        dbDirector.put(name, director);
    }

    public void addMovieDirectorPair(String movieName, String directorName) {
        if(dbMovie.containsKey(movieName) && dbDirector.containsKey(directorName)){
            List<String> currentMovies = new ArrayList<>();
            if(movieDirectorDb.containsKey(directorName)) {
                currentMovies = movieDirectorDb.get(directorName);
            }
            currentMovies.add(movieName);
            movieDirectorDb.put(directorName, currentMovies);
        }
    }

    public Movie getMovieByName(String movieName) {
        return dbMovie.get(movieName);
    }

    public Director getDirectorByName(String directorName) {
        return dbDirector.get(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        List<String> moviesList = new ArrayList<>();
        if(movieDirectorDb.containsKey(directorName)){
            moviesList = movieDirectorDb.get(directorName);
        }
        return moviesList;
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(dbMovie.keySet());
    }

    public void deleteDirectorByName(String directorName) {
        List<String> movies ;
        if(movieDirectorDb.containsKey(directorName)){
            movies = movieDirectorDb.get(directorName);
            for(String movie: movies){
                dbMovie.remove(movie);
            }
            movieDirectorDb.remove(directorName);
        }
        dbDirector.remove(directorName);
    }

    public void deleteAllDirectors() {
        HashSet<String> moviesSet = new HashSet<>();

        for(String director: movieDirectorDb.keySet()){
            moviesSet.addAll(movieDirectorDb.get(director));
        }

        for(String movie: moviesSet){
            dbMovie.remove(movie);
        }
        dbDirector.clear();
    }
}