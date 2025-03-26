import java.util.ArrayList;

/**
 * A GameLogic osztály felelős a játék alapvető elemeinek nyilvántartásáért és a játékbeli történések vezérléséért.
 * Ez az osztály indítja el a játékot, kezeli a játékidő telését, és koordinálja a játék elemeit.
 * Kompozíciós kapcsolatban áll a Map osztállyal (egy az egyhez) és az Entity osztállyal (egy a többhöz).
 */
public class GameLogic {

    /** A játékban szereplő entitások listája, például gombatestek, spórák vagy rovarok. */
    ArrayList<Entity> entities = new ArrayList<>();

    /** A játékban szereplő Listener interfészt implementáló objektumok listája, amelyek frissítéseket kapnak. */
    ArrayList<Listener> listeners = new ArrayList<>();

    
    public GameLogic() {

    }

    
    public void startGame() {
        
    }

    
    public void timeTick() {

    }

   
    public void addListener() {

    }
}