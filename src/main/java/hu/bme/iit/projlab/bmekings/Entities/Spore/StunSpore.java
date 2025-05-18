package hu.bme.iit.projlab.bmekings.Entities.Spore;

import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
/**
 * A StunSpore osztály egy specifikus spóra típust valósít meg, amely bénító hatást fejt ki a rovarokra.
 * Ha egy rovar elfogyasztja, a rovar egy adott időtartamra lebénul, így nem tud mozogni vagy enni.
 * A Spore absztrakt osztályból származik, és implementálja a Listener interfészt az Entity ősosztályon keresztül.
 */
public class StunSpore extends Spore {

    public StunSpore() {
        super();
    }

    /**
     * A StunSpore osztály konstruktora.
     * Meghívja az ősosztály konstruktorát, és kiírja a konzolra, hogy egy új StunSpore objektum jött létre.
     */
    public StunSpore(Tecton baseLocation) {
        super(baseLocation);
    }

    /**
     * A StunSpore osztály konstruktora.
     * Meghívja az ősosztály konstruktorát, és kiírja a konzolra, hogy egy új StunSpore objektum jött létre.
     */
    public StunSpore(String id, Tecton baseLocation) {
        super(id, baseLocation);
    }
    /**
     * Aktiválja a spóra bénító hatását.
     * A metódus egy adott időtartamra megállítja a rovart, amely elfogyasztotta a spórát,
     * és kiírja a konzolra, hogy a hatás aktiválódott.
     */
    @Override
    public void activateEffect(Insect targetInsect) {
        System.out.println("Ate Stun Spore");
        targetInsect.stunEffect(4);
    }

    /**
     * Frissíti a StunSpore állapotát.
     * A metódus a Listener interfészből származik, és kiírja a konzolra, hogy a StunSpore frissítve lett.
     */
    @Override
    public void update() {
        
    }

    @Override
    public int getNutritionValue(){
        return nutritionValue;
    }    

}