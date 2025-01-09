package caw24g.johanek.series_and_movies;

import caw24g.johanek.series_and_movies.models.Movie;
import caw24g.johanek.series_and_movies.models.Serie;
import caw24g.johanek.series_and_movies.services.MovieService;
import caw24g.johanek.series_and_movies.services.SerieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SeriesAndMoviesApplicationTests {

	@Autowired
	MovieService movieService;

	@Autowired
	SerieService serieService;

	// Test of CRUD-functions for Movie entity.

	@Test
	public void testSaveMovie() {

		// Arrange

		Movie movie = new Movie("testSave", 1);

		// Act

		movieService.saveMovie(movie);

		// Assert

        Assertions.assertEquals("testSave", movieService.getLastMovieInDatabase().getName(), "The last movie should be named testSave");

		// After

		movieService.deleteMovieByName("testSave");

	}

	@Test
	public void testGetMovie(){

		// Arrange
		List <Movie> list = new ArrayList<>();

		// Act

		list.add(movieService.getLastMovieInDatabase());

		// Assert

        Assertions.assertEquals(1, list.size(), "There should be a movie in the list");
	}

	@Test
	public void testUpdateMovie(){

		// Arrange

		Movie movie = new Movie("testPreUpdate", 1);

		// Act

		movieService.saveMovie(movie);
		movie.setName("testPostUpdate");
		movieService.updateMovieById(movie, movie.getId());

		// Assert

        Assertions.assertEquals("testPostUpdate", movieService.getLastMovieInDatabase().getName(), "The last movie should be named testPostUpdate");

		// After

		movieService.deleteMovieByName("testPostUpdate");

	}

	@Test
	public void testDeleteMovie(){

		// Arrange

		Movie movie = new Movie("testDelete", 1);

		// Act

		movieService.saveMovie(movie);
		long id = movieService.getLastMovieInDatabase().getId();
		movieService.deleteMovieByName("testDelete");

		// Assert

        Assertions.assertNull(movieService.getSingleMovie(id), "The movie testDelete should be removed");

	}

	// Test of CRUD-functions for Serie entity.

	@Test
	public void testSaveSerie() {

		// Arrange

		Serie serie = new Serie("testSave", 1);

		// Act

		serieService.saveSerie(serie);

		// Assert

        Assertions.assertEquals("testSave", serieService.getLastSerieInDatabase().getName(), "The last serie should be named testSave");

		// After

		serieService.deleteSerieByName("testSave");

	}

	@Test
	public void testGetSerie(){

		// Arrange
		List <Serie> list = new ArrayList<>();

		// Act

		list.add(serieService.getLastSerieInDatabase());

		// Assert

		Assertions.assertEquals(1, list.size(), "There should be a serie in the list");
	}

	@Test
	public void testUpdateSerie(){

		// Arrange

		Serie serie = new Serie("testPreUpdate", 1);

		// Act

		serieService.saveSerie(serie);
		serie.setName("testPostUpdate");
		serieService.updateSerieById(serie, serie.getId());

		// Assert

		Assertions.assertEquals("testPostUpdate", serieService.getLastSerieInDatabase().getName(), "The last serie should be named testPostUpdate");

		// After

		serieService.deleteSerieByName("testPostUpdate");

	}

	@Test
	public void testDeleteSerie(){

		// Arrange

		Serie serie = new Serie("testDelete", 1);

		// Act

		serieService.saveSerie(serie);
		long id = serieService.getLastSerieInDatabase().getId();
		serieService.deleteSerieByName("testDelete");

		// Assert

		Assertions.assertNull(serieService.getSingleSerie(id), "The serie testDelete should be removed");

	}

}
