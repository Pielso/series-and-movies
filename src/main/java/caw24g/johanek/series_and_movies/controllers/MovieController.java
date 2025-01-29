package caw24g.johanek.series_and_movies.controllers;

import caw24g.johanek.series_and_movies.models.Movie;
import caw24g.johanek.series_and_movies.services.MovieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;
    private final String exceptionMessage = "Fields may not be empty, and rating must be between 1-10.";

    /* At the fine url /movies, all movies in the repo are loaded onto a list, given an attribute name,
    which then is accessed through Thymeleaf in the .html.
     */

    @GetMapping("/movies")
    public String getMoviesHome(Model model) {

        List<Movie> allMovies = movieService.getAllMovies();
        model.addAttribute("movies", allMovies);
        return "movies-list-view";
    }

    // Initial seed (15 movies) for the Docker Container.

    @PostMapping("/seed-movies")
    public String seedInitialMovies() {

        movieService.seedInitialMovies();
        return "redirect:/movies";
    }

    // Create.

    /* I realize that there is an opportunity for refactoring (extracting) here, due to the repeated code in save and update, and I did give it a shot,
    but I didn't manage to find a clean solution. Sure one can extract the check for errors,
    but since the RedirectAttributes, BindingResults (and in update the HttpServletRequest) needs to be passed on, I felt it was spaghetti?
    tl;dr - A more competent dev would surely refactor these (save and update) but as for now, six months after knowing nothing, this will have to do.
     */

    @Validated
    @PostMapping("/movies")
    public String saveNewMovie(@Valid @ModelAttribute Movie movie, RedirectAttributes rdMessage, BindingResult binding){

        if (binding.hasErrors()){
            rdMessage.addFlashAttribute("message", exceptionMessage);
            return "redirect:/movies";
        }
        try {
            String movieSaved = movieService.saveMovie(movie);
            rdMessage.addFlashAttribute("message", movieSaved);
            return "redirect:/movies";

        }
        catch (Exception e) {
            rdMessage.addFlashAttribute("error", e.getMessage());
            return "redirect:/movies";
        }
    }

    // Read. - No exceptions handled since I think they cannot occur?

    @GetMapping("/movies/{id}")
    public String getSingleMovie(@PathVariable("id") long id, Model model){

        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "single-movie-view";
    }

    // Update.

    /* I realize that there is an opportunity for refactoring (extracting) here, due to the repeated code in save and update, and I did give it a shot,
    but I didn't manage to find a clean solution. Sure one can extract the check for errors,
    but since the RedirectAttributes, BindingResults (and in update the HttpServletRequest) needs to be passed on, I felt it was spaghetti?
    tl;dr - A more competent dev would surely refactor these (save and update) but as for now, six months after knowing nothing, this will have to do.
     */

    @Transactional // This solved an issue with invalid user input to update, but also made my exception handling moot.
    @PostMapping("/movies/update")
    public String updateMovie(long id, @ModelAttribute Movie movie, RedirectAttributes rdMessage, BindingResult binding, HttpServletRequest request){

        String referer = request.getHeader("referer");

        if (binding.hasErrors()){
            rdMessage.addFlashAttribute("message", exceptionMessage);
            return "redirect:" + referer;        }
        try {
            String movieUpdated = movieService.updateMovieById(movie, id);
            rdMessage.addFlashAttribute("message", movieUpdated);
            return "redirect:/movies";
        }
        catch (Exception e) {
            rdMessage.addFlashAttribute("error", e.getMessage());
            return "redirect:/movies";
        }
    }

    // Delete. - No exceptions handled since I think they cannot occur?

    @PostMapping("/movies/{id}")
    public String deleteMovie(@PathVariable("id") long id, RedirectAttributes rdMessage){

        String movieDeleted = movieService.deleteMovieById(id);
        rdMessage.addFlashAttribute("message", movieDeleted);
        return "redirect:/movies";
    }
}
