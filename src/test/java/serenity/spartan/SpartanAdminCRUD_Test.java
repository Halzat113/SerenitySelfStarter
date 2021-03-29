package serenity.spartan;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.*;
import serenity.steps.Spartan;
import serenity.utility.SpartanUtil;

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.Matchers.*;

@SerenityTest
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class SpartanAdminCRUD_Test {

    @Steps
    Spartan sp;

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "http://54.144.193.41:7000";
        RestAssured.basePath="/api";
    }

    @AfterAll
    public static void teardown(){
        RestAssured.reset();
    }

    @DisplayName("1.Admin user should be able to Add Spartan")
    @Test
    public void testAdd1Data(){

        sp.prepare();
        sp.action();
//        Map<String,Object> payload = SpartanUtil.getRandomSpartanRequestPayload();
//
//        given()
//                .auth().basic("admin","admin")
//                .log().body()
//                .contentType(ContentType.JSON)
//                .body(payload).
//        when()
//                .post("/spartans");
//
//        Ensure.that("Request was successful",
//                response->response.statusCode(201))
//                .andThat("We got json format result",
//                        thenResponse->thenResponse.contentType(ContentType.JSON))
//                .andThat("Success message is A Spartan is born!",
//                        thenResponse->thenResponse.body("success",is("A Spartan is Born!")));
//
//        Ensure.that("The data we provided added correctly",
//                response->response.body("data.name",is(payload.get("name")))
//                        .body("data.gender",is(payload.get("gender")))
//                        .body("data.phone",is(payload.get("phone"))))
//                .andThat("New ID has been generated and not null",
//                        vRes->vRes.body("data.id",is(notNullValue())));
//
//        //how do we extract information after sending request?
//        //lastResponse() method is coming SerenityRest class
//        lastResponse().prettyPeek();
//        System.out.println("lastResponse().jsonPath().getList(\"data.id\") = "
//                + lastResponse().jsonPath().getInt("data.id"));
    }

    @DisplayName("2.Admin should be able to read single data")
    @Test
    public void getOneData(){

       int newId = lastResponse().jsonPath().getInt("data.id");
        System.out.println("newId = " + newId);

        given().auth().basic("admin","admin").
        when().get("/spartans/{id}",newId);

        Ensure.that("We can access newly generated data",
                thenResponse->thenResponse.statusCode(200));




    }

    @DisplayName("3.Admin should be able to delete single data")
    @Test
    public void testDeleteOneData(){

        int id = lastResponse().jsonPath().getInt("id");

        given()
                .auth().basic("admin","admin").
        when()
                .delete("spartans/{id}",id);

        Ensure.that("Request is successful",response->response.statusCode(204));

        given()
                .auth().basic("admin","admin").
                when()
                .get("spartans/{id}",id);

        Ensure.that("Data is deleted",response->response.statusCode(404));
    }
}
