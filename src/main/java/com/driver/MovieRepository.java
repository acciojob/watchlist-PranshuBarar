package com.driver;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    Map<String,Movie> movieDB = new HashMap<>();
    Map<String,Director> directorDB = new HashMap<>();
    Map<String,List<String>> pairDB = new HashMap<>();

    public void addMovie(Movie movie){
        String name = movie.getName();
        movieDB.put(name,movie);

    }

    public Movie getMovieByName(String name){
        return movieDB.get(name);
    }

    public List<String> findAllMovies(){
        return new ArrayList<>(movieDB.keySet());
    }

    public void addDirector(Director director){
        String name = director.getName();
        directorDB.put(name,director);
    }

    public Director getDirectorByName(String directorName){
        return directorDB.get(directorName);
    }

    //Pass movie name and director name as request parameters
    public void addMovieDirectorPair(String movieName, String directorName){
        if(movieDB.containsKey(movieName) && directorDB.containsKey(directorName)){
            if(pairDB.containsKey(directorName)){
                List<String> list = pairDB.get(directorName);
                list.add(movieName);
                return;
            }
            List<String> list = new ArrayList<>();
            list.add(movieName);

            pairDB.put(directorName, list);
        }
    }

    /*Get List of movies name for a given director name: GET /movies/get-movies-by-director-name/{director}
    Pass director name as path parameter
    Return List of movies name(List()) wrapped in a ResponseEntity object
    Controller Name - getMoviesByDirectorName*/
    public List<String> getMoviesByDirectorName(String directorName){
        List<String> list = new ArrayList<>();
        if(pairDB.containsKey(directorName)){
            list = pairDB.get(directorName);
        }
        return list;
    }

    /*Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
    Pass director’s name as request parameter
    Return success message wrapped in a ResponseEntity object
    Controller Name - deleteDirectorByName*/
    public void deleteDirectorByName(String directorName){
        List<String> movies;
        if(pairDB.containsKey(directorName)){
            movies = pairDB.get(directorName);
            for(String movie : movies){
                movieDB.remove(movie);
            }
            pairDB.remove(directorName);
        }
        directorDB.remove(directorName);

    }

    /*Delete all directors and all movies by them from the records: DELETE /movies/delete-all-directors
    No params or body required
    Return success message wrapped in a ResponseEntity object
    Controller Name - deleteAllDirectors
    (Note that there can be some movies on your watchlist that aren’t mapped to any of the director.
    Make sure you do not remove them.)*/
    public void deleteAllDirectors(){
        for(List<String> movie : pairDB.values()){
            for(String m : movie){
                if(movieDB.containsKey(m)){
                    movieDB.remove(m);
                }
            }
        }

        if(!pairDB.isEmpty()){
            pairDB.clear();
        }
        if(!directorDB.isEmpty()){
            directorDB.clear();
        }
    }

}
