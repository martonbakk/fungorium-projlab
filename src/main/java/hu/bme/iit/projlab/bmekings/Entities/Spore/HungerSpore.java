package hu.bme.iit.projlab.bmekings.Entities.Spore;

import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;

/**
 * A HungerSpore osztály egy specifikus spóra típust valósít meg, amely éheztető hatást fejt ki a rovarokra.
 * Ha egy rovar elfogyasztja, csökkenti a rovar telítettségi szintjét, így a rovar több spórát tud fogyasztani.
 * A Spore absztrakt osztályból származik, és implementálja a Listener interfészt az Entity ősosztályon keresztül.
 */
public class HungerSpore extends Spore {

    public HungerSpore() {
        super();
    }

    public HungerSpore(int n, String id, Tecton baseLocation) {
        super(n,id,baseLocation);
    }

    public HungerSpore(int n, Tecton baseLocation) {
        super(n,baseLocation);
    }

    public void activateEffect(Insect targetInsect) {
        
    }

    @Override
    public void update() {

    }

    @Override
    public int getNutritionValue(){
        return 0;
    }
}