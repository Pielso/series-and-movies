package caw24g.johanek.series_and_movies.stepdefinitions;

import caw24g.johanek.series_and_movies.models.Serie;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CucumberTestSteps {

    Serie cucumberIsBestPickled;

    // Save serie.

    @Given("The user is on the correct page")
    public void the_user_is_on_the_correct_page() {
        // Nothing to test
        Assertions.assertTrue(true);
    }

    @When("The user have filled in the serie title {string} with a rating of {int} and clicks the save button")
    public void the_user_have_filled_in_the_serie_title_with_a_rating_of_and_clicks_the_save_button(String string, Integer int1) {
        cucumberIsBestPickled = new Serie(string, int1);
        Assertions.assertTrue(true);
    }

    @Then("The serie is created and has the name {string} and the rating {int}")
    public void the_serie_is_created(String string, int int1) {
        Assertions.assertEquals(string, cucumberIsBestPickled.getName());
        Assertions.assertEquals(int1, cucumberIsBestPickled.getRating());
    }

    // Update name

    @Given("A serie with the name {string} exists")
    public void a_serie_with_the_name_exists(String string) {
        cucumberIsBestPickled = new Serie("South Park", 10);
        Assertions.assertEquals(string, cucumberIsBestPickled.getName());
    }
    @When("User changes the name to {string}")
    public void user_changes_the_name_to(String string) {
        cucumberIsBestPickled.setName(string);
    }
    @Then("The series name is now {string}")
    public void the_series_name_is_now(String string) {
        Assertions.assertEquals(string, cucumberIsBestPickled.getName());
    }

    // Update rating

    @Given("A serie exists")
    public void a_serie_with_the_name_exists() {
        cucumberIsBestPickled = new Serie("Family Guy", 10);
    }

    @When("User changes the rating to {int}")
    public void user_changes_the_rating_to(Integer int1) {
        cucumberIsBestPickled.setRating(int1);
    }

    @Then("The new rating is {int}")
    public void the_new_rating_is(Integer int1) {
        Assertions.assertEquals(int1, cucumberIsBestPickled.getRating());
    }
}
