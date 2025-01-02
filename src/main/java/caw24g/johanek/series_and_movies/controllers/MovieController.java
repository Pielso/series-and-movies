package caw24g.johanek.series_and_movies.controllers;

import caw24g.johanek.series_and_movies.models.Movie;
import caw24g.johanek.series_and_movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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


}
