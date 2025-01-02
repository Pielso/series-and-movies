package caw24g.johanek.series_and_movies.controllers;

import caw24g.johanek.series_and_movies.models.Serie;
import caw24g.johanek.series_and_movies.services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class SerieController {

    @Autowired
    private SerieService serieService;

    @GetMapping("/series")
    public String getSeriesHome(Model model){
        List <Serie> allSeries = serieService.getAllSeries();
        model.addAttribute("series", allSeries);
        return "series-list-view";
    }

    @PostMapping("/seed-series")
    public String postSeedSeries(){
        serieService.seedInitialSeries();
        return "redirect:/series";
    }

    @PostMapping("/series")
    public String saveNewSerie(@ModelAttribute Serie serie){
        serieService.saveSerie(serie);
        return "redirect:/series";
    }


}
