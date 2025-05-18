package hu.bme.iit.projlab.bmekings.Logic.GameLogic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import hu.bme.iit.projlab.bmekings.Entities.Entity;
import hu.bme.iit.projlab.bmekings.Interface.Listener.Listener;
import hu.bme.iit.projlab.bmekings.Logic.Ticker.Ticker;
import hu.bme.iit.projlab.bmekings.Map.Map;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Player.Entomologist.Entomologist;
import hu.bme.iit.projlab.bmekings.Player.Mycologist.Mycologist;
import hu.bme.iit.projlab.bmekings.Program.Params;


/**
 * A GameLogic osztály felelős a játék alapvető elemeinek nyilvántartásáért és a játékbeli történések vezérléséért.
 * Ez az osztály indítja el a játékot, kezeli a játékidő telését, és koordinálja a játék elemeit.
 * Kompozíciós kapcsolatban áll a Map osztállyal (egy az egyhez) és az Entity osztállyal (egy a többhöz).
 */
public class GameLogic implements Serializable{
    private static final long serialVersionUID = 1L;

    /** A játékidő telését vezérlő ticker objektum. */
    private Ticker ticker=new Ticker(1000);
    /** A játékban szereplő Listener interfészt implementáló objektumok listája, amelyek frissítéseket kapnak. */
    private transient ArrayList<Listener> listeners = new ArrayList<>();
    private static ArrayList<Mycologist> mycologists = new ArrayList<>();
    private static ArrayList<Entomologist> entomologists = new ArrayList<>();
    private static ArrayList<Entity> entityList = new ArrayList<>();
    private static Params params = new Params(); 
    public Map map;
    private long elapsedTicks;
    private long maxTicks;

    /// Ezek azért kellenek mert statikus mezőket nem lehet szerializálni, ezért ezeken keresztül lesznel majd kezelve a szerializálás
    private ArrayList<Mycologist> serializedMycologists;
    private ArrayList<Entomologist> serializedEntomologists;
    private ArrayList<Entity> serializedEntityList;

    public ArrayList<Listener> getListeners() { return listeners; }
    
    public Map getMap(){
        return map;
    } 
    
    
    private void writeObject(ObjectOutputStream oos) throws IOException {
        // Statikus listák másolása az ideiglenes mezőkbe
        synchronized (mycologists) {
            serializedMycologists = new ArrayList<>(mycologists);
        }
        synchronized (entomologists) {
            serializedEntomologists = new ArrayList<>(entomologists);
        }
        synchronized (entityList) {
            serializedEntityList = new ArrayList<>(entityList);
        }
        oos.defaultWriteObject(); // Szerializálja az összes nem statikus mezőt
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
    ois.defaultReadObject(); // Deszerializálja a nem statikus mezőket
    synchronized (mycologists) {
        mycologists.clear();
        mycologists.addAll(serializedMycologists);
    }
    synchronized (entomologists) {
        entomologists.clear();
        entomologists.addAll(serializedEntomologists);
    }
    synchronized (entityList) {
        entityList.clear();
        entityList.addAll(serializedEntityList);
    }
    }

    public void saveGame(String name) throws IOException {
    // Érvényes fájlnév ellenőrzése
    if (name == null || name.trim().isEmpty() || name.matches(".*[\\\\/:*?\"<>|].*")) {
        throw new IllegalArgumentException("Érvénytelen mentési név: " + name);
    }

    // A játék gyökérmappájának elérése
    String rootDir = System.getProperty("user.dir");
    // A saves mappa elérési útja
    Path savesDir = Paths.get(rootDir, "Saves");

    // Ellenőrizzük, hogy a saves mappa létezik-e, ha nem, létrehozzuk
    Files.createDirectories(savesDir);

    // A mentési fájl elérési útja: saves/name.ser
    Path filePath = savesDir.resolve(name + ".ser");
    String path = filePath.toString();

    // Játékállapot érvényességének ellenőrzése
    validateState();

    // Játékállapot mentése
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
        oos.writeObject(this); 
        System.out.println("Játék sikeresen mentve: " + path);
    }
}

    public static GameLogic loadGame(String name) throws IOException, ClassNotFoundException {
        if (name == null || name.trim().isEmpty() || name.matches(".*[\\\\/:*?\"<>|].*")) {
        throw new IllegalArgumentException("Érvénytelen mentési név: " + name);
        }

        // A játék gyökérmappájának elérése
        String rootDir = System.getProperty("user.dir");
        // A saves mappa elérési útja
        Path savesDir = Paths.get(rootDir, "Saves");

        // Ellenőrizzük, hogy a saves mappa létezik-e, ha nem, létrehozzuk
        Files.createDirectories(savesDir);

        // A mentési fájl elérési útja: saves/name.ser
        Path filePath = savesDir.resolve(name + ".ser");
        String path = filePath.toString();
        
        
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            GameLogic loadedGame = (GameLogic) ois.readObject();
            return loadedGame;
        }
    }

    private void validateState() {
        if (map == null) throw new IllegalStateException("Map is null");
        if (mycologists == null) throw new IllegalStateException("Mycologists list is null");
        if (entomologists == null) throw new IllegalStateException("Entomologists list is null");
        if (entityList == null) throw new IllegalStateException("Entity list is null");
    }

    public GameLogic(int TickInterval, int playerNum) {
        ticker = new Ticker(TickInterval);
        map = new Map();
        this.maxTicks = TickInterval;
    }

    @SuppressWarnings("static-access")
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

    public void tick() {
        for (Tecton t : map.getAllTectons()) {
            t.update();
        }

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