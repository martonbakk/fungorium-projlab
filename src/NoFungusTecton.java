/**
 * A NoFungusTecton osztály olyan tektonokat reprezentál, amelyeken nem nőhet gombatest.
 * Felelőssége annak biztosítása, hogy gombafonalak ne tudjanak ezen a tektonon fejlődni.
 * A Tecton osztályból származik, és implementálja a Listener interfészt az ősosztályon keresztül.
 */
public class NoFungusTecton extends Tecton {

    /**
     * A NoFungusTecton osztály konstruktora.
     * Kiírja a konzolra, hogy egy új NoFungusTecton objektum jött létre.
     */
    public NoFungusTecton() {
        System.out.println("new NoFungusTecton created");
    }

    /**
     * A NoFungusTecton speciális hatását aktiválja.
     * A metódus megakadályozza, hogy gombafonalak vagy gombatestek nőjenek a tektonon,
     * és kiírja a konzolra, hogy gomba nem nőhet itt.
     */
    public void runSpecialEffect() {
        System.out.println("No fungus can grow");
    }
}