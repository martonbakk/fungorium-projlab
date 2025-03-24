/**
 * A SlowSpore osztály egy specifikus spóra típust valósít meg, amely lassító hatást fejt ki a rovarokra.
 * Ha egy rovar elfogyasztja, a rovar mozgási sebessége csökken, így lassabban tud mozogni a tektonok között.
 * A Spore absztrakt osztályból származik, és implementálja a Listener interfészt az Entity ősosztályon keresztül.
 */
public class SlowSpore extends Spore {

    /**
     * A SlowSpore osztály konstruktora.
     * Meghívja az ősosztály konstruktorát, és kiírja a konzolra, hogy egy új SlowSpore objektum jött létre.
     */
    public SlowSpore() {
        super();
        System.out.println("new SlowSpore Created");
    }

    /**
     * Aktiválja a spóra lassító hatását.
     * A metódus csökkenti a rovart elfogyasztó rovar mozgási sebességét, és kiírja a konzolra, hogy a hatás aktiválódott.
     */
    public void activateEffect() {
        System.out.println("Slow effect activated");
    }

    /**
     * Létrehozza (spawnolja) a SlowSpore-t a játékban.
     * A metódus kiírja a konzolra, hogy a SlowSpore létrejött.
     */
    public void spawnSpore() {
        System.out.println("SlowSpore is spawned");
    }

    /**
     * Frissíti a SlowSpore állapotát.
     * A metódus a Listener interfészből származik, és kiírja a konzolra, hogy a SlowSpore frissítve lett.
     */
    @Override
    public void update() {
        System.out.println("SlowSpore is updated");
    }
}