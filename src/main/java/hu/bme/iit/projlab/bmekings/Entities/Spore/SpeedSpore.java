package main.java.hu.bme.iit.projlab.bmekings.Entities.Spore;
import main.java.hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
/**
 * A SpeedSpore osztály egy specifikus spóra típust valósít meg, amely gyorsító hatást fejt ki a rovarokra.
 * Ha egy rovar elfogyasztja, a rovar mozgási sebessége nő, így gyorsabban tud mozogni a tektonok között.
 * A Spore absztrakt osztályból származik, és implementálja a Listener interfészt az Entity ősosztályon keresztül.
 */
public class SpeedSpore extends Spore {

    public SpeedSpore() {
        super();
    }

    public SpeedSpore(int n, String id, Tecton baseLocation) {
        super(n, id, baseLocation);
    }


    public void activateEffect() {

    }


    public void spawnSpore() {

    }


    @Override
    public void update() {

    }
}
