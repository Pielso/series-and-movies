package caw24g.johanek.series_and_movies.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/* I've read and seen examples that uses lombok here to automate and tidy up some things,
but if I understand it correctly, lombok is used with @NoArgsConstructor and/or @AllArgsConstructor, which in our case wouldn't help much
since we use an auto-incremented primary key, right?
 */

@Entity
public class Movie {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int rating;

    // Constructor with args for creating movie objects @ back-end, for seeding and testing.

    public Movie(String name, int rating){
        this.name = name;
        this.rating = rating;
    }

    // Empty constructor that lets an empty body of movie to be created and then filled by user input from client-side.

    public Movie(){

    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
