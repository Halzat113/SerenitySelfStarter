package serenity.zipcode_app;

import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;


public class ZipToCityEndpointTest {

    //api.zippopotam.us/us/90210

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "https://api.zippopotam.us";
    }

    @AfterAll
    public static void teardown(){
        clear();
    }

    @DisplayName("Testing 1 zip code and get the result")
    @Test
    public void test1ZipCode(){

        given()
                .pathParam("country","us")
                .pathParam("zipcode",22030).
        when()
                .get("/{country}/{zipcode}").
        then().log().body()
        .statusCode(200)
        .body("'post code'",is("22030"))
        .body("places[0].'place name'",is("Fairfax"));
    }

    @DisplayName("Testing multiple zipcodes and get the result")
    @ParameterizedTest(name = "Iter number{index}: {arguments}")
    @ValueSource(strings = {"22030", "22031", "22032", "22034", "22035"})
    public void testZipCodes(String zip){

        System.out.println(zip);

        given()
                .pathParam("country","us")
                .pathParam("zipcode",zip).
        when()
                .get("/{country}/{zipcode}");
    }

    @ParameterizedTest(name = "Iter number {index} Country is {0}")
    @CsvFileSource(resources = "/country_zip.csv",numLinesToSkip = 1)
    public void testCountryZip(String country,int zip){
        given()
                .pathParam("country",country)
                .pathParam("zipcode",zip).
                when()
                .get("/{country}/{zipcode}");
    }


}
