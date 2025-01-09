package caw24g.johanek.series_and_movies.repositories;

import caw24g.johanek.series_and_movies.models.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* Not much to describe here. The class (interface) inherits the methods from JPA,
and handles Movie, by the keys of Long. I have wondered why Long (object) is used in the back-end, like for the keys
and as properties, when we use the primitive type in interaction with the client?
Maybe it doesn't matter, or is it that DB-Keys always should be the object-type?
 */

@Repository
public interface SerieRepository extends JpaRepository <Serie, Long> {

    // Added this one for the method deleteByName.

    Serie findByName(String name);
}

