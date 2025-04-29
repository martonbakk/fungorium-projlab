package hu.bme.iit.projlab.bmekings;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hu.bme.iit.projlab.bmekings.Program.Program;

public class TestHelper {

    public static void runTest(String inputFilePath, String expectedOutputFilePath, String outputFilePath) throws IOException {
        // Eredeti System.out és System.in mentése
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;

        try {
            // Input fájl beolvasása
            String input = Files.readString(Paths.get(inputFilePath));

            // Várt kimenet beolvasása
            String expectedOutput = Files.readString(Paths.get(expectedOutputFilePath));

            // Kimenet gyűjtése memóriába
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream combinedOut = new PrintStream(new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    originalOut.write(b); // Konzolra írás
                    outputStream.write(b); // Memóriába gyűjtés
                }

                @Override
                public void flush() throws IOException {
                    originalOut.flush();
                    outputStream.flush();
                }

                @Override
                public void close() throws IOException {
                    originalOut.close();
                    outputStream.close();
                }
            });

            // System.out átirányítása a kombinált kimenetre
            System.setOut(combinedOut);

            // Input szimulálása
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            // Program inicializálása
            Program.initBasePlayers();
            Program.gameLogic.startGame();

            // Parancsok feldolgozása soronként
            String[] commands = input.split("\n");
            for (String command : commands) {
                command = command.trim();
                if (!command.isEmpty()) {
                    Program.consoleActions(command); // Parancs futtatása
                }
            }

            

            // Kimenet kiírása fájlba
            String actualOutput = outputStream.toString();
            Files.writeString(Paths.get(outputFilePath), actualOutput);

            // Kimenet ellenőrzése
            assertEquals(expectedOutput.trim(), actualOutput.trim(), "A kimenet nem egyezik az elvárt kimenettel!");
            Program.gameLogic=null;

        } finally {
            // Eredeti System.out és System.in visszaállítása
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
    }
}