package userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class HomePage {

    public static final Target METRICAS_TEXT = Target.the("texto de Métricas en la página de inicio")
            .locatedBy("//*[contains(text(),'Métricas')]");
}
