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
          "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/MapSplitTectonInput.txt",
          "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/MapSplitTectonExpectedOutput.txt",
          "src/test/java/hu/bme/iit/projlab/bmekings/MapTest/MapSplitTectonOutput.txt"
      );
    }

    
    @Test
    public void disconnectTectons()  throws IOException{
        TestHelper.runTest(
          "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/disconnectTectonsInput.txt",
          "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/disconnectTectonsExpectedOutput.txt",
          "src/test/java/hu/bme/iit/projlab/bmekings/MapTest/disconnectTectonsOutput.txt"
      );
    }

    @Test
    public void weakTectonSpecialEffect()  throws IOException{
        TestHelper.runTest(
          "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/weakTectonSpecialEffectInput.txt",
          "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/weakTectonSpecialEffectOutput.txt",
          "src/test/java/hu/bme/iit/projlab/bmekings/MapTest/weakTectonSpecialEffectOutput.txt"
      );
    }

     @Test
    public void toxicTectonSpecialEffect()  throws IOException{
        TestHelper.runTest(
          "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/ToxicTectonSpecialEffectInput.txt",
          "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/ToxicTectonSpecialEffectExpectedOutput.txt",
          "src/test/java/hu/bme/iit/projlab/bmekings/MapTest/ToxicTectonSpecialEffectOutput.txt"
      );
    }


    @Test
    public void noFungusTectonSpecialEffect()  throws IOException{
        TestHelper.runTest(
          "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/noFungusTectonSpecialEffectInput.txt",
          "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/noFungusTectonSpecialEffectOutput.txt",
          "src/test/java/hu/bme/iit/projlab/bmekings/MapTest/noFungusTectonSpecialEffectOutput.txt"
      );
    }
    
    public void hyphalPreserverTectonSpecialEffect()  throws IOException{
        TestHelper.runTest(
          "src/test/java/hu/bme/iit/projlab/bmekings/TestInputs/hyphalPreserverTectonSpecialEffectInput.txt",
          "src/test/java/hu/bme/iit/projlab/bmekings/TestExceptedOutputs/hyphalPreserverTectonSpecialEffectExpectedOutput.txt",
          "src/test/java/hu/bme/iit/projlab/bmekings/MapTest/hyphalPreserverTectonSpecialEffectOutput.txt"
      );
    }
    
}