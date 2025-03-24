/**
 * Absztrakt osztály, amely az entitások és figurák közös alapadatait tárolja.
 * Az entitások a játékban szereplő egyedeket reprezentálják, például gombatesteket, spórákat vagy rovarokat.
 * Implementálja a Listener interfészt, így minden leszármazottjának biztosítania kell az update() metódus implementációját.
 */
public abstract class Entity implements Listener {

    /** Az a tekton, amelyen az entitás vagy figura éppen tartózkodik. */
    public Tecton baseLocation;

    /**
     * Az Entity osztály alapértelmezett konstruktora.
     * Kiírja a konzolra, hogy egy új Entity objektum jött létre.
     */
    public Entity() {
        System.out.println("\tnew Entity");
    }
}