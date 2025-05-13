package hu.bme.iit.projlab.bmekings.Logic.GameLogic;

import java.util.ArrayList;

import hu.bme.iit.projlab.bmekings.Entities.Entity;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.FungalBody;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Interface.Listener.Listener;
import hu.bme.iit.projlab.bmekings.Interface.SporeInterface.SporeInterface;
import hu.bme.iit.projlab.bmekings.Logic.Ticker.Ticker;
import hu.bme.iit.projlab.bmekings.Map.Map;
import hu.bme.iit.projlab.bmekings.Player.Entomologist.Entomologist;
import hu.bme.iit.projlab.bmekings.Player.Mycologist.Mycologist;
import hu.bme.iit.projlab.bmekings.Program.Params;


/**
 * A GameLogic osztály felelős a játék alapvető elemeinek nyilvántartásáért és a játékbeli történések vezérléséért.
 * Ez az osztály indítja el a játékot, kezeli a játékidő telését, és koordinálja a játék elemeit.
 * Kompozíciós kapcsolatban áll a Map osztállyal (egy az egyhez) és az Entity osztállyal (egy a többhöz).
 */
public class GameLogic {
    /** A játékidő telését vezérlő ticker objektum. */
    private Ticker ticker=new Ticker(1000);
    /** A játékban szereplő Listener interfészt implementáló objektumok listája, amelyek frissítéseket kapnak. */
    private ArrayList<Listener> listeners = new ArrayList<>();
    private static ArrayList<Mycologist> mycologists = new ArrayList<>();
    private static ArrayList<Entomologist> entomologists = new ArrayList<>();
    private static ArrayList<Entity> entityList = new ArrayList<>();
    private static Params params = new Params();
    public Map map;

    public GameLogic(int TickInterval, int playerNum) {
        ticker = new Ticker(TickInterval);
        map = new Map();
    }

    public GameLogic(ArrayList<Entity> entities, ArrayList<Listener> listeners, Map map) {
        this.entityList = entities;
        this.listeners=listeners;
        this.map = map;
        for (Listener l : listeners) {
            ticker.addListener(l);
        }
    }
    
    public void startGame() {
        ticker.start();
    }

    // kéne a teszteléshez egy olyan függvény, ami csak 1, vagy több tick-et hajt végre
    public void tick() {
        for (Entity e : entityList) {
            e.update();
        }
    }
   
    public void addListener(Listener l) {
        ticker.addListener(l);
        listeners.add(l);
    }

    public void stopGame() {
        ticker.stop();
    }

    public static Params getParams() {
        return params;
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

    public static void addEntomologist(Entomologist player){
        entomologists.add(player);
    }

    public static void addMycologist(Mycologist player){
        mycologists.add(player);
    }

    public static void resetPlayers(){
        mycologists = new ArrayList<>();
        entomologists = new ArrayList<>();
    }

}