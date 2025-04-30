package hu.bme.iit.projlab.bmekings.Entities.Spore;

import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;

/**
 * A SpeedSpore osztály egy specifikus spóra típust valósít meg, amely gyorsító hatást fejt ki a rovarokra.
 * Ha egy rovar elfogyasztja, a rovar mozgási sebessége nő, így gyorsabban tud mozogni a tektonok között.
 * A Spore absztrakt osztályból származik, és implementálja a Listener interfészt az Entity ősosztályon keresztül.
 */
public class SpeedSpore extends Spore {

    public SpeedSpore() {
        super();
    }

    public SpeedSpore( Tecton baseLocation) {
        super(baseLocation);
    }

    public SpeedSpore(String id, Tecton baseLocation) {
        super(id, baseLocation);
    }

    @Override
    public void activateEffect(Insect targetInsect) {
        targetInsect.increaseMovingSpeed(10);
    }

    @Override
    public void update() {

    }

    @Override
    public int getNutritionValue(){
        return nutritionValue;
    }

}
