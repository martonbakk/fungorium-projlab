/**
 * A SpeedSpore osztály egy specifikus spóra típust valósít meg, amely gyorsító hatást fejt ki a rovarokra.
 * Ha egy rovar elfogyasztja, a rovar mozgási sebessége nő, így gyorsabban tud mozogni a tektonok között.
 * A Spore absztrakt osztályból származik, és implementálja a Listener interfészt az Entity ősosztályon keresztül.
 */
public class SpeedSpore extends Spore {

    /**
     * A SpeedSpore osztály konstruktora.
     * Meghívja az ősosztály konstruktorát, és kiírja a konzolra, hogy egy új SpeedSpore objektum jött létre.
     */
    public SpeedSpore() {
        super();
        System.out.println("new SpeedSpore Created");
    }

    /**
     * Aktiválja a spóra gyorsító hatását.
     * A metódus növeli a rovart elfogyasztó rovar mozgási sebességét, és kiírja a konzolra, hogy a hatás aktiválódott.
     */
    public void activateEffect() {
        System.out.println("Speed effect activated");
    }

    /**
     * Létrehozza (spawnolja) a SpeedSpore-t a játékban.
     * A metódus kiírja a konzolra, hogy a SpeedSpore létrejött.
     */
    public void spawnSpore() {
        System.out.println("SpeedSpore is spawned");
    }

    /**
     * Frissíti a SpeedSpore állapotát.
     * A metódus a Listener interfészből származik, és kiírja a konzolra, hogy a SpeedSpore frissítve lett.
     */
    @Override
    public void update() {
        System.out.println("SpeedSpore is updated");
    }
}