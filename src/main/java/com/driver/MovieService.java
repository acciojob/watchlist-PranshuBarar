package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(Movie movie){
        return movieRepository.addMovie(movie);
    }

    public Movie getMovieByName(String name){
        return movieRepository.getMovieByName(name);
    }

    public List<Movie> findAllMovies(){
        return movieRepository.findAllMovies();
    }

    public String addDirector(Director director){
        return movieRepository.addDirector(director);
    }

    public Director getDirectorByName(String name){
        return movieRepository.getDirectorByName(name);
    }

    public void addMovieDirectorPair(String movieName, String directorName){
        movieRepository.addMovieDirectorPair(movieName,directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName){
        return movieRepository.getMoviesByDirectorName(directorName);
    }

    public String deleteDirectorByName(String directorName){
        return movieRepository.deleteDirectorByName(directorName);

    }

    public void deleteAllDirectors(){
        movieRepository.deleteAllDirectors();
    }

}
