package caw24g.johanek.series_and_movies.repositories;

import caw24g.johanek.series_and_movies.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository <Movie, Long> {


}
