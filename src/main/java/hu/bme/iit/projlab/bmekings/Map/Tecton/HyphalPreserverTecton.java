package hu.bme.iit.projlab.bmekings.Map.Tecton;

import java.util.ArrayList;

import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Logger.Loggable;

/**
 * A HyphalPreserverTecton osztály a Tecton egy specifikus 
 * leszármazottja, amely egy olyan struktúrát reprezentál, amely képes 
 * megőrizni a hifális (gombafonalas) struktúrákat.
 * Ez az osztály kiterjeszti az alap Tecton működését, és egyedi
 * speciális hatásokat is végrehajthat a runSpecialEffect metóduson keresztül.
 */

@Loggable("HyhalPreserverTecton")
public class HyphalPreserverTecton extends Tecton {
    public HyphalPreserverTecton(double splitChance, boolean occupiedByInsect, boolean occupiedByFungalBody) {
        super(splitChance,  occupiedByInsect,  occupiedByFungalBody);
    }

    public HyphalPreserverTecton() {
        super();
    }

    @Loggable
    @Override
    public Flags runSpecialEffect() {
        
        ArrayList<Hyphal> hyphals = connectedNeighbours.get(this);
            if (hyphals != null) {
                for (Hyphal hyphal : hyphals) {
                    hyphal.hyphalHeal();
            }
        }
        return this.flags;
    }
}