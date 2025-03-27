package main.java.hu.bme.iit.projlab.bmekings.Entities.Fungal;

import java.util.ArrayList;
import main.java.hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import main.java.hu.bme.iit.projlab.bmekings.Entities.Entity;
import main.java.hu.bme.iit.projlab.bmekings.Interface.SporeInterface.SporeInterface;

/**
 * A FungalBody osztály a gombatestek adatait tárolja, és a fejlődéssel valamint spóraszórással kapcsolatos
 * műveleteket kezeli. A gombatestek a játék kulcselemei, amelyek spórákat termelnek, és terjesztik a gombafonalakat.
 * Az Entity absztrakt osztályból származik, így örökli annak attribútumait és metódusait.
 */
public class FungalBody extends Entity {

    /** A gombatest által kilőtt spórák listája. */
    private ArrayList<SporeInterface> spores = new ArrayList<>();
    private int currLevel;
    private int shotSporesNum;
    private TypeCharacteristics characteristics;
    
    public FungalBody() {
        super();  

        this.characteristics = new TypeCharacteristics(0, 0, 0, 0);
        this.currLevel = 0;
        this.shotSporesNum = 0;
    }

    public FungalBody(int currLevel, int shotSporesNum, TypeCharacteristics characteristics, String id, Tecton baseLocation){
        super(id, baseLocation);
        
        this.characteristics = characteristics;
        this.currLevel = shotSporesNum;
        this.shotSporesNum = currLevel;
    }

    
    public void shootSpore() {
        
    }

    
    public void levelUp() {
        
    }

    
    public void keepHyphalAlive(ArrayList<Hyphal> hyphalList) {
        
    }

    
    @Override
    public void update() {
        
    }

    private class TypeCharacteristics{
        int shoointRange;
        int sporeProductionIntensity;
        int startingHyphalNum;
        int sporeCapacity;

        public TypeCharacteristics(int shoointRange, int sporeProductionIntensity, int startingHyphalNum, int sporeCapacity) {
            this.shoointRange = shoointRange;
            this.sporeProductionIntensity = sporeProductionIntensity;
            this.startingHyphalNum = startingHyphalNum;
            this.sporeCapacity = sporeCapacity;
        }
    }
}

