package tasks;

import models.DatosSigposLoombokData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import userinterfaces.LoginPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Login implements Task {

    DatosSigposLoombokData datos;

    public Login(DatosSigposLoombokData datos) {
        this.datos = datos;
    }

    public static Login withCredentials(DatosSigposLoombokData datos) {
        return Tasks.instrumented(Login.class, datos);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(LoginPage.TXT_USUARIO, isVisible()).forNoMoreThan(20).seconds(),
                Enter.theValue(datos.getUsuario()).into(LoginPage.TXT_USUARIO),
                Enter.theValue(datos.getClave()).into(LoginPage.TXT_CLAVE),
                Click.on(LoginPage.BTN_INGRESAR)
        );
    }
}
