package caw24g.johanek.series_and_movies.controllers;

import caw24g.johanek.series_and_movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public String getMoviesHome(){
        return "movies";
    }


}
