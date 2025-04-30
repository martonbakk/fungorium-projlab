package hu.bme.iit.projlab.bmekings.BigTest;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.bme.iit.projlab.bmekings.TestHelper;
import hu.bme.iit.projlab.bmekings.Logic.IDGenerator.IDGenerator;
import hu.bme.iit.projlab.bmekings.Program.Program;

class BigTest {
    @BeforeEach
    void setUp() throws Exception {
        Program.initBasePlayers();
        Program.gameLogic.startGame();
        IDGenerator.reset();
    }

    @Test
    public void bigTest() throws IOException {
        TestHelper.runTest(
            "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/BigTestInput.txt",
            "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/BigTestExpectedOutput.txt",
            "src/test/java/hu/bme/iit/projlab/bmekings/BigTest/BigTestOutput.txt"
        );
    }
}
