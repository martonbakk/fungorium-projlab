package hu.bme.iit.projlab.bmekings.Map.Tecton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        return this.flags;
    }
}