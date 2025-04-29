package hu.bme.iit.projlab.bmekings.Entities.Spore;

import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;

/**
 * A SlowSpore osztály egy specifikus spóra típust valósít meg, amely lassító hatást fejt ki a rovarokra.
 * Ha egy rovar elfogyasztja, a rovar mozgási sebessége csökken, így lassabban tud mozogni a tektonok között.
 * A Spore absztrakt osztályból származik, és implementálja a Listener interfészt az Entity ősosztályon keresztül.
 */
public class SlowSpore extends Spore {

    public SlowSpore() {
        super();
    }

    public SlowSpore(Tecton baseLocation) {
        super(baseLocation);
    }

    public SlowSpore(String id, Tecton baseLocation) {
        super(id, baseLocation);
    }

    @Override
    public void activateEffect(Insect targetInsect) {
        targetInsect.decreaseMovingSpeed(10);
    }

    @Override
    public void update() {
        
    }

    @Override
    public int getNutritionValue(){
        return 0;
    }
}