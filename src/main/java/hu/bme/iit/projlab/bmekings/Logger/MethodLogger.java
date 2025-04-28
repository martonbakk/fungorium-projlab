package hu.bme.iit.projlab.bmekings.Logger;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import hu.bme.iit.projlab.bmekings.Player.Player;

public class MethodLogger {
    private static final Logger LOGGER = Logger.getLogger(MethodLogger.class.getName());
    private static final String LOG_FILE_PATH = "method_calls.log";

    static {
        try {
            // Fájlkezelő beállítása
            FileHandler fileHandler = new FileHandler(LOG_FILE_PATH, true); // true: hozzáfűzés
            fileHandler.setFormatter(new SimpleFormatter() {
                private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                @Override
                public String format(LogRecord record) {
                    String message = record.getMessage();
                    // A message formátuma: "ClassLabel:playerId:methodName:output"
                    String[] parts = message.split(":", 4);
                    if (parts.length == 4) {
                        return String.format(
                            "[%s] %s (%s): %s() => %s%n",
                            LocalDateTime.now().format(formatter),
                            parts[0], // ClassLabel
                            parts[1], // playerId
                            parts[2], // methodName
                            parts[3]  // output
                        );
                    }
                    return String.format(
                        "[%s] %s%n",
                        LocalDateTime.now().format(formatter),
                        message
                    );
                }
            });
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.INFO);
            // Konzol naplózás kikapcsolása
            LOGGER.setUseParentHandlers(false);
        } catch (IOException e) {
            System.err.println("Hiba a naplófájl inicializálása során: " + e.getMessage());
        }
    }

    /**
     * Naplózza egy metódus meghívását és annak kimenetét egy objektum alapján.
     * 
     * @param object Az objektum, amelynek osztályán van ClassLabel annotáció
     * @param methodName A hívott metódus neve
     * @param output A metódus kimenete
     */
    public static void logMethodCall(Object object, String methodName, String output) {
        // Annotáció kiolvasása
        ClassLabel label = object.getClass().getAnnotation(ClassLabel.class);
        String classLabel = (label != null && !label.value().isEmpty()) ? label.value() : object.getClass().getSimpleName();

        // PlayerId kiolvasása reflection-nel vagy getterrel
        String playerId = "unknown";
        if (object instanceof Player) {
            playerId = ((Player) object).getPlayerId();
        }

        String message = String.format("%s:%s:%s:%s", classLabel, playerId, methodName, output);
        LOGGER.log(Level.INFO, message);
    }

    /**
     * Naplózza az aktuális metódus meghívását.
     * 
     * @param object Az objektum, amelynek osztályán van ClassLabel annotáció
     * @param output A metódus kimenete
     */
    public static void logCurrentMethod(Object object, String output) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length >= 2) {
            StackTraceElement caller = stackTrace[2]; // stackTrace[1] a logCurrentMethod, stackTrace[2] a hívó
            logMethodCall(object, caller.getMethodName(), output);
        }
    }
}