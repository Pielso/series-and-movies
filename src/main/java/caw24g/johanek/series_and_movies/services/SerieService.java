package caw24g.johanek.series_and_movies.services;


import caw24g.johanek.series_and_movies.models.Movie;
import caw24g.johanek.series_and_movies.models.Serie;
import caw24g.johanek.series_and_movies.repositories.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieService {
    @Autowired
    private SerieRepository serieRepository;

    public List<Serie> getAllSeries(){
        return serieRepository.findAll();
    }

    public void saveSerie(Serie serie){
        serieRepository.save(serie);
    }

    public void seedInitialSeries(){

        long totalSeriesCount = serieRepository.count();

        if (totalSeriesCount > 0){
            return;
        }

        Serie bsg = new Serie("Battlestar Galactica", 10);
        Serie fringe = new Serie("Fringe", 10);
        Serie sttng = new Serie("Star Trek: The Next Generation", 10);

        serieRepository.save(bsg);
        serieRepository.save(fringe);
        serieRepository.save(sttng);

    }

    public Serie getSingleSerie(long id) {
        Optional <Serie> optionalSerie = serieRepository.findById(id);
        return optionalSerie.orElse(null);
    }

    public void deleteSerie(long id) {
        serieRepository.deleteById(id);
    }

    public void updateSerieById(Serie serie, long id) {
        Serie oldSerie = getSingleSerie(id);
        oldSerie.setName(serie.getName());
        oldSerie.setRating(serie.getRating());
        serieRepository.save(oldSerie);
    }
}
