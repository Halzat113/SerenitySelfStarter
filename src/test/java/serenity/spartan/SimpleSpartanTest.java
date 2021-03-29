package serenity.spartan;

import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import static net.serenitybdd.rest.SerenityRest.*;

import net.serenitybdd.rest.Ensure;
import org.junit.jupiter.api.*;
import java.util.concurrent.TimeUnit;

@SerenityTest
public class SimpleSpartanTest {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "http://54.144.193.41:7000";
        RestAssured.basePath="/api";
    }

    @AfterAll
    public static void teardown(){
        reset();
    }

    @DisplayName("Testing GET /api/hello Endpoint")
    @Test
    public void testingHelloEndpoint(){

        given()
                .auth().basic("admin","admin").
        when()
                .get("/hello").
       then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.TEXT)
                .body(is("Hello from Sparta"));
        Ensure.that("Success response was recieved",
                thenResponse->thenResponse.statusCode(200))
                .andThat("I got text response",
                        response->response.contentType(ContentType.TEXT))
                .andThat("I got Hello from sparta",
                        vResponse->vResponse.body(is("Hello from Sparta")))
                .andThat("I got my response within 2 seconds",
                        vResponse-> vResponse.time(lessThan(2L), TimeUnit.SECONDS));
    }
}
