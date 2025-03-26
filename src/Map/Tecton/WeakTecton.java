package Map.Tecton;

/**
 * A WeakTecton osztály olyan tektonokat reprezentál, amelyek nem engedélyezik, hogy egynél több gombafonal
 * nőjön rájuk. Felelős annak biztosításáért, hogy a tektonon csak egyetlen gombafonal létezhessen.
 * A Tecton osztályból származik, és implementálja a Listener interfészt az ősosztályon keresztül.
 */
public class WeakTecton extends Tecton {


    public WeakTecton() {
    }


    public void runSpecialEffect() {}
}