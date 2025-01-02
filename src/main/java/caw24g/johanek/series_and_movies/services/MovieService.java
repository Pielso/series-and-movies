package caw24g.johanek.series_and_movies.services;

import caw24g.johanek.series_and_movies.models.Movie;
import caw24g.johanek.series_and_movies.models.Serie;
import caw24g.johanek.series_and_movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

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
}
