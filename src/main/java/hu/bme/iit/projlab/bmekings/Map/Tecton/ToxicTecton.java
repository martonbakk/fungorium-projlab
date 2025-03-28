package hu.bme.iit.projlab.bmekings.Map.Tecton;

/**
 * A ToxicTecton osztály olyan tektonokat reprezentál, amelyeken a gombafonalak egy idő után felszívódnak.
 * Felelős ennek a jelenségnek a kezeléséért, amely során a tektonon lévő gombafonalak leválasztódnak.
 * A Tecton osztályból származik, és implementálja a Listener interfészt az ősosztályon keresztül.
 */
public class ToxicTecton extends Tecton {
    private double hyphalDestroyTime;

    public ToxicTecton(){
        super();
        this.hyphalDestroyTime = 0;
    }

    public ToxicTecton(double hyphalDestroyTime, String id, double splitChance, boolean occupiedByInsect, boolean occupiedByFungalBody) {
        super(id, splitChance, occupiedByInsect, occupiedByFungalBody);
        this.hyphalDestroyTime = hyphalDestroyTime;
    }

    public void runSpecialEffect() {

    }
}