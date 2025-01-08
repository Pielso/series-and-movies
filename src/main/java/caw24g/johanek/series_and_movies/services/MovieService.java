package caw24g.johanek.series_and_movies.services;

import caw24g.johanek.series_and_movies.models.Movie;
import caw24g.johanek.series_and_movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    public MovieRepository movieRepository;

    public MovieService(){

    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public void saveMovie(Movie movie){
        movieRepository.save(movie);
    }

    public void seedInitialMovies(){

        long totalMovieCount = movieRepository.count();

        if (totalMovieCount > 0){
            return;
        }

        Movie nobody = new Movie("Mr. Nobody", 10);
        Movie interstellar = new Movie("Interstellar", 10);
        Movie matrix = new Movie("The Matrix", 10);

        movieRepository.save(nobody);
        movieRepository.save(interstellar);
        movieRepository.save(matrix);

    }

    public Movie getSingleMovie(long id) {
        Optional <Movie> optionalMovie = movieRepository.findById(id);
        return optionalMovie.orElse(null);
    }

    public void deleteMovie(long id) {
        movieRepository.deleteById(id);
    }

    public void updateMovieById(Movie movie, long id){
        Movie oldMovie = getSingleMovie(id);
        oldMovie.setName(movie.getName());
        oldMovie.setRating(movie.getRating());
        movieRepository.save(oldMovie);
    }

    public Movie getLastMovieInDatabase(){
        List <Movie> list = movieRepository.findAll();
        return list.getLast();
    }

}
