package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WinAppDriverManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(WinAppDriverManager.class);
    private static final String WINAPPDRIVER_PATH = "C:\\Program Files (x86)\\Windows Application Driver\\WinAppDriver.exe";
    private static Process process;

    private WinAppDriverManager() {
        // Utility class
    }

    public static void startWinAppDriver() {
        try {
            LOGGER.info("Iniciando WinAppDriver...");
            ProcessBuilder builder = new ProcessBuilder(WINAPPDRIVER_PATH);
            process = builder.start();
            // Espera un momento para que el servidor se inicie completamente
            Thread.sleep(3000);
            LOGGER.info("WinAppDriver iniciado correctamente.");
        } catch (IOException | InterruptedException e) {
            LOGGER.error("Error al iniciar WinAppDriver", e);
            Thread.currentThread().interrupt();
        }
    }

    public static void stopWinAppDriver() {
        if (process != null && process.isAlive()) {
            try {
                LOGGER.info("Deteniendo WinAppDriver...");
                // Termina el proceso de WinAppDriver
                Runtime.getRuntime().exec("taskkill /F /IM WinAppDriver.exe");
                process.destroy();
                process.waitFor();
                LOGGER.info("WinAppDriver detenido.");
            } catch (IOException | InterruptedException e) {
                LOGGER.error("Error al detener WinAppDriver", e);
                Thread.currentThread().interrupt();
            }
        }
    }
}