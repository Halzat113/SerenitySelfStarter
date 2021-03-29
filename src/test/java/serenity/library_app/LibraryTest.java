package serenity.library_app;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import static net.serenitybdd.rest.SerenityRest.*;

import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import serenity.utility.ConfigReader;


@SerenityTest
public class LibraryTest {

    @Steps
    ConfigReader conf;


//    @BeforeAll
//    public static void setup() {
//        RestAssured.baseURI = "http://library1.cybertekschool.com";
//        RestAssured.basePath = "/rest/v1";
//    }

    @BeforeEach
    public void setupEach(){
        RestAssured.baseURI=conf.getProperty("base.url");
        RestAssured.basePath=conf.getProperty("base.path");
    }

    @AfterAll
    public static void teardown() {
        RestAssured.reset();
        clear();
    }

    @Test
    public void testLogin(){
        given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email",conf.getProperty("librarian.username"))
                .formParam("password",conf.getProperty("librarian.password")).
        when()
                .post("/login");
    }
    @Test
    public void getToken(){

//        given()  .log().all()
//                .contentType(ContentType.URLENC)
//                .formParam("email", "librarian69@library")
//                .formParam("password", "KNPXrm3S").
//        when()
//                .post("/login");

//        ConfigReader configReader = new ConfigReader();
//        System.out.println("configReader.get(\"base.url\") = " + configReader.get("base.url"));

        System.out.println(conf.getProperty("base.url"));

    }


}
