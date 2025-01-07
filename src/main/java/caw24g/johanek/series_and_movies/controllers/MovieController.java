package caw24g.johanek.series_and_movies.controllers;

import caw24g.johanek.series_and_movies.models.Movie;
import caw24g.johanek.series_and_movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public String getMoviesHome(Model model){
        List<Movie> allMovies = movieService.getAllMovies();
        model.addAttribute("movies", allMovies);
        return "movies-list-view";
    }

    @PostMapping("/seed-movies")
    public String seedInitialMovies(){
        movieService.seedInitialMovies();
        return "redirect:/movies";
    }

    @PostMapping("/movies")
    public String saveNewMovie(@ModelAttribute Movie movie){
        movieService.saveMovie(movie);
        return "redirect:/movies";
    }

    @GetMapping("/movies/{id}")
    public String getSingleMovie(@PathVariable("id") long id, Model model){
        Movie movie = movieService.getSingleMovie(id);
        model.addAttribute("movie", movie);
        return "single-movie-view";
    }

    @PostMapping("/movies/{id}")
    public String deleteMovie(@PathVariable("id") long id){
        movieService.deleteMovie(id);
        return "redirect:/movies";
    }

    // Får inte denna att fungera
    @PostMapping("/movies/update")
    public String updateMovie(long id, @ModelAttribute Movie movie){
        movieService.updateMovieById(movie,id);
        return "redirect:/movies";
    }



}
