package caw24g.johanek.series_and_movies.tests;

import caw24g.johanek.series_and_movies.controllers.MovieController;
import caw24g.johanek.series_and_movies.models.Movie;
import caw24g.johanek.series_and_movies.repositories.MovieRepository;
import caw24g.johanek.series_and_movies.services.MovieService;
import org.apache.coyote.Request;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class TestMoviesCRUD {

    @Test
    public void testAddMovie(){
        // Arrange
        Movie testMovie = new Movie("test", 1);
        MovieService movieService = new MovieService();


        // Act
        movieService.saveMovie(testMovie);



        // Assert

    }
}
