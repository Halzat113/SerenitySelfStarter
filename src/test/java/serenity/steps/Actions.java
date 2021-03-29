package serenity.steps;

import net.thucydides.core.annotations.Step;

public class Actions {

    String actor;

    @Step("#actor prepare all stuff")
    public void preparedSomething(){
        System.out.println("preparing");
    }

    @Step("#actor Taking some action")
    public void takeAnAction(){
        System.out.println("Taking action");
    }

    @Step("#actor expecting a result")
    public void expectSomeResult(){
        System.out.println("expecting some result");
    }
}
