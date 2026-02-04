package utils;

import io.appium.java_client.windows.WindowsDriver;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class WindowsDriverProvider implements DriverSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(WindowsDriverProvider.class);
    private static final AtomicBoolean isShutdownHookRegistered = new AtomicBoolean(false);
    private static WindowsDriver driver;

    @Override
    public WebDriver newDriver() {
        if (driver == null) {
            try {
                LOGGER.info("=== INICIANDO CONFIGURACIÓN DE WINAPPDRIVER ===");
                WinAppDriverManager.startWinAppDriver();

                // Registrar el shutdown hook para asegurar que WinAppDriver se detenga al final
                if (isShutdownHookRegistered.compareAndSet(false, true)) {
                    Runtime.getRuntime().addShutdownHook(new Thread(WinAppDriverManager::stopWinAppDriver));
                }

                String appPath = System.getenv("APP_PATH");
                if (appPath == null || appPath.isBlank()) {
                    appPath = "C:\\App-1.9.3\\WebFlowers.Central.App.exe";
                    LOGGER.warn("No se encontró la variable de entorno APP_PATH; usando ruta por defecto: '{}'", appPath);
                } else {
                    LOGGER.info("Usando APP_PATH desde entorno: '{}'", appPath);
                }

                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("app", appPath);
                capabilities.setCapability("deviceName", "WindowsPC");
                capabilities.setCapability("platformName", "Windows");
                capabilities.setCapability("automationName", "Windows"); // <-- Agregado
                capabilities.setCapability("ms:waitForAppLaunch", "20"); // Espera a que la app inicie

                driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                LOGGER.info("✅ Configuración de WinAppDriver completada.");

            } catch (Exception e) {
                LOGGER.error("❌ Error durante la configuración de WinAppDriver.", e);
                throw new RuntimeException("Error al crear una nueva instancia del driver", e);
            }
        }
        return driver;
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }

    // El método shutdown/shutDown no es parte de la interfaz DriverSource en Serenity 3.x.
    // La limpieza de WinAppDriver.exe se maneja ahora a través de un JVM Shutdown Hook.
    // Serenity se encarga de llamar a driver.quit() automáticamente.
}

