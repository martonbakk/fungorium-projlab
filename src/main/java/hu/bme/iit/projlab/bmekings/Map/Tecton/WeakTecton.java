package hu.bme.iit.projlab.bmekings.Map.Tecton;

import hu.bme.iit.projlab.bmekings.Logger.Loggable;

/**
 * A WeakTecton osztály olyan tektonokat reprezentál, amelyek nem engedélyezik, hogy egynél több gombafonal
 * nőjön rájuk. Felelős annak biztosításáért, hogy a tektonon csak egyetlen gombafonal létezhessen.
 * A Tecton osztályból származik, és implementálja a Listener interfészt az ősosztályon keresztül.
 */

@Loggable("WeakTecton")
public class WeakTecton extends Tecton {
    private boolean hyphalExistHere;

    public WeakTecton(){
        super();
        this.hyphalExistHere=false;
        this.flags.oneHyphalApproved=10;
    }

    public WeakTecton(boolean hyphalExistHere, double splitChance, boolean occupiedByInsect, boolean occupiedByFungalBody) {
        super(splitChance, occupiedByInsect, occupiedByFungalBody);
        this.hyphalExistHere = hyphalExistHere;
    }

    @Loggable
    public Flags runSpecialEffect() {
        return this.flags;
    }   
}