/**
 * A HyphalProtectorSpore osztály egy specifikus spóra típust valósít meg, amely védő hatást fejt ki a gombafonalakra.
 * Ha egy rovar elfogyasztja, bizonyos ideig nem lesz képes gombafonalat vágni.
 * A Spore absztrakt osztályból származik, és implementálja a Listener interfészt az Entity ősosztályon keresztül.
 */
public class HyphalProtectorSpore extends Spore {

    /**
     * A HyphalProtectorSpore osztály konstruktora.
     * Meghívja az ősosztály konstruktorát, és kiírja a konzolra, hogy egy új HyphalProtectorSpore objektum jött létre.
     */
    public HyphalProtectorSpore() {
        super();
        System.out.println("new HyphalProtectorSpore Created");
    }

    /**
     * Aktiválja a spóra védő hatását.
     * A metódus megakadályozza, hogy a rovar, amely elfogyasztotta a spórát, egy adott ideig gombafonalat tudjon vágni,
     * és kiírja a konzolra, hogy a hatás aktiválódott.
     */
    public void activateEffect() {
        System.out.println("HyphalProtector effect activated");
    }

    /**
     * Létrehozza (spawnolja) a HyphalProtectorSpore-t a játékban.
     * A metódus kiírja a konzolra, hogy a HyphalProtectorSpore létrejött.
     */
    public void spawnSpore() {
        System.out.println("HyphalProtectorSpore is spawned");
    }

    /**
     * Frissíti a HyphalProtectorSpore állapotát.
     * A metódus a Listener interfészből származik, és kiírja a konzolra, hogy a HyphalProtectorSpore frissítve lett.
     */
    @Override
    public void update() {
        System.out.println("HyphalProtectorSpore is updated");
    }
}