package main.java.hu.bme.iit.projlab.bmekings.Entities.Spore;

import main.java.hu.bme.iit.projlab.bmekings.Entities.Entity;
import main.java.hu.bme.iit.projlab.bmekings.Interface.SporeInterface.SporeInterface;

/**
 * A Spore absztrakt osztály a különböző típusú spórák közös tulajdonságait és viselkedését definiálja.
 * A spórák a gombatestek által termelt egységek, amelyek új gombafonalak és gombatestek fejlődését segítik,
 * valamint különböző hatásokat gyakorolhatnak a rovarokra. Az Entity osztályból származik, és implementálja
 * a SporeInterface interfészt.
 */
public abstract class Spore extends Entity implements SporeInterface {

    public Spore() {
        
    }
}