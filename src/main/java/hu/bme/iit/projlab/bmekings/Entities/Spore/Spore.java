package main.java.hu.bme.iit.projlab.bmekings.Entities.Spore;

import main.java.hu.bme.iit.projlab.bmekings.Entities.Entity;
import main.java.hu.bme.iit.projlab.bmekings.Interface.SporeInterface.SporeInterface;
import main.java.hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;

/**
 * A Spore absztrakt osztály a különböző típusú spórák közös tulajdonságait és viselkedését definiálja.
 * A spórák a gombatestek által termelt egységek, amelyek új gombafonalak és gombatestek fejlődését segítik,
 * valamint különböző hatásokat gyakorolhatnak a rovarokra. Az Entity osztályból származik, és implementálja
 * a SporeInterface interfészt.
 */
public abstract class Spore extends Entity implements SporeInterface {

    private int nutritionValue;

    public Spore(){
        super();
        this.nutritionValue=0;
    }

    public Spore(int n, String id, Tecton baseLocation) {
        super(id, baseLocation);
        nutritionValue = n;
    }
}