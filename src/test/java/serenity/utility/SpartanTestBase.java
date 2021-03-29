package serenity.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class SpartanTestBase {
    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "http://54.144.193.41:7000";
        RestAssured.basePath="/api";
    }

    @AfterAll
    public static void teardown(){
        RestAssured.reset();
    }
}
