package hu.bme.iit.projlab.bmekings.Entities.SporeTest;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.bme.iit.projlab.bmekings.Logic.IDGenerator.IDGenerator;
import hu.bme.iit.projlab.bmekings.Program.Program;
import hu.bme.iit.projlab.bmekings.TestHelper;

class SporeTest {
    @BeforeEach
    void setUp() throws Exception {
        Program.initBasePlayers();
        Program.gameLogic.startGame();
        IDGenerator.reset();
    }


    @Test
    void decreaseSporeFromTect()  throws IOException{
        TestHelper.runTest(
           "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/DecreaseSporeFromTectInput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/DecreaseSporeFromTectExpectedOutput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/Entities/SporeTest/decreaseSporeFromTectOutput.txt"
        );
    }

    @Test
    void addSporeToTecton()  throws IOException{
        TestHelper.runTest(
           "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/addSporeToTectonInput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/addSporeToTectonExpectedOutput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/Entities/SporeTest/addSporeToTectonOutput.txt"
        );
    }


    @Test
    public void stunsporeSpecialEffect() throws IOException {
        TestHelper.runTest(
            "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/stunsporeSpecialEffectInput.txt",
            "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/stunsporeSpecialEffectExpectedOutput.txt",
            "src/test/java/hu/bme/iit/projlab/bmekings/Entities/SporeTest/stunsporeSpecialEffectOutput.txt"
        );
    }

    @Test
    public void hyphalProtectorSporeSpecialEffect() throws IOException {
        TestHelper.runTest(
            "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/hyphalProtectorSporeSpecialEffectInput.txt",
            "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/hyphalProtectorSporeSpecialEffectExpectedOutput.txt",
            "src/test/java/hu/bme/iit/projlab/bmekings/Entities/SporeTest/hyphalProtectorSporeSpecialEffectOutput.txt"
        );
    }

    @Test
    public void SpeedSporeSpecialEffect() throws IOException {
        TestHelper.runTest(
            "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/SpeedSporeSpecialEffectInput.txt",
            "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/SpeedSporeSpecialEffectExpectedOutput.txt",
            "src/test/java/hu/bme/iit/projlab/bmekings/Entities/SpeedSporeSpecialEffectOutput.txt.txt"
        );
    }

    @Test
    public void hungerSporeSpecialEffect() throws IOException {
        TestHelper.runTest(
            "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/hungerSporeSpecialEffectInput.txt",
            "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/hungerSporeSpecialEffectExpectedOutput.txt",
            "src/test/java/hu/bme/iit/projlab/bmekings/Entities/SporeTest/hungerSporeSpecialEffectOutput.txt"
        );
    }

    @Test
    public void slowSporeSpecialEffect() throws IOException {
        TestHelper.runTest(
            "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/slowSporeSpecialEffectInput.txt",
            "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/slowSporeSpecialEffectExpectedOutput.txt",
            "src/test/java/hu/bme/iit/projlab/bmekings/Entities/SporeTest/slowSporeSpecialEffectOutput.txt"
        );
    }

}