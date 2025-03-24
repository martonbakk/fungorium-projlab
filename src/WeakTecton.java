
/**
 * A WeakTecton osztály olyan tektonokat reprezentál, amelyek nem engedélyezik, hogy egynél több gombafonal
 * nőjön rájuk. Felelős annak biztosításáért, hogy a tektonon csak egyetlen gombafonal létezhessen.
 * A Tecton osztályból származik, és implementálja a Listener interfészt az ősosztályon keresztül.
 */
public class WeakTecton extends Tecton {

    /**
     * A WeakTecton osztály konstruktora.
     * Kiírja a konzolra, hogy egy új WeakTecton objektum jött létre.
     */
    public WeakTecton() {
        System.out.println("new WeakTecton created");
    }

    /**
     * A WeakTecton speciális hatását aktiválja.
     * A metódus megszakítja a műveletet, ha egy második gombafonal megpróbál a tektonra nőni.
     * Jelenleg üres implementációval rendelkezik.
     */
    public void runSpecialEffect() {}
}