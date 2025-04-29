package hu.bme.iit.projlab.bmekings.Entities.HyphalTest;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.bme.iit.projlab.bmekings.Logic.IDGenerator.IDGenerator;
import hu.bme.iit.projlab.bmekings.Program.Program;
import hu.bme.iit.projlab.bmekings.TestHelper;

class HyphalTest {
    @BeforeEach
    void setUp() throws Exception {
        Program.initBasePlayers();
        Program.gameLogic.startGame();
        IDGenerator.reset();
    }
    
    @Test //build fail
    void connectTectonsWithHyphal()  throws IOException{
        TestHelper.runTest(
           "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/connectTectonsWithHyphalInput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/connectTectonsWithHyphalExpectedOutput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/Entities/HyphalTest/connectTectonsWithHyphalOutput.txt"
        );
    }

    @Test //build fail
    void hyphalAging()  throws IOException{
        TestHelper.runTest(
           "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/hyphalAgingInput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/hyphalAgingExpectedOutput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/Entities/HyphalTest/hyphalAgingOutput.txt"
        );
    }


    @Test
    void eatInsect() throws IOException{
        TestHelper.runTest(
           "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/HyphalEatInsectInput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/HyphalEatInsectExpectedOutput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/Entities/HyphalTest/HyphalEatInsectOutput.txt"
        );
    }

}