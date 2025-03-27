package main.java.hu.bme.iit.projlab.bmekings.Entities.Spore;
import main.java.hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
/**
 * A SlowSpore osztály egy specifikus spóra típust valósít meg, amely lassító hatást fejt ki a rovarokra.
 * Ha egy rovar elfogyasztja, a rovar mozgási sebessége csökken, így lassabban tud mozogni a tektonok között.
 * A Spore absztrakt osztályból származik, és implementálja a Listener interfészt az Entity ősosztályon keresztül.
 */
public class SlowSpore extends Spore {

    public SlowSpore() {
        super();
    }

    public SlowSpore(int n, String id, Tecton baseLocation) {
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