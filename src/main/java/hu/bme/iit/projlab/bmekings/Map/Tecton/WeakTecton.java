package hu.bme.iit.projlab.bmekings.Map.Tecton;

/**
 * A WeakTecton osztály olyan tektonokat reprezentál, amelyek nem engedélyezik, hogy egynél több gombafonal
 * nőjön rájuk. Felelős annak biztosításáért, hogy a tektonon csak egyetlen gombafonal létezhessen.
 * A Tecton osztályból származik, és implementálja a Listener interfészt az ősosztályon keresztül.
 */
public class WeakTecton extends Tecton {
    private boolean hyphalExistHere;

    public WeakTecton(){
        super();
        this.hyphalExistHere=false;
    }

    public WeakTecton(boolean hyphalExistHere, String id, double splitChance, boolean occupiedByInsect, boolean occupiedByFungalBody) {
        super(id, splitChance, occupiedByInsect, occupiedByFungalBody);
        this.hyphalExistHere = hyphalExistHere;
    }

    public WeakTecton(boolean hyphalExistHere, double splitChance, boolean occupiedByInsect, boolean occupiedByFungalBody) {
        super(splitChance, occupiedByInsect, occupiedByFungalBody);
        this.hyphalExistHere = hyphalExistHere;
    }

    public void runSpecialEffect() {}
}