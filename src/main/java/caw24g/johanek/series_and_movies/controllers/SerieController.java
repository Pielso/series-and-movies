package caw24g.johanek.series_and_movies.controllers;

import caw24g.johanek.series_and_movies.models.Serie;
import caw24g.johanek.series_and_movies.services.SerieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class SerieController {

    @Autowired
    private SerieService serieService;
    private final String exceptionMessage = "Fields may not be empty, and rating must be between 1-10.";

    /* At the lovely url /series, all series in the repo are loaded onto a list, given an attribute name,
    which then is accessed through Thymeleaf in the .html.
     */

    @GetMapping("/series")
    public String getSeriesHome(Model model){

        List <Serie> allSeries = serieService.getAllSeries();
        model.addAttribute("series", allSeries);
        return "series-list-view";
    }

    // Initial seed (24 series) for the Docker Container.

    @PostMapping("/seed-series")
    public String seedInitialSeries(){

        serieService.seedInitialSeries();
        return "redirect:/series";
    }

    // Create.

    /* I realize that there is an opportunity for refactoring (extracting) here, due to the repeated code in save and update, and I did give it a shot,
    but I didn't manage to find a clean solution. Sure one can extract the check for errors,
    but since the RedirectAttributes, BindingResults (and in update the HttpServletRequest) needs to be passed on, I felt it was spaghetti?
    tl;dr - A more competent dev would surely refactor these (save and update) but as for now, six months after knowing nothing, this will have to do.
     */

    @Validated
    @PostMapping("/series")
    public String saveNewSerie(@Valid @ModelAttribute Serie serie, RedirectAttributes rdMessage, BindingResult binding){

        if (binding.hasErrors()){
            rdMessage.addFlashAttribute("message", exceptionMessage);
            return "redirect:/series";
        }
        try {
            String serieSaved = serieService.saveSerie(serie);
            rdMessage.addFlashAttribute("message", serieSaved);
            return "redirect:/series";

        }
        catch (Exception e) {
            rdMessage.addFlashAttribute("error", e.getMessage());
            return "redirect:/series";
        }
    }

    // Read. - No exceptions handled since I think they cannot occur?

    @GetMapping("/series/{id}")
    public String getSingleSerie(@PathVariable("id") long id, Model model){

        Serie serie = serieService.getSerieById(id);
        model.addAttribute("serie", serie);
        return "single-serie-view";
    }

    // Update.
    /* I realize that there is an opportunity for refactoring (extracting) here, due to the repeated code in save and update, and I did give it a shot,
    but I didn't manage to find a clean solution. Sure one can extract the check for errors,
    but since the RedirectAttributes, BindingResults (and in update the HttpServletRequest) needs to be passed on, I felt it was spaghetti?
    tl;dr - A more competent dev would surely refactor these (save and update) but as for now, six months after knowing nothing, this will have to do.
     */

    @Transactional // This solved an issue with invalid user input to update, but also made my exception handling moot.
    @PostMapping("/series/update")
    public String updateSerie(long id, @ModelAttribute Serie serie, RedirectAttributes rdMessage, BindingResult binding, HttpServletRequest request){

        String referer = request.getHeader("referer");
        if (binding.hasErrors()){
            rdMessage.addFlashAttribute("message", exceptionMessage);
            return "redirect:" + referer;
        }
        try {
            String serieUpdated = serieService.updateSerieById(serie, id);
            rdMessage.addFlashAttribute("message", serieUpdated);
            return "redirect:/series";

        }
        catch (Exception e) {
            rdMessage.addFlashAttribute("error", e.getMessage());
            return "redirect:/series";
        }
    }

    // Delete. - No exceptions handled since I think they cannot occur?

    @PostMapping("/series/{id}")
    public String deleteSerie(@PathVariable("id") long id, RedirectAttributes rdMessage){

        String serieDeleted = serieService.deleteSerieById(id);
        rdMessage.addFlashAttribute("message", serieDeleted);
        return "redirect:/series";
    }
}

