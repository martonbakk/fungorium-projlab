/**
 * A StunSpore osztály egy specifikus spóra típust valósít meg, amely bénító hatást fejt ki a rovarokra.
 * Ha egy rovar elfogyasztja, a rovar egy adott időtartamra lebénul, így nem tud mozogni vagy enni.
 * A Spore absztrakt osztályból származik, és implementálja a Listener interfészt az Entity ősosztályon keresztül.
 */
public class StunSpore extends Spore {

    /**
     * A StunSpore osztály konstruktora.
     * Meghívja az ősosztály konstruktorát, és kiírja a konzolra, hogy egy új StunSpore objektum jött létre.
     */
    public StunSpore() {
        super();
        System.out.println("new StunSpore Created");
    }

    /**
     * Aktiválja a spóra bénító hatását.
     * A metódus egy adott időtartamra megállítja a rovart, amely elfogyasztotta a spórát,
     * és kiírja a konzolra, hogy a hatás aktiválódott.
     */
    public void activateEffect() {
        System.out.println("Stun effect activated");
    }

    /**
     * Létrehozza (spawnolja) a StunSpore-t a játékban.
     * A metódus kiírja a konzolra, hogy a StunSpore létrejött.
     */
    public void spawnSpore() {
        System.out.println("StunSpore is spawned");
    }

    /**
     * Frissíti a StunSpore állapotát.
     * A metódus a Listener interfészből származik, és kiírja a konzolra, hogy a StunSpore frissítve lett.
     */
    @Override
    public void update() {
        System.out.println("StunSpore is updated");
    }
}