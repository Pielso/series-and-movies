package caw24g.johanek.series_and_movies.services;

import caw24g.johanek.series_and_movies.models.Serie;
import caw24g.johanek.series_and_movies.repositories.SerieRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    // Probably not needed, but maybe for tests?
    public SerieService(){
    }

    // Kept this, since the db in Docker Compose Container will be empty from start.
    public void seedInitialSeries(){
        long totalSeriesCount = serieRepository.count();
        if (totalSeriesCount > 0){
            return;
        }

        Serie serie1 = new Serie("Battlestar Galactica", 10);
        Serie serie2 = new Serie("Fringe", 10);
        Serie serie3 = new Serie("Star Trek: TNG", 10);
        Serie serie4 = new Serie("Farscape", 9);
        Serie serie5 = new Serie("Stargate SG-1", 7);
        Serie serie6 = new Serie("Stargate: Atlantis", 8);
        Serie serie7 = new Serie("Heroes", 9);
        Serie serie8 = new Serie("Lost", 9);
        Serie serie9 = new Serie("Star Trek: TOS", 10);
        Serie serie10 = new Serie("Star Trek DS9", 10);
        Serie serie11 = new Serie("Star Trek: Picard", 10);
        Serie serie12 = new Serie("Star Trek: Discovery", 10);
        Serie serie13 = new Serie("The Expanse", 10);
        Serie serie14 = new Serie("Star Trek: Voyager", 10);
        Serie serie15 = new Serie("Star Trek: Enterprise", 8);
        Serie serie16 = new Serie("Star Trek: Strange New Worlds", 9);
        Serie serie17 = new Serie("The 100", 10);
        Serie serie18 = new Serie("X-Files", 10);
        Serie serie19 = new Serie("The Office", 10);
        Serie serie20 = new Serie("Game of Thrones", 10);
        Serie serie21 = new Serie("Stranger Things", 10);
        Serie serie22 = new Serie("Black Mirror", 10);
        Serie serie23 = new Serie("Twin Peaks", 10);
        Serie serie24 = new Serie("The Three Body Problem", 9);

        serieRepository.saveAll(Arrays.asList(
                serie1, serie2, serie3, serie4, serie5, serie6, serie7,
                serie8, serie9, serie10, serie11, serie12, serie13, serie14,
                serie15, serie16, serie17, serie18, serie19, serie20, serie21,
                serie22, serie23, serie24
        ));
    }

    public List <Serie> getAllSeries(){
        return serieRepository.findAll();
    }


    public String saveSerie(Serie serie){
        serieRepository.save(serie);
        return "The serie was saved";
    }

    public Serie getSerieById(long id) {
        Optional <Serie> optionalSerie = serieRepository.findById(id);
        return optionalSerie.orElse(null);
    }

    public String deleteSerieById(long id) {
        serieRepository.deleteById(id);
        return "The serie was deleted";
    }

    /* Have no idea why IntelliJ wanted me to annotate @NotNull here. It worked fine without it,
    but when I was cleaning up the warnings it was suggested to put it there, so I did.
     */

    public String updateSerieById(@NotNull Serie serie, long id) {
        Serie oldSerie = getSerieById(id);
        oldSerie.setName(serie.getName());
        oldSerie.setRating(serie.getRating());
        serieRepository.save(oldSerie);
        return "The serie was updated";
    }

}
