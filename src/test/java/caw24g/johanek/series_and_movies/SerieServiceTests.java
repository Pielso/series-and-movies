package caw24g.johanek.series_and_movies;

import caw24g.johanek.series_and_movies.models.Serie;
import caw24g.johanek.series_and_movies.repositories.SerieRepository;
import caw24g.johanek.series_and_movies.services.SerieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SerieServiceTests {

    @InjectMocks
    SerieService serieService;

    @Mock
    SerieRepository serieRepository;


    @Test
    public void testSaveAndGetSerie(){

        // Saving a movie, then getting it.
        Serie testSerie = new Serie("Test", 1);

        when(serieRepository.findById(1L)).thenReturn(Optional.of(testSerie));
        String success = serieService.saveSerie(testSerie);
        Serie serie = serieService.getSerieById(1L);
        Assertions.assertEquals(testSerie, serie);
        Assertions.assertEquals("The serie was saved", success);
        Mockito.verify(serieRepository, times(1)).save(testSerie);
    }

    @Test
    public void testUpdateSerie(){

        // First saving a movie. Then changing it. Then asserting.
        Serie testSerie = new Serie("Old Name", 1);
        String success = serieService.saveSerie(testSerie);
        Assertions.assertEquals("The serie was saved", success);

        testSerie.setName("New Name");
        testSerie.setRating(5);

        when(serieRepository.findById(1L)).thenReturn(Optional.of(testSerie));
        serieService.updateSerieById(testSerie, 1);
        Assertions.assertEquals("New Name", serieService.getSerieById(1).getName());

    }
}
