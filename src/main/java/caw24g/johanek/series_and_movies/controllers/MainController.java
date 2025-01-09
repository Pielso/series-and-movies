package caw24g.johanek.series_and_movies.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // Separate controller for the homepage. Could be expanded to include peripheral information such as CONTACT, ABOUT and so on.

    @GetMapping ("/home") // http://localhost:8080/home
    public String getHome(){
        return "home";
    }
}
