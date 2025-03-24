/**
 * A NormalSpore osztály egy alapvető spóra típust valósít meg, amely hatással van az azt elfogyasztó rovarra.
 * A spóra elfogyasztása növeli a rovar telítettségi szintjét.
 * A Spore absztrakt osztályból származik, és implementálja a Listener interfészt az Entity ősosztályon keresztül.
 */
public class NormalSpore extends Spore {

    /**
     * A NormalSpore osztály konstruktora.
     * Meghívja az ősosztály konstruktorát, és kiírja a konzolra, hogy egy új NormalSpore objektum jött létre.
     */
    public NormalSpore() {
        super();
        System.out.println("new NormalSpore Created");
    }

    /**
     * Aktiválja a spóra hatását.
     * A metódus növeli a rovart elfogyasztó rovar telítettségi szintjét, és kiírja a konzolra, hogy a hatás aktiválódott.
     */
    public void activateEffect() {
        System.out.println("Normal effect activated");
    }

    /**
     * Létrehozza (spawnolja) a NormalSpore-t a játékban.
     * A metódus kiírja a konzolra, hogy a NormalSpore létrejött.
     */
    public void spawnSpore() {
        System.out.println("NormalSpore is spawned");
    }

    /**
     * Frissíti a NormalSpore állapotát.
     * A metódus a Listener interfészből származik, és kiírja a konzolra, hogy a NormalSpore frissítve lett.
     */
    @Override
    public void update() {
        System.out.println("NormalSpore is updated");
    }
}