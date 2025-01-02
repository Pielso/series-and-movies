package caw24g.johanek.series_and_movies.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping ("/")
    public String getHome(){
        return "home";
    }
}
