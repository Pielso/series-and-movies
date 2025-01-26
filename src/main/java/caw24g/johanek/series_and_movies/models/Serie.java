package caw24g.johanek.series_and_movies.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

/* I've read and seen examples that uses lombok here to automate and tidy up some things,
but if I understand it correctly, lombok is used with @NoArgsConstructor and/or @AllArgsConstructor, which in our case wouldn't help much
since we use an auto-incremented primary key, right?
 */

@Entity
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "You need to provide a title for the serie")
    private String name;

    @NotNull(message = "Don't put in blank fields. Rate the serie.")
    @Range(min = 1, max = 10, message = "The rating must be between 1 - 10")
    private int rating;

    // Constructor with args for creating serie objects @ back-end, for seeding and testing.
    public Serie(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    // Empty constructor that lets an empty body of serie to be created and then filled by user input from client-side.
    public Serie() {
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