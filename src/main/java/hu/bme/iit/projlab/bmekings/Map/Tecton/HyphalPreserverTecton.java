package hu.bme.iit.projlab.bmekings.Map.Tecton;

/**
 * A HyphalPreserverTecton osztály a Tecton egy specifikus 
 * leszármazottja, amely egy olyan struktúrát reprezentál, amely képes 
 * megőrizni a hifális (gombafonalas) struktúrákat.
 * Ez az osztály kiterjeszti az alap Tecton működését, és egyedi
 * speciális hatásokat is végrehajthat a runSpecialEffect metóduson keresztül.
 */
public class HyphalPreserverTecton extends Tecton {

    public HyphalPreserverTecton(String id, double splitChance, boolean occupiedByInsect, boolean occupiedByFungalBody) {
        super( id,  splitChance,  occupiedByInsect,  occupiedByFungalBody);
    }

    public HyphalPreserverTecton() {
        super();
    }

    public void runSpecialEffect() {

    }
}