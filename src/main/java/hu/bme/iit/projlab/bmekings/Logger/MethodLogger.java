package hu.bme.iit.projlab.bmekings.Logger;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MethodLogger {
    private static final Logger LOGGER = Logger.getLogger(MethodLogger.class.getName());
    private static final String LOG_FILE_PATH = "method_calls.log";

    // KÜLÖN deklaráljuk
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static {
        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE_PATH, true); // true: append
            fileHandler.setFormatter(new SimpleFormatter() {
                @Override
                public String format(LogRecord record) {
                    String message = record.getMessage();
                    String timestamp = LocalDateTime.now().format(FORMATTER);
                    String[] parts = message.split(":", 3);
                    if (parts.length == 3) {
                        return String.format(
                            "[%s] %s (%s): %s()%n",
                            timestamp,
                            parts[0], // ClassLabel
                            parts[1], // playerId
                            parts[2]  // methodName
                        );
                    }
                    return String.format("[%s] %s%n", timestamp, message);
                }
            });
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.INFO);
            LOGGER.setUseParentHandlers(false); // ne írjon konzolra
        } catch (IOException e) {
            System.err.println("Hiba a naplófájl inicializálása során: " + e.getMessage());
        }
    }

    public static void logMethodCall(String classLabel, String playerId, String methodName) {
        String message = String.format("%s:%s:%s", classLabel, playerId, methodName);
        LOGGER.log(Level.INFO, message);
    }
}
