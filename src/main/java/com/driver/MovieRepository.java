package com.driver;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {
    Map<String,Movie> movieDB = new HashMap<>();
    Map<String,Director> directorDB = new HashMap<>();
    Map<String,List<String>> pairDB = new HashMap<>();

    public String addMovie(Movie movie){
        String name = movie.getName();
        if(!movieDB.containsKey(name)){
            movieDB.put(name,movie);
            return "Added";
        }
        return "Already there";
    }

    public Movie getMovieByName(String name){
        if(movieDB.containsKey(name)){
            return movieDB.get(name);
        }
        return null;
    }

    public List<String> findAllMovies(){
        return new ArrayList<>(movieDB.keySet());
    }

    public String addDirector(Director director){
        String name = director.getName();
        if(directorDB.containsKey(name)){
            return "Already there";
        }
        directorDB.put(name,director);
        return "Added";
    }

    public Director getDirectorByName(String directorName){
        if(directorDB.containsKey(directorName)){
            return directorDB.get(directorName);
        }
        return null;
    }

    //Pass movie name and director name as request parameters
    public void addMovieDirectorPair(String movieName, String directorName){
        Movie movie = movieDB.get(movieName);

        if(pairDB.containsKey(directorName)){
            for(String m : pairDB.get(directorName)){
                if(m.equals(movieName)){
                    return;
                }
            }
            List<String> list = pairDB.get(directorName);
            list.add(movieName);
            return;
        }
        List<String> list = new ArrayList<>();
        list.add(movieName);

        pairDB.put(directorName, list);
    }

    /*Get List of movies name for a given director name: GET /movies/get-movies-by-director-name/{director}
    Pass director name as path parameter
    Return List of movies name(List()) wrapped in a ResponseEntity object
    Controller Name - getMoviesByDirectorName*/
    public List<String> getMoviesByDirectorName(String directorName){
        if(!pairDB.containsKey(directorName)){
            return new ArrayList<>();
        }
        return pairDB.get(directorName);
    }

    /*Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
    Pass director’s name as request parameter
    Return success message wrapped in a ResponseEntity object
    Controller Name - deleteDirectorByName*/
    public String deleteDirectorByName(String directorName){
        if(pairDB.containsKey(directorName)){
            pairDB.remove(directorName);
            return "Deleted";
        }
        return "Invalid Input";

    }

    /*Delete all directors and all movies by them from the records: DELETE /movies/delete-all-directors
    No params or body required
    Return success message wrapped in a ResponseEntity object
    Controller Name - deleteAllDirectors
    (Note that there can be some movies on your watchlist that aren’t mapped to any of the director.
    Make sure you do not remove them.)*/
    public void deleteAllDirectors(){
        if(!pairDB.isEmpty()){
            pairDB.clear();
        }
        if(!directorDB.isEmpty()){
            directorDB.clear();
        }
    }

}
