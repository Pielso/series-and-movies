package caw24g.johanek.series_and_movies.controllers;

import caw24g.johanek.series_and_movies.services.MovieService;
import caw24g.johanek.series_and_movies.services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @GetMapping ("/home") //http://localhost:8080/home
    public String getHome(){
        return "home";
    }
}