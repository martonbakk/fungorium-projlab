package hu.bme.iit.projlab.bmekings.Entities.Spore;

import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;

/**
 * A HungerSpore osztály egy specifikus spóra típust valósít meg, amely éheztető hatást fejt ki a rovarokra.
 * Ha egy rovar elfogyasztja, csökkenti a rovar telítettségi szintjét, így a rovar több spórát tud fogyasztani.
 * A Spore absztrakt osztályból származik, és implementálja a Listener interfészt az Entity ősosztályon keresztül.
 */
public class HungerSpore extends Spore {

    public HungerSpore(String id1, Tecton baseLocation1) {
        super();
    }

    public HungerSpore(Tecton baseLocation) {
        super(baseLocation);
    }

    @Override
    public void activateEffect(Insect targetInsect) {
        targetInsect.feedInsect(this.getNutritionValue());
        targetInsect.feedInsect(this.getNutritionValue());
    }

    @Override
    public void update() {

    }

    @Override
    public int getNutritionValue(){
        return nutritionValue * -1;
    }
}