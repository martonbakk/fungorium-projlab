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
           "src/test/java/hu/bme/it/projlab/bmekings/Entities/SporeTest/decreaseSporeFromTectOutput.txt"
        );
    }
}