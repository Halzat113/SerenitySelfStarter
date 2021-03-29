package serenity.spartan;

import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import serenity.utility.SpartanTestBase;

@SerenityTest
public class SpartanSearch_Test extends SpartanTestBase {

    @DisplayName("Authenticated user should be able to search")
    @Test
    public void testSearch(){
        given()
                .queryParam("nameContains","a")
                .queryParam("gender","Male")
                .auth().basic("admin","admin").
        when()
                .get("/spartans/search");

        Ensure.that("Request was successful",response->response.statusCode(200))
        .andThat("We got json result",response->response.contentType(ContentType.JSON));

        Ensure.that("Make sure every item contains a",
                response->response.body("content.name",
                        everyItem(anyOf(containsString("a"),
                                containsString("A")))));

        Ensure.that("Make sure every item contains a",
                response->response.body("content.gender",everyItem(is("Male"))));
    }
}
