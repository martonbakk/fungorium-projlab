package  hu.bme.iit.projlab.bmekings.MapTest;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import hu.bme.iit.projlab.bmekings.TestHelper;

class MapTest {
    // A MapTest osztály a térkép generálásának és frissítésének tesztelésére szolgál.
    @Test
    public void generateMap() throws IOException {
        TestHelper.runTest(
            "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/GeneratemapInput.txt",
            "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/GenerateMapExpectedOutput.txt",
            "src/test/java/hu/bme/iit/projlab/bmekings/MapTest/GenerateMapOutput.txt"
        );
    }

    @Test  
    public void splitTecton()  throws IOException{
        TestHelper.runTest(
          "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/SplitTectonInput.txt",
          "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/SplitTectonExpectedOutput.txt",
          "src/test/java/hu/bme/iit/projlab/bmekings/MapTests/SplitTectonOutput.txt"
      );
    }

    @Test
    public void updateTectons()  throws IOException{
        TestHelper.runTest(
          "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/GeneratemapInput.txt",
          "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/GenerateMapExpectedOutput.txt",
          "src/test/java/hu/bme/iit/projlab/bmekings/UpdateTectonsOutput.txt"
      );
    }
}