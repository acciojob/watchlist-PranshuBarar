package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    //working
    @PostMapping("/movies/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    //working
    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String name){
        Movie movie = movieService.getMovieByName(name);
        return new ResponseEntity<>(movie, HttpStatus.FOUND);
    }

    //working
    @GetMapping("/movies/get-all-movies")
    public ResponseEntity findAllMovies(){
        List<String> response = movieService.findAllMovies();
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    //working
    @PostMapping("/movies/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        movieService.addDirector(director);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    //working
    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String name){
        Director director = movieService.getDirectorByName(name);
        return new ResponseEntity<>(director, HttpStatus.FOUND);
    }

    //working
    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movieName") String movieName, @RequestParam("directorName") String directorName){
        movieService.addMovieDirectorPair(movieName,directorName);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }


    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String directorName){
        List<String> nameList = movieService.getMoviesByDirectorName(directorName);
        return new ResponseEntity<>(nameList, HttpStatus.FOUND);

    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("name") String name){
        movieService.deleteDirectorByName(name);
        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
    }
}
