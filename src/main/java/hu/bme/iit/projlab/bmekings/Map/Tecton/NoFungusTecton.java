package hu.bme.iit.projlab.bmekings.Map.Tecton;

import hu.bme.iit.projlab.bmekings.Logger.Loggable;

/**
 * A NoFungusTecton osztály a Tecton egy speciális leszármazottja, 
 * amely egy olyan terepelemet reprezentál, ahol gombás testek nem tudnak megtelepedni.
 * Ez az osztály felülírhatja a {@code Tecton} viselkedését a runSpecialEffect
 * metódus segítségéve
 * Kétféle konstruktor érhető el: egy paraméteres, amellyel az objektum azonnal 
 * inicializálható, valamint egy paraméter nélküli alapértelmezett konstruktor.
 */

@Loggable("NoFungusTecton")
public class NoFungusTecton extends Tecton {
    public NoFungusTecton(double splitChance, boolean occupiedByInsect, boolean occupiedByFungalBody) {
        super(splitChance,  occupiedByInsect,  occupiedByFungalBody);
        this.flags.fungalApproved=false;
    }

    public NoFungusTecton(){
        super();
        this.flags.fungalApproved=false;
    }

    @Loggable
    public Flags runSpecialEffect() {
        return this.flags;
    }
}