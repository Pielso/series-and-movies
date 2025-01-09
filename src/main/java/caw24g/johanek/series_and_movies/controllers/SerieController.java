package caw24g.johanek.series_and_movies.controllers;

import caw24g.johanek.series_and_movies.models.Serie;
import caw24g.johanek.series_and_movies.services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;


@Controller
public class SerieController {

    @Autowired
    private SerieService serieService;

    /* At the lovely url /series, all series in the repo are loaded onto a list, given an attribute name,
    which then is accessed through Thymeleaf in the .html.
     */

    @GetMapping("/series")
    public String getSeriesHome(Model model){
        List <Serie> allSeries = serieService.getAllSeries();
        model.addAttribute("series", allSeries);
        return "series-list-view";
    }

    // May possible be removed after checking with Master Of Code: Linus.

    @PostMapping("/seed-series")
    public String seedInitialSeries(){
        serieService.seedInitialSeries();
        return "redirect:/series";
    }

    // Create.

    @PostMapping("/series")
    public String saveNewSerie(@ModelAttribute Serie serie){
        serieService.saveSerie(serie);
        return "redirect:/series";
    }

    // Read.

    @GetMapping("/series/{id}")
    public String getSingleSerie(@PathVariable("id") long id, Model model){
        Serie serie = serieService.getSingleSerie(id);
        model.addAttribute("serie", serie);
        return "single-serie-view";
    }

    // Update.

    @PostMapping("/series/update")
    public String updateSerie(long id, @ModelAttribute Serie serie){
        serieService.updateSerieById(serie, id);
        return "redirect:/series";
    }

    // Delete.

    @PostMapping("/series/{id}")
    public String deleteSerie(@PathVariable("id") long id){
        serieService.deleteSerie(id);
        return "redirect:/series";
    }
}
