package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.HashMap;
import java.util.Map;

public class WebView2Connector {
    public static WebDriver connectToWebView2() {
        System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");

        EdgeOptions options = new EdgeOptions();

        // Configurar debuggerAddress para conectar al WebView2
        Map<String, Object> edgeOptions = new HashMap<>();
        edgeOptions.put("debuggerAddress", "localhost:9222");

        options.setCapability("ms:edgeOptions", edgeOptions);


        return new EdgeDriver(options);
    }
}
