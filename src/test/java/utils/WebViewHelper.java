package utils;

import io.appium.java_client.windows.WindowsDriver;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebElement;

public class WebViewHelper {

    private WindowsDriver driver;

    public WebViewHelper(WindowsDriver driver) {
        this.driver = driver;
    }

    public WebElement getWebView() {
        return driver.findElement(By.className("TopContainerOverlayView"));
    }

    public void executeJS(String script) {
        WebElement webView = getWebView();
        driver.executeScript(script, webView);
    }

    /**
     * Ejecuta JavaScript en el WebView2 para setear los valores de login usando los Targets de LoginPage
     */
    public void loginWithJS(Target userField, Target passwordField, Target loginButton, String usuario, String clave) {

        // JS dinámico usando concatenación (compatible con Java 8)
        String js = "function setLogin() {"
                + "const user = document.getElementById('" + userField.getCssOrXPathSelector() + "');"
                + "const pass = document.getElementById('" + passwordField.getCssOrXPathSelector() + "');"
                + "const btn  = document.getElementById('" + loginButton.getCssOrXPathSelector() + "');"
                + "if(user && pass && btn){"
                + "user.value = '" + usuario + "';"
                + "pass.value = '" + clave + "';"
                + "btn.click();"
                + "} else {"
                + "setTimeout(setLogin, 100);"
                + "}"
                + "}"
                + "setLogin();";
    }
}
