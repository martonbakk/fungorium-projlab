package hu.bme.iit.projlab.bmekings.Entities.InsectTest;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.bme.iit.projlab.bmekings.Logic.IDGenerator.IDGenerator;
import hu.bme.iit.projlab.bmekings.Program.Program;
import hu.bme.iit.projlab.bmekings.TestHelper;

class InsectTest {
    @BeforeEach
    void setUp() throws Exception {
        Program.initBasePlayers();
        Program.gameLogic.startGame();
        IDGenerator.reset();
    }


    @Test
    void insectMove()  throws IOException{
        TestHelper.runTest(
           "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/insectMoveInput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/insectMoveExpectedOutput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/Entities/InsectTest/insectMoveOutput.txt"
        );
    }

    @Test
    void insectEatSpore()  throws IOException{
        TestHelper.runTest(
           "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/insectEatSporeInput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/insectEatSporeExpectedOutput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/Entities/InsectTest/insectEatSporeOutput.txt"
        );
    }

    @Test
    void divideInsect()  throws IOException{
        TestHelper.runTest(
           "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/divideInsectInput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/divideInsectExpectedOutput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/Entities/InsectTest/divideInsectOutput.txt"
        );
    }

    @Test
    void update() {
    }
}