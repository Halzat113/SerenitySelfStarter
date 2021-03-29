package serenity.steps;

import io.restassured.http.ContentType;
import static net.serenitybdd.rest.SerenityRest.*;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import serenity.utility.SpartanUtil;

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.given;

public class Spartan {

    Map<String,Object> payload ;

    @Step
    public void prepare(){
        payload = SpartanUtil.getRandomSpartanRequestPayload();
        given()
                .auth().basic("admin","admin")
                .log().body()
                .contentType(ContentType.JSON)
                .body(payload);
    }

    @Step
    public void action() {
        when()
                .post("/spartans");
        lastResponse().prettyPeek();

    }



}
