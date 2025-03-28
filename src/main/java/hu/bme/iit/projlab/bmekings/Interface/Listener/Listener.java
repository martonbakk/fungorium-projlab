package hu.bme.iit.projlab.bmekings.Interface.Listener;

/**
 * A Listener interfész definiálja a játékban szereplő objektumok frissítési mechanizmusát.
 * Azok az osztályok, amelyek implementálják ezt az interfészt, kötelesek megvalósítani az update() metódust,
 * amely a játékbeli állapotuk frissítésére szolgál.
 */
public interface Listener {

    /**
     * Frissíti az objektum állapotát.
     * Ez egy absztrakt metódus, amelyet minden implementáló osztálynak meg kell valósítania.
     */
    void update();
}