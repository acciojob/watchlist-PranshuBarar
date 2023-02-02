package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    //working
    @PostMapping("/movies/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        String result = movieService.addMovie(movie);
        if(result.equals("Added")){
            return new ResponseEntity<>("Added", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Already there", HttpStatus.BAD_REQUEST);
    }

    //working
    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String name){
        Movie movie = movieService.getMovieByName(name);
        if(movie == null){
            return new ResponseEntity<>(movie, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movie, HttpStatus.FOUND);
    }

    //working
    @GetMapping("/movies/get-all-movies")
    public ResponseEntity findAllMovies(){
        return new ResponseEntity<>(movieService.findAllMovies(), HttpStatus.FOUND);
    }

    //working
    @PostMapping("/movies/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        String result = movieService.addDirector(director);
        if(result.equals("Already there")){
            return new ResponseEntity<>("Already there", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Added", HttpStatus.CREATED);
    }

    //working
    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String name){
        Director director = movieService.getDirectorByName(name);
        if(director == null){
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(director, HttpStatus.FOUND);
    }

    //working
    @PostMapping("/movies/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movieName") String movieName, @RequestParam("directorName") String directorName){
        movieService.addMovieDirectorPair(movieName,directorName);
        return new ResponseEntity<>("Added Successfully", HttpStatus.CREATED);
    }


    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String directorName){
        List<String> nameList = movieService.getMoviesByDirectorName(directorName);
        return new ResponseEntity<>(nameList, HttpStatus.FOUND);

    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("name") String name){
        String result = movieService.deleteDirectorByName(name);
        if(result.equals("Deleted")){
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Invalid Input", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.ACCEPTED);
    }
}
