package hu.bme.iit.projlab.bmekings.Logic.GameLogic;

import java.util.ArrayList;

import hu.bme.iit.projlab.bmekings.Interface.Listener.Listener;
import hu.bme.iit.projlab.bmekings.Logic.Ticker.Ticker;
import hu.bme.iit.projlab.bmekings.Entities.Entity;
import hu.bme.iit.projlab.bmekings.Map.Map;
import hu.bme.iit.projlab.bmekings.Player.Entomologist.*;
import hu.bme.iit.projlab.bmekings.Player.Mycologist.*;


/**
 * A GameLogic osztály felelős a játék alapvető elemeinek nyilvántartásáért és a játékbeli történések vezérléséért.
 * Ez az osztály indítja el a játékot, kezeli a játékidő telését, és koordinálja a játék elemeit.
 * Kompozíciós kapcsolatban áll a Map osztállyal (egy az egyhez) és az Entity osztállyal (egy a többhöz).
 */
public class GameLogic {
    /** A játékidő telését vezérlő ticker objektum. */
    private Ticker ticker;
    /** A játékban szereplő Listener interfészt implementáló objektumok listája, amelyek frissítéseket kapnak. */
    private ArrayList<Listener> listeners = new ArrayList<>();
    private static ArrayList<Mycologist> mycologists = new ArrayList<>();
    private static ArrayList<Entomologist> entomologists = new ArrayList<>();
    private static ArrayList<Entity> entityList = new ArrayList<>();
     

    public Map map;

    public GameLogic(int TickInterval, int playerNum) {
        ticker = new Ticker(TickInterval); 
        map = new Map();
        map.generateMap(this); 
    }

    public GameLogic(ArrayList<Entity> entities, ArrayList<Listener> listeners, Map map) {
        this.listeners=listeners;
        this.map = map;
    }
    
    public void startGame() {
        ticker.start();
    }
   
    public void addListener(Listener l) {
        ticker.addListener(l);
        listeners.add(l);
    }

    public void stopGame() {
        ticker.stop();
    }

    public static ArrayList<Entomologist> getEntomologists(){
        return entomologists;
    }

    public static ArrayList<Mycologist> getMycologists(){
        return mycologists;
    }

    public static ArrayList<Entity> getEntityList(){
        return entityList;
    }

    public static void deleteEntity(Entity entity){
        entityList.remove(entity);
    }

    public static void addEntity(Entity entity){
        entityList.add(entity);
    }

}