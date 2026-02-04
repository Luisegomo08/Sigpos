package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import models.DatosSigposLoombokData;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import questions.ValidarInicioSesionSigpos;
import tasks.Login;

public class LoginStepDefinitions {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Dado("El usuario está en la página de inicio de sesión")
    public void elUsuarioEstaEnLaPaginaDeInicioDeSesion() {
        OnStage.theActorCalled("Usuario");
    }

    @Cuando("El usuario ingresa credenciales válidas")
    public void elUsuarioIngresaCredencialesValidas(DataTable table) {
        //OnStage.theActorInTheSpotlight().attemptsTo(Login.withCredentials(DatosSigposLoombokData.setData(table).get(0)));
        OnStage.theActorInTheSpotlight().attemptsTo(Login.withCredentials(new DatosSigposLoombokData().setData(table).get(0)));
    }

    @Entonces("El usuario debería ser redirigido a la página de inicio")
    public void elUsuarioDeberiaSerRedirigidoALaPaginaDeInicio() {
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(ValidarInicioSesionSigpos.isVisible()));
    }
}
