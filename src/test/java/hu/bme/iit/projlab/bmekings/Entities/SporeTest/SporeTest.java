package hu.bme.iit.projlab.bmekings.Entities.SporeTest;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import hu.bme.iit.projlab.bmekings.TestHelper;
import hu.bme.iit.projlab.bmekings.TestHelper;

class SporeTest {
 
    @Test
    void decreaseSporeFromTect()  throws IOException{
        TestHelper.runTest(
           "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/DecreaseSporeFromTectInput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/DecreaseSporeFromTectOutput.txt",
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
            "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/stunsporeSpecialEffectOutput.txt",
            "src/test/java/hu/bme/iit/projlab/bmekings/Entities/SporeTest/stunsporeSpecialEffectOutput.txt"
        );
    }



}