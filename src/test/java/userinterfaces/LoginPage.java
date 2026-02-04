package userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {

    // Usamos By.id, que en el contexto de Appium/WinAppDriver,
    // a menudo se resuelve como AccessibilityId.
    // Esto evita que Serenity entre en modo 'mobile' incorrectamente.
    public static final Target TXT_USUARIO = Target.the("campo de usuario")
            .located(By.xpath("//*[@AutomationId='UserName-id']"));

    public static final Target TXT_CLAVE = Target.the("campo de contraseña")
            .located(By.xpath("//*[@AutomationId='Password-id']"));

    public static final Target BTN_INGRESAR = Target.the("botón de inicio de sesión")
            .located(By.xpath("//*[@AutomationId='Login-button-id']"));
}

