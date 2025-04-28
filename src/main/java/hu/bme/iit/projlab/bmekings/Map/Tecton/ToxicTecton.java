package hu.bme.iit.projlab.bmekings.Map.Tecton;

import java.util.ArrayList;

import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Logger.Loggable;

/**
 * A ToxicTecton osztály olyan tektonokat reprezentál, amelyeken a gombafonalak egy idő után felszívódnak.
 * Felelős ennek a jelenségnek a kezeléséért, amely során a tektonon lévő gombafonalak leválasztódnak.
 * A Tecton osztályból származik, és implementálja a Listener interfészt az ősosztályon keresztül.
 */

@Loggable("ToxicTecton")
public class ToxicTecton extends Tecton {
    private double hyphalDestroyTime;

    public ToxicTecton(){
        super();
        this.hyphalDestroyTime = 0;
    }

    public ToxicTecton(double hyphalDestroyTime, double splitChance, boolean occupiedByInsect, boolean occupiedByFungalBody) {
        super(splitChance, occupiedByInsect, occupiedByFungalBody);
        this.hyphalDestroyTime = hyphalDestroyTime;
    }

    @Loggable
    public Flags runSpecialEffect() {
        ArrayList<Hyphal> hyphals = connectedNeighbours.get(this);
            if (hyphals != null) {
                for (Hyphal hyphal : hyphals) {
                    hyphal.dying();
            }
        }
        return this.flags;
    }
}