package Entities.Fungal;
import java.util.ArrayList;
import java.util.Scanner;

import SporeInterface;

/**
 * A FungalBody osztály a gombatestek adatait tárolja, és a fejlődéssel valamint spóraszórással kapcsolatos
 * műveleteket kezeli. A gombatestek a játék kulcselemei, amelyek spórákat termelnek, és terjesztik a gombafonalakat.
 * Az Entity absztrakt osztályból származik, így örökli annak attribútumait és metódusait.
 */
public class FungalBody extends Entity {

    /** A gombatest által kilőtt spórák listája. */
    ArrayList<SporeInterface> spores = new ArrayList<>();

    
    public FungalBody() {
        super();
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
}