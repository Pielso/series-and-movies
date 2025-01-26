package caw24g.johanek.series_and_movies.services;

import caw24g.johanek.series_and_movies.models.Movie;
import caw24g.johanek.series_and_movies.repositories.MovieRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // Probably not needed, but maybe for tests?
    public MovieService(){
    }

    // Kept this, since the db in Docker Compose Container will be empty from start.
    public void seedInitialMovies(){
        long totalMovieCount = movieRepository.count();
        if (totalMovieCount > 0){
            return;
        }

        Movie movie1 = new Movie("Mr. Nobody", 10);
        Movie movie2 = new Movie("Interstellar", 10);
        Movie movie3 = new Movie("The Matrix", 10);
        Movie movie4 = new Movie("The Langoliers", 9);
        Movie movie5 = new Movie("Grave of the Fireflies", 10);
        Movie movie6 = new Movie("La vita é bella", 10);
        Movie movie7 = new Movie("K-Pax", 10);
        Movie movie8 = new Movie("Cube", 10);
        Movie movie9 = new Movie("Total Recall", 9);
        Movie movie10 = new Movie("Demolition Man", 8);
        Movie movie11 = new Movie("Lord of the Rings: Trilogy", 10);
        Movie movie12 = new Movie("Cloud Atlas", 9);
        Movie movie13 = new Movie("V for Vendetta", 10);
        Movie movie14 = new Movie("Avatar", 10);
        Movie movie15 = new Movie("Dumb & Dumber", 10);

        movieRepository.saveAll(Arrays.asList(
                movie1, movie2, movie3, movie4, movie5, movie6, movie7, movie8,
                movie9, movie10, movie11, movie12, movie13, movie14, movie15));
    }

    public List <Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public String saveMovie(Movie movie){
        movieRepository.save(movie);
        return "The movie was saved";
    }

    public Movie getMovieById(long id) {
        Optional <Movie> optionalMovie = movieRepository.findById(id);
        return optionalMovie.orElse(null);
    }

    public String deleteMovieById(long id) {
        movieRepository.deleteById(id);
        return "The movie was deleted";
    }

    /* Have no idea why IntelliJ wanted me to annotate @NotNull here. It worked fine without it,
    but when I was cleaning up the warnings it was suggested to put it there, so I did.
     */

    public String updateMovieById(@NotNull Movie movie, long id){
        Movie oldMovie = getMovieById(id);
        oldMovie.setName(movie.getName());
        oldMovie.setRating(movie.getRating());
        movieRepository.save(oldMovie);
        return "The movie was updated";
    }

}
