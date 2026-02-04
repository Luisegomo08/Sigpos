package utils;

import io.appium.java_client.windows.WindowsDriver;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class WinAppDriverProvider implements DriverSource {

    @Override
    public WebDriver newDriver() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", "C:\\App-1.9.3\\WebFlowers.Central.App.exe");
            capabilities.setCapability("platformName", "Windows");
            capabilities.setCapability("deviceName", "WindowsPC");

            return new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo iniciar WinAppDriver", e);
        }
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }
}