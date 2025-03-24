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

    /**
     * A GameLogic osztály konstruktora.
     * Kiírja a konzolra, hogy egy új GameLogic objektum jött létre.
     */
    public GameLogic() {
        System.out.println("\tnew GameLogic");
    }

    /**
     * Elindítja a játékot.
     * A metódus jelenleg a térkép generálását szimulálja, és kiírja a konzolra a műveletet.
     */
    public void startGame() {
        System.out.println("-> generateMap()");
    }

    /**
     * Kezeli a játékidő telését.
     * A metódus a játék elindulásakor kerül meghívásra, és szimulálja az idő múlását a játékban.
     */
    public void timeTick() {
        System.out.println("-> timeTick()");
    }

    /**
     * Hozzáad egy új Listener objektumot a figyelők listájához.
     * A metódus kiírja a konzolra, hogy egy új figyelő került hozzáadásra.
     */
    public void addListener() {
        System.out.println("-> Listener added");
    }
}