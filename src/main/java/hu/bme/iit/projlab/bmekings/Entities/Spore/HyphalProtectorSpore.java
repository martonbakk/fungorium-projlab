package hu.bme.iit.projlab.bmekings.Entities.Spore;

import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;


/**
 * A HyphalProtectorSpore osztály egy specifikus spóra típust valósít meg, amely védő hatást fejt ki a gombafonalakra.
 * Ha egy rovar elfogyasztja, bizonyos ideig nem lesz képes gombafonalat vágni.
 * A Spore absztrakt osztályból származik, és implementálja a Listener interfészt az Entity ősosztályon keresztül.
 */
public class HyphalProtectorSpore extends Spore {

    public HyphalProtectorSpore() {
        super();
    }

    public HyphalProtectorSpore(Tecton baseLocation) {
        super(baseLocation);
    }

    @Override
    public void activateEffect(Insect targInsect) {
        System.out.println("Ate HyphalProtector Spore");
        targInsect.inreaseHyphalCoolDown(10);
    }

    @Override
    public void update() {
        
    }

    @Override
    public int getNutritionValue(){
        return nutritionValue;
    }
}
