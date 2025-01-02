package caw24g.johanek.series_and_movies.repositories;

import caw24g.johanek.series_and_movies.models.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository <Serie, Long> {
}
