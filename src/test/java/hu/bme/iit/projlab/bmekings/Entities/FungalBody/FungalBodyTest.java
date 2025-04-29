package hu.bme.iit.projlab.bmekings.Entities.FungalBody;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import hu.bme.iit.projlab.bmekings.TestHelper;

class HyphalTest {

    @Test
    void fungalBodyLevelUp()  throws IOException{
        TestHelper.runTest(
           "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/fungalBodyLevelUpInput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/fungalBodyLevelUpExpectedOutput.txt",
           "src/test/java/hu/bme/iit/projlab/bmekings/Entities/FungalBody/fungalBodyLevelUpOutput.txt"
        );
    }

}