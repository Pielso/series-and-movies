package caw24g.johanek.series_and_movies.services;


import caw24g.johanek.series_and_movies.models.Serie;
import caw24g.johanek.series_and_movies.repositories.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
