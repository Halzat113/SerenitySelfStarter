package serenity;

import net.serenitybdd.junit5.SerenityTest;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import serenity.steps.Actions;

@SerenityTest
@Tag("math")
public class MathTest {


    @Steps
    Actions actions;

    @Test
    public void test(){

        //user preparedSomething
        //takeAnAction
        //expectSomething

        //GIVEN
        actions.preparedSomething();
        //WHEN
        actions.takeAnAction();
        //THEN
        actions.expectSomeResult();

    }

}
