package hu.bme.iit.projlab.bmekings.Map.Tecton;

/**
 * A NoFungusTecton osztály a Tecton egy speciális leszármazottja, 
 * amely egy olyan terepelemet reprezentál, ahol gombás testek nem tudnak megtelepedni.
 * Ez az osztály felülírhatja a {@code Tecton} viselkedését a runSpecialEffect
 * metódus segítségével, bár jelenleg az implementáció üres.
 * Kétféle konstruktor érhető el: egy paraméteres, amellyel az objektum azonnal 
 * inicializálható, valamint egy paraméter nélküli alapértelmezett konstruktor.
 */
public class NoFungusTecton extends Tecton {

    public NoFungusTecton(String id, double splitChance, boolean occupiedByInsect, boolean occupiedByFungalBody) {
        super( id,  splitChance,  occupiedByInsect,  occupiedByFungalBody);
    }

    public NoFungusTecton(double splitChance, boolean occupiedByInsect, boolean occupiedByFungalBody) {
        super(splitChance,  occupiedByInsect,  occupiedByFungalBody);
    }

    public NoFungusTecton(){
        super();
    }

    public void runSpecialEffect() {

    }
}