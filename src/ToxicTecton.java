/**
 * A ToxicTecton osztály olyan tektonokat reprezentál, amelyeken a gombafonalak egy idő után felszívódnak.
 * Felelős ennek a jelenségnek a kezeléséért, amely során a tektonon lévő gombafonalak leválasztódnak.
 * A Tecton osztályból származik, és implementálja a Listener interfészt az ősosztályon keresztül.
 */
public class ToxicTecton extends Tecton {

    /**
     * A ToxicTecton osztály konstruktora.
     * Kiírja a konzolra, hogy egy új ToxicTecton objektum jött létre.
     */
    public ToxicTecton() {
        System.out.println("new ToxicTecton created");
    }

    /**
     * A ToxicTecton speciális hatását aktiválja.
     * A metódus leválasztja a tektonon lévő gombafonalakat, szimulálva a felszívódásukat,
     * és kiírja a konzolra a műveletet.
     */
    public void runSpecialEffect() {
        System.out.println("-> disconnectTecton()");
        this.disconnectTecton();
    }
}