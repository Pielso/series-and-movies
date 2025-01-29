package caw24g.johanek.series_and_movies;

import caw24g.johanek.series_and_movies.controllers.MovieController;
import caw24g.johanek.series_and_movies.models.Movie;
import caw24g.johanek.series_and_movies.services.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
public class MovieControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    MovieService movieService;


    // Great movie from back in the days.
    Movie catsAndDogs = new Movie("Homeward Bound: The Incredible Journey", 8);

    @Test
    public void testGetMovie() throws Exception {
        when(movieService.getMovieById(1)).thenReturn(catsAndDogs);
        mockMvc.perform(MockMvcRequestBuilders.get("/movies/{id}",1))
                .andExpect(status().isOk())
                .andExpect(view().name("single-movie-view"));
    }

    @Test
    public void testSaveNewMovie() throws Exception {

        // Arrange
        String movieName = "Test";
        int movieRating = 1;

        // Act: Saving the movie, and expecting to be redirected to the same page.
        mockMvc.perform(MockMvcRequestBuilders.post("/movies")
                        .param("name", movieName)
                        .param("rating", String.valueOf(movieRating)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies"));

        // Assert: Checking that save was called.
        Mockito.verify(movieService, Mockito.times(1)).saveMovie(Mockito.any(Movie.class));
    }

    @Test
    public void testSaveNewMovie_Fail() throws Exception {

        String movieName = "";
        int movieRating = 12;

        // Act: Saving a movie with invalid input, and expecting to be redirected and to get an exception message.
        mockMvc.perform(MockMvcRequestBuilders.post("/movies")
                        .param("name", movieName)
                        .param("rating", String.valueOf(movieRating)))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("error", "Fields may not be empty, and rating must be between 1-10.")); // Verify the redirection URL
    }

    @Test
    public void testUpdateMovie() throws Exception {

        // Arrange
        String movieName = "Test";
        int movieRating = 1;

        // Act: Saving the movie, and expecting to be redirected to the same page.
        mockMvc.perform(MockMvcRequestBuilders.post("/movies")
                        .param("name", movieName)
                        .param("rating", String.valueOf(movieRating)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies"));

        // Changing the pseudo-movie-properties.
        movieName = "Test Movie";
        movieRating = 2;

        // Now we will update the movie by changing the name to "Test Movie" and the rating to 2.
        mockMvc.perform(MockMvcRequestBuilders.post("/movies/update")
                        .param("id", "1")
                        .param("name", movieName)
                        .param("rating", String.valueOf(movieRating)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies"));

        // Assert: Check if the service save method was called, and loading parameters into a Movie object.
        Mockito.verify(movieService, Mockito.times(1)).updateMovieById(Mockito.any(Movie.class), Mockito.anyLong());
    }

    @Test
    public void testUpdateMovie_Fail() throws Exception {

        // Saving the movie, and expecting to be redirected to the same page.
        mockMvc.perform(MockMvcRequestBuilders.post("/movies")
                        .param("name", "A")
                        .param("rating", String.valueOf(10)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies")); // Verify the redirection URL
        verify(movieService, times(1)).saveMovie(any(Movie.class));

        // Act: This should produce a flash attribute error message.
        mockMvc.perform(MockMvcRequestBuilders.post("/movies/update")
                        .param("id", "1")
                        .param("name", "")
                        .param("rating", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("error", "Fields may not be empty, and rating must be between 1-10."));

        // Assert: Making sure that the update was not involved, and that exception was thrown already @ Controller Method.
        Mockito.verify(movieService, Mockito.times(0)).updateMovieById(Mockito.any(Movie.class), Mockito.anyLong());
    }

    @Test
    public void testDeleteMovie() throws Exception {

        String movieName = "Test";
        int movieRating = 1;
        long movieId = 1;

        // Arrange: Saving the movie, and expecting to be redirected to the same page.
        mockMvc.perform(MockMvcRequestBuilders.post("/movies")
                        .param("name", movieName) // Pass the name
                        .param("rating", String.valueOf(movieRating))) // Pass the rating
                .andExpect(status().is3xxRedirection()) // Verify the redirection status
                .andExpect(redirectedUrl("/movies")); // Verify the redirection URL

        // Act: This movie didn't live up to the hype, so it has to go.
        mockMvc.perform(MockMvcRequestBuilders.post("/movies/{id}", movieId)
                        .param("id", String.valueOf(movieId)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies"));

        // Assert: Checking if the service delete method was called.
        Mockito.verify(movieService, Mockito.times(1)).deleteMovieById(Mockito.anyLong());
    }
}
