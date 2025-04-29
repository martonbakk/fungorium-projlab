package hu.bme.iit.projlab.bmekings.Entities.FungalBody;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.bme.iit.projlab.bmekings.Program.Program;
import hu.bme.iit.projlab.bmekings.TestHelper;
import hu.bme.iit.projlab.bmekings.Logic.IDGenerator.IDGenerator;


class FungalBodyTest {
    @BeforeEach
    void setUp() throws Exception {
        Program.initBasePlayers();
        Program.gameLogic.startGame();
        IDGenerator.reset();
    }


    @Test 
    void fungalBodyLevelUp()  throws IOException{
        TestHelper.runTest(
           "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/fungalBodyLevelUpInput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/fungalBodyLevelUpExpectedOutput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/Entities/FungalBody/fungalBodyLevelUpOutput.txt"
        );
    }

    @Test
    void fungalBodyKeepHyphalAlive()  throws IOException{
        TestHelper.runTest(
           "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/fungalBodyKeepHyphalAliveInput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/fungalBodyKeepHyphalAliveExpectedOutput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/Entities/FungalBody/fungalBodyKeepHyphalAliveOutput.txt"
        );
    }

    @Test
    void fungalBodyShootSpore()  throws IOException{
        TestHelper.runTest(
           "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/FungalBodyShootSporeInput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/FungalBodyShootSporeExpectedOutput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/Entities/FungalBody/FungalBodyShootSporeOutput.txt"
        );
    }


}