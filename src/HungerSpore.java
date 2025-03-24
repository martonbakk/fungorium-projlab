/**
 * A HungerSpore osztály egy specifikus spóra típust valósít meg, amely éheztető hatást fejt ki a rovarokra.
 * Ha egy rovar elfogyasztja, csökkenti a rovar telítettségi szintjét, így a rovar több spórát tud fogyasztani.
 * A Spore absztrakt osztályból származik, és implementálja a Listener interfészt az Entity ősosztályon keresztül.
 */
public class HungerSpore extends Spore {

    /**
     * A HungerSpore osztály konstruktora.
     * Meghívja az ősosztály konstruktorát, és kiírja a konzolra, hogy egy új HungerSpore objektum jött létre.
     */
    public HungerSpore() {
        super();
        System.out.println("new HungerSpore Created");
    }

    /**
     * Aktiválja a spóra éheztető hatását.
     * A metódus csökkenti a rovart elfogyasztó rovar telítettségi szintjét, és kiírja a konzolra, hogy a hatás aktiválódott.
     */
    public void activateEffect() {
        System.out.println("Hunger effect activated");
    }

    /**
     * Létrehozza (spawnolja) a HungerSpore-t a játékban.
     * A metódus kiírja a konzolra, hogy a HungerSpore létrejött.
     */
    public void spawnSpore() {
        System.out.println("HungerSpore is spawned");
    }

    /**
     * Frissíti a HungerSpore állapotát.
     * A metódus a Listener interfészből származik, és kiírja a konzolra, hogy a HungerSpore frissítve lett.
     */
    @Override
    public void update() {
        System.out.println("HungerSpore is updated");
    }
}