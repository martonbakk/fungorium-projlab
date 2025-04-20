package hu.bme.iit.projlab.bmekings.Logic.GameLogic;

import java.util.ArrayList;

import hu.bme.iit.projlab.bmekings.Interface.Listener.Listener;
import hu.bme.iit.projlab.bmekings.Entities.Entity;
import hu.bme.iit.projlab.bmekings.Map.Map;


/**
 * A GameLogic osztály felelős a játék alapvető elemeinek nyilvántartásáért és a játékbeli történések vezérléséért.
 * Ez az osztály indítja el a játékot, kezeli a játékidő telését, és koordinálja a játék elemeit.
 * Kompozíciós kapcsolatban áll a Map osztállyal (egy az egyhez) és az Entity osztállyal (egy a többhöz).
 */
public class GameLogic {

    /** A játékban szereplő entitások listája, például gombatestek, spórák vagy rovarok. */
    private ArrayList<Entity> entities = new ArrayList<>();

    /** A játékban szereplő Listener interfészt implementáló objektumok listája, amelyek frissítéseket kapnak. */
    private ArrayList<Listener> listeners = new ArrayList<>();

    private Map map;

    public GameLogic() {
        
    }

    public GameLogic(ArrayList<Entity> entities, ArrayList<Listener> listeners, Map map) {
        this.entities=entities;
        this.listeners=listeners;
        this.map = map;
    }
    
    public void startGame() {
        
    }
    
    public void timeTick() {

    }

   
    public void addListener(Listener l) {

    }
}