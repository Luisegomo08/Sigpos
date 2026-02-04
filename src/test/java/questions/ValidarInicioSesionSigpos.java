package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;
import userinterfaces.HomePage;

public class ValidarInicioSesionSigpos implements Question<Boolean> {

    public static Question<Boolean> isVisible() {
        return new ValidarInicioSesionSigpos();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return Visibility.of(HomePage.METRICAS_TEXT).answeredBy(actor);
    }
}
